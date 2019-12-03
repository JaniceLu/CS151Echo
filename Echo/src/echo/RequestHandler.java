package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	public RequestHandler(Socket s) {
		super(s);
	}

	public RequestHandler() {
		
	}

	// override in a subclass:
	protected String response(String msg) {
		return "echo: " + msg;
	}

	public void run() {
		try {
			while (true) {
				String print = receive();
				System.out.println("received: " + print);
				if(print.equals("quit")) break;
				print = response(print);
				send(print);
				Thread.sleep(300);
			} 
			close();
			System.out.println("Request handler shutting down");
			// receive request
			// send response
			// sleep
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		// close
	}
}

