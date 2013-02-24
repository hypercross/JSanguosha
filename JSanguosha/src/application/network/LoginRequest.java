package application.network;

public class LoginRequest {
	
	public String playerName;
	
	public static class LoginResponse
	{
		boolean successful;
		
		public LoginResponse(boolean successful)
		{
			this.successful = successful;
		}
		
		public boolean isOK()
		{
			return successful;
		}
	}
}
