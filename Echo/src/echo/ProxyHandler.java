package echo;

import java.net.Socket;

public class ProxyHandler extends RequestHandler {
    protected Correspondent peer;

    public ProxyHandler() { super(); }

    public ProxyHandler(Socket socket) { super(socket); }

    public void initPeer(String host, int port) {
        peer = new Correspondent();
        peer.requestConnection(host, port);
    }
}