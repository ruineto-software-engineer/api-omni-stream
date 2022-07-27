import java.net.http.HttpClient;

public class App {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    // 1. Make the HTTP connection and search the top 250 movies
    String url = "https://api.mocki.io/v2/549a5d8b";
    HttpClient client = HttpClient.newHttpClient();

    // 2. Extract only the data that matters (title, poster, rating)

    // 3. View and manipulate the data
  }
}
