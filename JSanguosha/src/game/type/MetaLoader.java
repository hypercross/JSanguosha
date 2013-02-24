package game.type;


import hx.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MetaLoader {

	public static void loadAll()
	{
		Log.fine("loading type metadata...");
		for(FileHandle fh:Gdx.files.local("../JSanguosha/typemeta").list())
			loadMeta(fh);
		Log.fine("loading complete.");
	}

	public static void loadMeta(FileHandle fh)
	{
		TextureRegion cover = getTexture(fh,"cover");
		TextureRegion banner = getTexture(fh,"banner");
		TextureRegion icon = getTexture(fh,"icon");

		try{
			String[] names = parse(fh.child("type.ini"));
			TypeMeta tm = new TypeMeta(names[1],names[2],cover,banner,icon);
			TypeMeta.setTypeMeta(Type.fromString(names[0]), tm);

			Log.trivial("loaded type: " + Log.list(names));
		}catch(Exception e)
		{
			Log.alert("Malformat:");
			e.printStackTrace();
		}
	}

	private static TextureRegion getTexture(FileHandle fh, String name)
	{
		try{
			return new TextureRegion(new Texture(findTexture(fh,name)));
		}catch(Exception e)
		{
			Log.trivial(e.getMessage());
		}

		return null;
	}

	private static boolean isImg(String file)
	{
		return file.endsWith(".jpg") || file.endsWith(".png") || file.endsWith(".gif");
	}

	private static FileHandle findTexture(FileHandle fh,String name) throws Exception
	{
		for(FileHandle afile: fh.list())
			if(afile.name().startsWith(name) && isImg(afile.name()))return afile;

		throw new Exception("File not found: " + name);
	}

	private static String[] parse(FileHandle fh) throws IOException
	{
		String[] names = new String[3];

		BufferedReader br = new BufferedReader(new FileReader(fh.file()));

		Pattern ptn = Pattern.compile("\\[(.+)\\](.+)");
		while(true)
		{
			String aline = br.readLine();
			if(aline == null)break;
			Matcher matcher = ptn.matcher(aline);
			if(!matcher.matches())
			{
				break;
			}
			if(matcher.group(1).equals("Type"))names[0] = matcher.group(2);
			if(matcher.group(1).equals("Desc"))names[2] = matcher.group(2);
			if(matcher.group(1).equals("Name"))names[1] = matcher.group(2);

		}
		
		br.close();

		return names;
	}
}
