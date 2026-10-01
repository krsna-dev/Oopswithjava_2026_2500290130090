import java.util.*;

class Product {
    int id;
    String name;
    int price;

    Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " " + price;
    }
}

class PriceComparator implements Comparator<Product> {
    public int compare(Product o1, Product o2) {

        // Price: highest to lowest
        if (o1.price != o2.price) {
            return o2.price - o1.price;
        }

        // Name: alphabetical A to Z
        return o1.name.compareTo(o2.name);
    }
}

public class sorting2 {
    public static void main(String[] args) {

        ArrayList<Product> p = new ArrayList<>();
        p.add(new Product(1, "Laptop", 1000));
        p.add(new Product(2, "Mobile", 60000));     
        p.add(new Product(3, "Tablet", 30000));
        p.add(new Product(4, "Mouse", 00));

        p.sort(new PriceComparator());

        for (Product x : p) {
            System.out.println(x);
        }
    }
}