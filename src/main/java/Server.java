import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {

    private int port;
    private MaxSpendings maxSpendings;

    public Server(int port, MaxSpendings maxSpendings) {
        this.port = port;
        this.maxSpendings = maxSpendings;
    }

    public void start() throws IOException, ParseException {

        System.out.println("Starting server at " + port + "...");
        try (
                ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    out.println("На что вы потратили деньги сегодня? (наименование дата(ГГГГ.ММ.ДД) сумма");
                    String answer = in.readLine();
                    JSONParser parser = new JSONParser();
                    JSONObject obj = (JSONObject) parser.parse(answer);
                    String item = (String) obj.get("title");
                    String sumString = (String) obj.get("sum");
                    int sum = Integer.parseInt(sumString);
                    maxSpendings.add(item, sum);
                    out.println(maxSpendings.pickMax());
                }
            }
        }
    }
}
