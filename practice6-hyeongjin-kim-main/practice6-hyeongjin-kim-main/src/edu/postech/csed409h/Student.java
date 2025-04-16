package edu.postech.csed409h;

import java.util.Comparator;

public class Student {
    private String name;
    private int korean;
    private int english;
    private int math;

    public Student(String name, int korean, int english, int math){
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    int getTotal(){
        return korean + english + math;
    }

    float getAverage(){
        return (int) ((getTotal() / 3f) * 10 + 0.5) / 10f;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + ", Korean: " + this.korean + ", English" + this.english + ", Math: " + this.math;
    }
}
