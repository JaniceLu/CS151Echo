package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

    protected ServerSocket mySocket;
    protected int myPort;
    public static boolean DEBUG = true;
    protected Class<?> handlerType;

    public Server(int port, String handlerType) {
        try {
            myPort = port;
            mySocket = new ServerSocket(myPort);
            this.handlerType = (Class.forName(handlerType));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } // catch
    }

    public void listen() {
        while (true) {
            try {
                Socket socket = mySocket.accept();
                makeHandler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }

			// accept a connection CHECK
			// make handler CHECL
			// start handler
		} // while
	}

	public RequestHandler makeHandler(Socket s) {
		// handler = a new instance of handlerType
		// set handler's socket to s
		// RequestHandler handler = null;
        return null;
    }
    



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}
