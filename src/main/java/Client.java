import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Client {

    public static void main(String[] args) throws IOException, ParseException {
        String host = "127.0.0.1";
        int port = 8989;
        Scanner sc = new Scanner(System.in);
        String input;

        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            System.out.println(in.readLine());

            input = sc.nextLine();
            String[] spending = input.split(" ");
            String title = spending[0];
            String date = spending[1];
            String sum = spending[2];
            JSONObject answer = new JSONObject();
            answer.put("title", title);
            answer.put("date", date);
            answer.put("sum", sum);
            out.println(answer.toJSONString());
            System.out.println(in.readLine());
        }
    }
}
