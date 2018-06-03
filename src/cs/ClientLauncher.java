package cs;

import frames.Login;

public class ClientLauncher{
	
	public static void main(String[] args) {
		CapitalizeClient client = CapitalizeClient.getInstance();
		
	    Login l = new Login();
	    l.setVisible(true);
	   /* client.frame.setVisible(false);
	    client.connectToServer();*/
	}
}