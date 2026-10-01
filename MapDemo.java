import java.util.*;
public class MapDemo{
    public static void main(String[] args){
        Map<Integer,Integer>hm= new HashMap<>();
        hm.put(10,96);
        hm.put(20,66);
        hm.put(30,76);
        hm.put(50,93);
        hm.put(40,86);
        for(Map.Entry<Integer, Integer> i:hm.entrySet()){
            System.out.println(i.getKey()+ " "+ i.getValue());
        }
        if(hm.containsKey(20)){
            System.out.println(hm.get(20));
        }
        else{
            System.out.println("Key not found");
        }
        hm.put(10,89);
        hm.remove(10);
    }
}