import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    // 1. Make the HTTP connection and search the top 250 movies
    String url = "https://api.mocki.io/v2/549a5d8b";
    URI uri = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(uri).GET().build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();

    // 2. Extract only the data that matters (title, poster, rating)
    var parser = new JsonParser();
    List<Map<String, String>> movieList = parser.parse(body);

    System.out.println(movieList.size());
    System.out.println(movieList.get(0));

    // 3. View and manipulate the data
  }
}
