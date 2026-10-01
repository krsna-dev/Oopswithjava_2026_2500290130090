import java.util.*;
class Book {
    int id;
    String name;
    int page;

    public Book(int id, String name, int page) {
        this.id = id;
        this.name = name;
        this.page = page;
    }
    @Override
    public String toString() {
        return id + " " + name + " " + page;
    }
}


class Pagecomparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.page - o2.page;
    }
}



public class Sorting3 {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(101, "Java Basics", 150));
        books.add(new Book(102, "Java OOPS", 200));
        books.add(new Book(103, "Java Advance", 250));
        books.add(new Book(104, "Java Collections", 300));

        books.sort(new Pagecomparator());
        
}
}
