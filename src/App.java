import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    // 1. Make the HTTP connection and search the top 250 movies
    String url = "https://api.nasa.gov/planetary/apod?api_key=PxAAeVqwaGJcCy86ut8ENDOZHqUwOPCbUoPyT4El&start_date=2022-07-25&end_date=2022-07-29";
    URI uri = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(uri).GET().build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();

    // 2. Extract only the data that matters (title, poster, rating)
    var parser = new JsonParser();
    List<Map<String, String>> movieList = parser.parse(body);

    // 3. View and manipulate the data
    var generate = new StickerFactory();

    for (int i = 0; i < movieList.size(); i++) {
      Map<String, String> movie = movieList.get(i);

      String movieTitle = movie.get("title");
      String movieUrl = movie.get("url");

      if (movieUrl.indexOf("https://www.youtube.com/") != -1) {
        continue;
      }

      InputStream inputStream = new URL(movieUrl).openStream();
      String fileName = movieTitle + " - Sticker.png";

      generate.createSticker(inputStream, fileName);

      System.out.println(movieTitle);
      System.out.println(movieUrl);
      System.out.println();
    }
  }
}
