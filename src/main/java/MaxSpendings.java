import org.json.simple.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxSpendings {

    private Map <String, Integer> finalSet = new HashMap<>();
    static File file = new File("categories.tsv");

    private static Map<String, String> categories = readFile(file);

    public MaxSpendings() {
        this.finalSet = finalSet;
    }

    {
        for (String category : categories.values()) {
            finalSet.put(category, 0);
        }
    }

    public void add(String item, int sum) throws IOException {

        if (categories.containsKey(item)) {
            String category = categories.get(item);
            int i = finalSet.get(category);
            finalSet.put(category, i + sum);
        } else {
            finalSet.put("другое", finalSet.get("другое") + sum);
        }
    }

    public String pickMax() {
        int max = 0;
        String maxCategory = null;
        for (Map.Entry<String, Integer> category : this.finalSet.entrySet()) {
            if (category.getValue() > max) {
                max = category.getValue();
                maxCategory = category.getKey();
            }
        }
        JSONObject maxSpendings = new JSONObject();
        maxSpendings.put("Max category", maxCategory);
        maxSpendings.put("sum", max);
        return maxSpendings.toJSONString();
    }

    public static Map <String, String> readFile(File file) {
        Map<String, String> readingCategories = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String s;
            while ((s = reader.readLine()) != null) {
                readingCategories.put(s.split("\t")[0], s.split("\t")[1]);
            }
            readingCategories.put("другое", "другое");
        } catch (IOException e) {
            e.getMessage();
        }
        return readingCategories;
    }

//    public static Map <String, String> readFile(File file) {
//        Map<String, String> readingCategories = new HashMap<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String s;
//            while ((s = reader.readLine()) != null) {
//                readingCategories.put(s.split("\t")[0], s.split("\t")[1]);
//            }
//            readingCategories.put("другое", "другое");
//        } catch (IOException e) {
//            e.getMessage();
//        }
//        return readingCategories;
//    }

    @Override
    public String toString() {
        return finalSet.toString();
    }
}
