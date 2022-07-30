import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LanguagesContentExtrator implements ContentExtractor {
  public List<Content> extractContent(String json) {
    // Extract only the data that matters (title, poster, rating)
    var parser = new JsonParser();
    List<Map<String, String>> attributeList = parser.parse(json);

    List<Content> contents = new ArrayList<>();

    // Popular the content list
    for (Map<String, String> attribute : attributeList) {
      String attributeTitle = attribute.get("title");
      String attributeUrl = attribute.get("image");

      var content = new Content(attributeTitle, attributeUrl);

      contents.add(content);
    }

    return contents;
  }
}
