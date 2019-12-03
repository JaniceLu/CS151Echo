package echo;

import java.net.Socket;

public class MathHandler extends RequestHandler {
    public MathHandler() { super(); }
    public MathHandler(Socket socket) { super(socket); }

    private String execute(String op, double[] args) {
        double result = 0.0;
        if(op.equalsIgnoreCase("add")) {
            for(int i = 0; i < args.length; i++) {
                result += args[i];
            }
        } else if (op.equalsIgnoreCase("mul")) {
            for(int i = 0; i < args.length; i++) {
                if(i == 0) result = args[i];
                else result *= args[i];
            }
        }
        return Double.toString(result);
    }

    @Override
    protected String response(String request) {
        String[] tokens = request.split("\\s+");
        double[] args = new double[tokens.length - 1];
        try {
            for(int i = 0; i < args.length; i++) {
                args[i] = Double.parseDouble(tokens[i+1]);
            }
        } catch(NumberFormatException e) {
            return "arguments must be numeric";
        }
        return execute(tokens[0], args);
     }
} 