public class MockMovie {
  public String id;
  public String rank;
  public String title;
  public String fullTitle;
  public String year;
  public String image;
  public String crew;
  public String imDbRating;
  public String imDbRatingCount;

  MockMovie(String id, String rank, String title, String fullTitle, String year, String image,
      String crew,
      String imDbRating, String imDbRatingCount) {
    this.id = id;
    this.rank = rank;
    this.title = title;
    this.fullTitle = fullTitle;
    this.year = year;
    this.image = image;
    this.crew = crew;
    this.imDbRating = imDbRating;
    this.imDbRatingCount = imDbRatingCount;
  }
}
