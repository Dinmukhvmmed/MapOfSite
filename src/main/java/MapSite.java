import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;

import static java.lang.Thread.sleep;

public class MapSite extends RecursiveTask<Set<WebPage>> implements Comparable<MapSite> {

    private WebPage webPage;
    private static Set<WebPage> childrenSet = new TreeSet<>();

    public MapSite(WebPage webPage) {
        this.webPage = webPage;
    }

    @Override
    protected Set<WebPage> compute() {
        try {
            sleep(150);
            Connection connection = Jsoup.connect(webPage.getName()).timeout(10000);
            Document page = connection.get();
            Elements elements = page.select("body").select("a");

            for (Element element : elements) {
                String childUrl = element.absUrl("href");
                WebPage web = new WebPage(childUrl);
                checkAndAddToSet(web);
            }

            webPage.setChildren(childrenSet);
            Set<MapSite> taskSet = new TreeSet<>();

            for (WebPage exampleWebPage : webPage.getChildren()) {
                MapSite task = new MapSite(exampleWebPage);
                task.fork();
                taskSet.add(task);
            }

            for (MapSite task : taskSet) {
                task.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return childrenSet;
    }

    public void checkAndAddToSet(WebPage exampleWebPage) {
        if (isCorrectAndUniqueUrl(exampleWebPage)) {
            childrenSet.add(exampleWebPage);
        }
    }

    public boolean isCorrectAndUniqueUrl(WebPage exampleWebPage) {
        boolean isTrueDomain = exampleWebPage.getName().contains(Main.url);
        boolean isNotInnerElement = !exampleWebPage.getName().contains("#");
        boolean isWebPage = !exampleWebPage.getName().endsWith(".pdf");
        boolean isUniqueUrl = !childrenSet.contains(exampleWebPage);

        if (isTrueDomain && isUniqueUrl && isNotInnerElement && isWebPage) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(MapSite o) {
        return this.webPage.getName().compareTo(o.webPage.getName());
    }
}
