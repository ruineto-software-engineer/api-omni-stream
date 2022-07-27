import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
  public static void main(String[] args) throws Exception {
    // 1. Make the HTTP connection and search the top 250 movies
    String url = "https://api.mocki.io/v2/549a5d8b";
    URI uri = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(uri).GET().build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();

    System.out.println(body);

    // 2. Extract only the data that matters (title, poster, rating)

    // 3. View and manipulate the data
  }
}
