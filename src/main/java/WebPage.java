import java.util.Objects;
import java.util.Set;

public class WebPage implements Comparable<WebPage> {
    private String name;
    private Set<WebPage> children;

    public WebPage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WebPage> getChildren() {
        return children;
    }

    public void setChildren(Set<WebPage> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPage webPage = (WebPage) o;
        return Objects.equals(name, webPage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(WebPage o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "name='" + name + '\'' +
                '}';
    }
}