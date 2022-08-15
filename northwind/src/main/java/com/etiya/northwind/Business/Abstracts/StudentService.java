package com.etiya.northwind.Business.Abstracts;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    List<Student> students = new ArrayList<>();

    public StudentService(){
        students.add(new Student(1,"Ali"));
        students.add(new Student(2,"Veli"));
        students.add(new Student(3,"Sıddık"));
        students.add(new Student(4,"Ercü"));
        students.add(new Student(5,"Tezcan"));
        students.add(new Student(6,"Kamil"));
    }
}


//Yeni öğrenci eklenebilmelidir.