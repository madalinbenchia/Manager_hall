package cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import frames.Login;

public class CapitalizeServer {


    public static void main(String[] args) throws Exception {
        System.out.println("The capitalization server is running.");
        System.out.println("Default charset is: " + Charset.defaultCharset());
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            clientNumber += 1;
            final Socket socket = listener.accept();
            final Capitalizer capitalizer = new Capitalizer(socket, clientNumber);

            capitalizer.run();
            capitalizer.join();
            System.out.println("Thread finished running");

        } catch (final Exception someException) {
            System.err.println("Something went wrong!");
            someException.printStackTrace();
            listener.close();
        }
    }

    private static class Capitalizer extends Thread {
        private Socket socket;
        private int clientNumber;

        Capitalizer(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.flush();
                while (true) {
                    System.out.println("Reading line...");
                    System.out.println(socket.getPort());
                    String input = in.readLine();
                    System.out.println(input);
                    switch (input) {
                        case "login":
                            String u = in.readLine();
                            String p = in.readLine();

                            System.out.println("Username is: " + u + " Password is: " + p);
                            String message = Login.serverLogin(u, p);
                            out.println(message);
                    }
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    //out.println(input.toUpperCase());
                }
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        private void log(String message) {
            System.out.println(message);
        }
    }
}