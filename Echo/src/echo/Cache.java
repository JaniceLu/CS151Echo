package echo;

import java.util.HashMap;

public class Cache<K,V> extends HashMap<String, String>{

    public Cache() { }
    
    public String search(String request) {
        return this.get(request);
    }

    public synchronized void update(String request, String response) {
        synchronized(this) {
            this.put(request, response);
        }
    }
}