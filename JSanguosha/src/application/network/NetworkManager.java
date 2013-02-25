/**
 * protocol: 
 * #: planned
 * ^: designed
 * 
 * on connection: 
 * 
 * ^server sends environment's needed type meta list, client respond with what's missing, 
 * server sends related type meta to client
 * 
 * ^client sends user meta (id name, avatar) to server, server logs in client
 * 
 * ^server deploys game info to client
 * 
 * ^on resolution of game, server sends update to client
 * 
 */


package application.network;

import game.PlayerManager;
import game.entity.Entity;
import hx.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.network.handlers.EntityDeployHandler;
import application.network.handlers.EntityRemoveHandler;
import application.network.handlers.EntityUpdateHandler;
import application.network.handlers.INetworkHandler;
import application.network.handlers.SelectionRequestHandler;
import application.network.handlers.SelectionUpdateHandler;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class NetworkManager {
	public enum Side
	{
		Server,
		Client,
		Host
	}
	
	Server server;
	Client client;
	Side side;
	
	HashMap <Connection,Integer> connection_to_playerID;
	HashMap <Integer,Connection> playerID_to_connection;
	
	PlayerManager players;
	
	static ArrayList<INetworkHandler> handlers = new ArrayList<INetworkHandler>();
	
	public static NetworkManager instance;
	
	public static void initialize(Stage stage)
	{
			instance = new NetworkManager();
			instance.register(new EntityDeployHandler(stage));
			instance.register(new EntityRemoveHandler());
			instance.register(new EntityUpdateHandler());
			instance.register(new SelectionRequestHandler());
			instance.register(new SelectionUpdateHandler());
	}
	
	public static void initialize(Kryo kryo)
	{
		kryo.register(CardEntityUpdate.class);
		kryo.register(CardSlotEntityUpdate.class);
		kryo.register(EntityDeployRequest.class);
		kryo.register(EntityRemoveUpdate.class);
		kryo.register(EntityUpdate.class);
		kryo.register(PlayerEntityUpdate.class);
		kryo.register(SelectionRequest.class);
		kryo.register(SelectionUpdate.class);
		kryo.register(String[].class);
		kryo.register(int[].class);
	}
	
	public NetworkManager()
	{
		connection_to_playerID = new HashMap <Connection,Integer>();
		playerID_to_connection = new HashMap <Integer,Connection>();
	}
	
	public void setPlayerManager(PlayerManager pm)
	{
		players = pm;
	}
	
	public PlayerManager playerManager()
	{
		return players;
	}
	
	public void startServer() throws IOException
	{
		server = new Server();
		Kryo kryo = server.getKryo();
		initialize(kryo);
		
		server.start();
		server.bind(54323);
		server.addListener(new ServerLoginListener());
		
		this.side = Side.Server;
	}
	
	public void startClient(String host) throws IOException
	{
		client = new Client();
		Kryo kryo = client.getKryo();
		initialize(kryo);
		
		client.start();
		client.connect(5000, host, 54323);
		client.addListener(new ClientLoginListener());
		
		this.side = Side.Client;
	}
	
	public void startHost() throws IOException
	{
		startServer();
		startClient("localhost");
		this.side = Side.Host;
	}
	
	// network handler jobs
	// ====================================================
	// server
	
	private void register(int id, Connection address)
	{
		connection_to_playerID.put(address,id);
		playerID_to_connection.put(id,address);
	}
	
	private void unregister(int id)
	{
		for(Connection address : connection_to_playerID.keySet())
		{
			if(connection_to_playerID.get(address).equals(id))
			{
				connection_to_playerID.remove(address);
			}
		}
		
		playerID_to_connection.remove(id);
	}
	
	public void sendUpdate(Entity ge, int id)
	{
		playerID_to_connection.get(id).sendTCP(EntityUpdate.getUpdate(ge));
	}
	
	public void broadcastUpdate(Entity ge)
	{
		for(Connection conn : connection_to_playerID.keySet())
		{
			conn.sendTCP(EntityUpdate.getUpdate(ge));
		}
	}
	
	public void sendDeploy(Entity ge, int id, float x, float y)
	{
		playerID_to_connection.get(id).sendTCP(new EntityDeployRequest(ge, x, y));
	}
	
	public void sendDeployClient(Entity ge, int id, float x, float y)
	{
		playerID_to_connection.get(id).sendTCP(new EntityDeployRequest(ge, x, y).setClient());
	}
	
	public void sendDeployUnviewable(Entity ge,int id)
	{
		playerID_to_connection.get(id).sendTCP(new EntityDeployRequest(ge,0,0).setUnviewable());
	}
	
	public void broadcastDeploy(Entity ge, int x, int y)
	{
		for(Connection conn : connection_to_playerID.keySet())
		{
			conn.sendTCP(new EntityDeployRequest(ge, x, y));
		}
	}
	
	public void sendSelectionRequest(SelectionRequest sr)
	{
		client.sendTCP(sr);
	}
	
	public void sendSelectionUpdate(int id, SelectionUpdate su)
	{
		playerID_to_connection.get(id).sendTCP(su);
	}
	
	
	//network handler jobs
	// ====================================================
	// client
	
	public void report(Object obj)
	{
		client.sendTCP(obj);
	}
	
	// listener
	// ====================================================
	// server
	
	public void register(INetworkHandler handler)
	{
		handlers.add(handler);
	}
	
	class ServerLoginListener extends Listener
	{
		@Override
		public void connected(Connection connection)
	    {
			NetworkPlayer np = new NetworkPlayer();
			players.login(np);
			register(np.playerID(), connection);
			Log.fine("player" + np.playerID() + " logged in");
			
			players.game.environment.deployLayout(players.game, np);
	    }
		
		@Override
		public void received(Connection connection, Object object)
		{
			Log.fine("received " + object);
				for(INetworkHandler handler : handlers)
				{
					if(handler.side() == Side.Server)
					if(handler.captured(object))
					{
						handler.handled(object);
						Object response = handler.handledWithResponse(object);
						if(response != null)connection.sendTCP(response);
					}
				}
		}
		
		@Override
		public void disconnected(Connection connection)
	    {
			int id = connection_to_playerID.get(connection);
			unregister(id);
			Log.fine(("player" + id + " logged out"));
	    }
	}
	
	class ClientLoginListener extends Listener
	{
		@Override
		public void received(Connection connection, Object object)
		{
			Log.fine("received " + object);
				for(INetworkHandler handler : handlers)
				{
					if(handler.side() == Side.Client)
					if(handler.captured(object))
					{
						handler.handled(object);
						Object response = handler.handledWithResponse(object);
						if(response != null)connection.sendTCP(response);
					}
				}
		}
	}
}
