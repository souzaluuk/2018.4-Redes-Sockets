
import java.io.FileNotFoundException;
import pages.PageHTML;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new PageHTML("GET /result.html?num1=1&num2=2&num3=3 HTTP/1.1").getPageHTML());
    }
}
