package echo;

import java.net.Socket;

public class CacheHandler extends ProxyHandler {
    private static Cache<String,String> cache = new Cache<String, String>();

    public CacheHandler(Socket socket) { super(socket); }
    public CacheHandler() { super(); }

    @Override
    protected String response(String request) {
        String cachedResult = cache.search(request);
        String response = null;
        if (cachedResult == null) { 
            System.out.println("Request not found in cache.");
            peer.send(request); 
            response = peer.receive();
            System.out.println("This response stored in cache: " + response);
            cache.update(request, response);
        }
        else { 
            System.out.println("Request found in cache: " + cachedResult);
            response = cachedResult;
        }
        return response;
    }
}