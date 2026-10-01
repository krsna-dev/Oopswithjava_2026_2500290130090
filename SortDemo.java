import java.util.*;
class Student implements Comparable<Student>{
    String name;
    int rollno;
    int marks;
    
    Student(String n, int r, int m){
        name = n;
        rollno = r;
        marks = m;
    }
    @Override
    public int compareTo(Student o){
        // Ascending Order // Descending Orderke liye return type = return o.rollno - this.rollno
        return this.rollno - o.rollno;
    }
    @Override 
    public String toString(){
        return rollno + " " + name + " " + marks;
    }
}
class CustomComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2){
        if(o1.marks != o2.marks){
            return o2.marks-o1.marks;
        }
        else return o1.rollno - o2.rollno;

    }
}
    class NameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);
    }
}

public class SortDemo {
    public static void main(String[] args) {
        ArrayList<Integer> i = new ArrayList<>();
        i.add(23);
        i.add(12);
        i.add(14);
        i.add(15);
        i.add(16);
        i.sort(null);// ascending
    System.out.println(i);
        i.sort(Collections.reverseOrder()); //comparator for descending order
    System.out.println(i);
        ArrayList<Student> st = new ArrayList<>();
        st.add(new Student("rahul" , 1, 100));
        st.add(new Student("Nitesh", 10, 20));
        st.add(new Student("Neetesh", 7, 80));
        st.add(new Student("Nilesh", 9, 70));
        st.add(new Student("Mitesh", 20, 80));
        st.sort(null);
        st.sort(new CustomComparator());
        System.out.println(st);
        st.sort(new NameComparator());
        System.out.println(st);

    }
}