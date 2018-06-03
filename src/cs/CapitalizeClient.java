package cs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frames.Login;

public class CapitalizeClient {

    private BufferedReader in;
    private PrintWriter out;
    private JFrame frame = new JFrame("Capitalize Client");
    private JTextField dataField = new JTextField(40);
    private JTextArea messageArea = new JTextArea(8, 60);
    private static final CapitalizeClient CAPITALIZE_CLIENT_INSTANCE = new CapitalizeClient(); 
    private Socket socket = null;
    
    private CapitalizeClient() {
    	showClientAddress();
    	try {
			connectToServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	frame.pack();
    	frame.setVisible(true);
    }
    
    public static CapitalizeClient getInstance() {
    	return CAPITALIZE_CLIENT_INSTANCE;
    }
    
    
    public void showClientAddress() {

        // Layout GUI

        messageArea.setEditable(false);
        frame.getContentPane().add(dataField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");

        // Add Listeners
        dataField.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                out.println(dataField.getText());
                   String response;
                try {
                    response = in.readLine();
                    if (response == null || response.equals("")) {
                          System.exit(0);
                      }
                } catch (IOException ex) {
                       response = "Error: " + ex;
                }
                messageArea.append(response + "\n");
                dataField.selectAll();
            }
        });
        
        
    }
    
    public String sendLoginToServer(String u, String pass) throws Exception
    {
    	in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    	System.out.println("m1");
    	out.write("login");
    	out.write(u);
    	out.write(pass);
    	System.out.println("Done writing...");
    	try {
    		//wait(1500);
			String message = in.readLine();
			return message;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "Connection lost!";
		}
    	
    }
    
    public void connectToServer() throws IOException {

        // Get the server address from a dialog box.
        String serverAddress = JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Capitalization Program",
            JOptionPane.QUESTION_MESSAGE);

        // Make connection and initialize streams
        socket = new Socket(serverAddress, 9898);
        

    }

    /**
     * Runs the client application.
     */
   
}