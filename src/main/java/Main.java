import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
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

        MaxSpendings maxSpendings = new MaxSpendings();

        Server server = new Server(8989, maxSpendings);
        server.start();
    }
}
