import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    // 1. Make the HTTP connection and search the top 250 movies
    String url = "https://api.nasa.gov/planetary/apod?api_key=PxAAeVqwaGJcCy86ut8ENDOZHqUwOPCbUoPyT4El&start_date=2022-07-25&end_date=2022-07-29";

    ClientHttp clientHttp = new ClientHttp();
    String json = clientHttp.getData(url);

    // 2. Extract only the data that matters (title, poster, rating)
    var parser = new JsonParser();
    List<Map<String, String>> contentList = parser.parse(json);

    // 3. View and manipulate the data
    var generate = new StickerFactory();

    for (int i = 0; i < contentList.size(); i++) {
      Map<String, String> content = contentList.get(i);

      String contentTitle = content.get("title");
      String contentUrl = content.get("url");

      if (contentUrl.indexOf("https://www.youtube.com/") != -1) {
        continue;
      }

      InputStream inputStream = new URL(contentUrl).openStream();
      String fileName = contentTitle + " - Sticker.png";

      generate.createSticker(inputStream, fileName);

      System.out.println(contentTitle);
      System.out.println(contentUrl);
      System.out.println();
    }
  }
}
