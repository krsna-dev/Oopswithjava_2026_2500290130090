public class UseCase2 {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(4.0, 6.0);

        for (Shape s : shapes) {
            s.displayArea();   
        }
    }
}

abstract class Shape {
    abstract double area();
    void displayArea() {
        System.out.println("Area is: " + area());
    }
}

class Circle extends Shape {
    double radius;
    Circle(double r) {
        this.radius = r;
    }
    double area() {
        return 3.14 * radius * radius;
    }
}

class Rectangle extends Shape {
    double length;
    double breadth;
    Rectangle(double l, double b) {
        this.length = l;
        this.breadth = b;
    }
    double area() {
        return length * breadth;
    }
}