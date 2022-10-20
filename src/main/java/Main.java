import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static String url = "https://tengrinews.kz/";

    public static void main(String[] args) throws IOException {
        WebPage webPage = new WebPage(url);
        Set<WebPage> resultSet = new ForkJoinPool().invoke(new MapSite(webPage));
        for (WebPage w : resultSet) {
            String name = "\n" + slashToTab(w.getName());
            Files.write(Paths.get("src/main/resources/mapsite.txt"), name.getBytes());
        }
    }

    public static String slashToTab(String str) {
        String tab = "";
        char slash = '/';
        int slashCount = 0;
        for (char element : str.toCharArray()) {
            if (element == slash) {
                slashCount++;
                if (slashCount > 3) {
                    tab = tab.concat("\t");
                }
            }
        }
        return tab.concat(str);
    }
}