import java.util.*;

class Movie implements Comparable<Movie> {
    int id;
    String name;
    double rating;

    Movie(int id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    @Override
    public int compareTo(Movie o) {

        if (this.rating != o.rating) {
            return Double.compare(o.rating, this.rating);
        }
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + rating;
    }
}

public class Sorting4{
    public static void main(String[] args) {

        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie(101, "Avatar", 8.5));
        movies.add(new Movie(102, "Interstellar", 9.0));
        movies.add(new Movie(103, "Titanic", 8.0));
        movies.add(new Movie(104, "Inception", 9.0));

        movies.sort(null);

        for (Movie m : movies) {
            System.out.println(m);
        }
    }
}