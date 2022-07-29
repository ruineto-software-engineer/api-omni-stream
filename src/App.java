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
    String url = "https://api.mocki.io/v2/549a5d8b";
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
    String[] mockMoviesTitles = {
        "The Shawshank Redemption",
        "The Godfather",
        "The Dark Knight",
        "The Godfather: Part II",
        "12 Angry Men",
        "Schindler's List",
        "The Lord of the Rings: The Return of the King",
        "Pulp Fiction",
        "The Lord of the Rings: The Fellowship of the Ring",
        "The Good, the Bad and the Ugly"
    };
    String[] mockMovieImageUrls = {
        "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
        "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_FMjpg_UX1000_.jpg",
        "https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_FMjpg_UX1000_.jpg",
    };

    MockMovieIdentifier[] mockMovieIdentifierList;
    mockMovieIdentifierList = new MockMovieIdentifier[mockMoviesTitles.length];
    for (int i = 0; i < mockMoviesTitles.length; i++) {
      String mockMovieTitle = mockMoviesTitles[i];
      String mockMovieImageUrl = mockMovieImageUrls[i];

      mockMovieIdentifierList[i] = new MockMovieIdentifier(mockMovieTitle, mockMovieImageUrl);
    }

    MockMovie[] mockMovieList;
    mockMovieList = new MockMovie[movieList.size()];
    for (int i = 0; i < mockMovieIdentifierList.length; i++) {
      String mockMovieTitle = mockMovieIdentifierList[i].title;
      String mockMovieImage = mockMovieIdentifierList[i].image;

      for (Map<String, String> movie : movieList) {
        String movieTitle = movie.get("title");

        if (movieTitle.indexOf(mockMovieTitle) >= 0 && mockMovieTitle.indexOf(movieTitle) >= 0) {
          mockMovieList[i] = new MockMovie(movie.get("id"), movie.get("rank"),
              movieTitle,
              movie.get("fullTitle"), movie.get("year"), mockMovieImage, movie.get("crew"),
              movie.get("imDbRating"),
              movie.get("imDbRatingCount"));
        }
      }
    }

    for (int i = 0; i < mockMovieList.length; i++) {
      String mockMovieTitle = mockMovieList[i].title;
      String mockMovieImage = mockMovieList[i].image;

      InputStream inputStream = new URL(mockMovieImage).openStream();
      String fileName = mockMovieTitle + " - Sticker.png";

      generate.createSticker(inputStream, fileName);

      System.out.println(mockMovieTitle);
      System.out.println();
    }
  }
}
