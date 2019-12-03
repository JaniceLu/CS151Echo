package echo;

import java.net.Socket;

public class ProxyServer extends Server {
    private String peerHost;
    private int peerPort;

    public ProxyServer(int port, int peerPort, String peerHost, String host, String handlerType) {
        super(port, handlerType);
        this.peerPort = peerPort;
        this.peerHost = peerHost;
    }

    public RequestHandler makeHandler(Socket s) {
        RequestHandler handler = super.makeHandler(s);
        try {
            ((ProxyHandler) handler).initPeer(peerHost, peerPort);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
		// handler = a new instance of handlerType
		// set handler's socket to s
		// return handler
        return handler;
    }

    public static void main(String[] args) {
        String host = "111";
        String pHost = "111";
        int port = 0;
        int pPort = 0;
        String service = "echo.placeholder";

        if ( 6 == args.length) { 
            service = args[1];
            pPort = Integer.parseInt(args[2]);
            port = Integer.parseInt(args[3]);
            pHost = args[4];
            host = args[5];
        } else {
            System.err.println("Not enough arguments");
        }
        Server server = new ProxyServer(port, pPort, pHost, host, service);
        server.listen();
    }

}