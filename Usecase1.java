public class Usecase1 {
    public static void main(String[] args){
        Book b1 = new Book("Java Programming","John Doe",29.99,"1234567890");
        Book b2 = new Book("Python Programming","Jane Smith",39.99,"0987654321");
        System.out.println("Book 1 Title: " + b1.getTitle());
        System.out.println("Book 1 Author: " + b1.getAuthor());
        System.out.println("Book 1 Price: $" + b1.getPrice());
        System.out.println("Book 1 ISBN: " + b1.getIsbn());
        System.out.println("Book 2 Title: " + b2.getTitle());
        System.out.println("Book 2 Author: " + b2.getAuthor());
        System.out.println("Book 2 Price: $" + b2.getPrice());
        System.out.println("Book 2 ISBN: " + b2.getIsbn());
        System.out.println("Total number of books: " + Book.bookcount);
    }
}
class Book{
    private String title;
    private String author;
    private double price;
    private String isbn;
    final static String libraryname ="Sunrise";
    static int bookcount =0;
    Book(String t,String a, double p,String i){
        this.title =t;
        this.author= a;
        this.price = p;
        this.isbn =i;
        ++bookcount;

    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String t){
        this.title = t;
       
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String a){
        this.author = a;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double p){
        this.price = p;
    }
    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String i){
        this.isbn = i;
    }
}
