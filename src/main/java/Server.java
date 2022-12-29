import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server implements Serializable {
    public static void main(String[] args) throws IOException, ParseException {

        File categories = new File("categories.tsv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(categories))) {
            writer.write("булка"+"\t"+"еда\n");
            writer.write("колбаса"+"\t"+"еда\n");
            writer.write("сухарики"+"\t"+"еда\n");
            writer.write("курица"+"\t"+"еда\n");
            writer.write("тапки"+"\t"+"одежда\n");
            writer.write("шапка"+"\t"+"одежда\n");
            writer.write("мыло"+"\t"+"одежда\n");
            writer.write("акции"+"\t"+"одежда\n");
        }

        File test = new File("test.tsv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(test))) {
            writer.write("стол"+"\t"+"мебель\n");
            writer.write("часы"+"\t"+"аксессуар\n");
        }

        System.out.println(MaxSpendings.readFile(test));

        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            MaxSpendings maxSpendings = new MaxSpendings();
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
