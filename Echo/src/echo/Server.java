package echo;

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
        System.out.println("server address: " + 
            mySocket.getInetAddress());
        try { 
            while (true) {
                System.out.println("Server listening at port " + myPort);
                Socket socket = mySocket.accept(); //accept a connection
                RequestHandler handler = makeHandler(socket); //create a connection
                if(handler == null) continue;
                Thread slave = new Thread(handler);
                slave.start(); // start handler
            } // while
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public RequestHandler makeHandler(Socket s) {
        RequestHandler handler = null;
        try {
            handler = (RequestHandler) handlerType.newInstance();
            handler.setSocket(s);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
		// handler = a new instance of handlerType
		// set handler's socket to s
		// return handler
        return handler;
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
