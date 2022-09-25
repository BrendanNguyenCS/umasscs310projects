// Homework 2 Question 1

import java.util.ArrayList;
import java.util.List;

public class ListIterator {
    public static void main(String[] args) {
        printOneA();
        //printOneC();
    }

    public static void printOneA() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        System.out.println(list1);

        System.out.println("----------------- Question 1a -----------------");
        System.out.println(list1.iterator().next());
        System.out.println(list1.listIterator().next());
        System.out.println(list1.listIterator(2).next());
        System.out.println(list1.listIterator(4).previous());
    }

    // Currently broken
    public static void printOneC() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        System.out.println(list1);

        System.out.println("----------------- Question 1c -----------------");
        list1.listIterator(2).next();
        list1.listIterator(2).remove();
        list1.listIterator(4).previous();
    }
}
