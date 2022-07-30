import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
  public static void main(String[] args) throws Exception {
    // 1. Make the HTTP connection and search the contents
    String url = "http://localhost:8080/languages";
    ContentExtractor extractor = new LanguagesContentExtrator();

    /*
     * String url =
     * "https://api.nasa.gov/planetary/apod?api_key=PxAAeVqwaGJcCy86ut8ENDOZHqUwOPCbUoPyT4El&start_date=2022-07-25&end_date=2022-07-29";
     * ContentExtractor extractor = new NASAContentExtractor();
     */

    /*
     * String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
     * ContentExtractor extractor = new IMDbContentExtractor();
     */

    ClientHttp clientHttp = new ClientHttp();
    String json = clientHttp.getData(url);

    // 2. Extract only the data that matters (title, url)
    List<Content> contents = extractor.extractContent(json);

    // 3. View and manipulate the data
    var generate = new StickerFactory();

    for (int i = 0; i < contents.size(); i++) {
      Content content = contents.get(i);

      InputStream inputStream = new URL(content.getImageUrl()).openStream();
      String fileName = content.getTitle() + " - Sticker.png";

      generate.createSticker(inputStream, fileName);

      System.out.println(content.getTitle());
      System.out.println(content.getImageUrl());
      System.out.println();
    }
  }
}
