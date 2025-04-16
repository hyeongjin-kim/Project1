package edu.postech.csed409h;

import java.util.*;

public class CollectionPractice {

    public static void main(String[] args){

        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();

        ArrayList<Integer> intersection = new ArrayList<Integer>();
        ArrayList<Integer> union = new ArrayList<Integer>();
        ArrayList<Integer> difference = new ArrayList<Integer>();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list2.add(7);

        //TODO: Implement your code
        for(Integer integer: list2){
            if(list1.contains(integer)){
                intersection.add(integer);
            }
        }
        union.addAll(list1);
        for(Integer integer: list2){
            if(!union.contains(integer)){
                union.add(integer);
            }
        }

        difference.addAll(list1);
        for(Integer integer: list2){
            if(difference.contains(integer)){
                difference.remove(integer);
            }
        }


        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println("intersection = " + intersection);
        System.out.println("union = " + union);
        System.out.println("difference = " + difference);

        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        studentArrayList.add(new Student("KimJava", 100, 100, 100));
        studentArrayList.add(new Student("LeeJava", 90, 100, 80));
        studentArrayList.add(new Student("ParkJava", 80, 90, 90));
        studentArrayList.add(new Student("ChaeJava", 70, 90, 100));
        studentArrayList.add(new Student("AhnJava", 100, 90, 90));
        studentArrayList.add(new Student("YunJava", 100, 90, 80));

        studentArrayList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //TODO: Implement this code!
                int score1 = o1.getTotal();
                int score2 = o2.getTotal();
                return Integer.compare(score1, score2);
            }
        });
        for (Student student : studentArrayList) {
            System.out.println(student);
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(7);

        HashSet<Integer> set = new HashSet<Integer>(list);
        TreeSet<Integer> test = new TreeSet<Integer>(set);
        Stack<Integer> stack = new Stack<Integer>();
        stack.addAll(test);

        while(!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}