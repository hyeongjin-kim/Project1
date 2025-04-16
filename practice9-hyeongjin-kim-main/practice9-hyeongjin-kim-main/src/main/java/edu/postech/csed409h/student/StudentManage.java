package edu.postech.csed409h.student;

import java.util.Optional;

public class StudentManage {
    public static void main(String[] args) {
        Student student = new Student("John Doe", null);
        System.out.println(Optional.ofNullable(student)
        .map(Student::getName)
        .map(name -> {
            return "\"Student Name: \" + name";
        }).orElse("Student Name not available.")
        );
        System.out.println(Optional.ofNullable(student)
                .map(Student::getAddress)
                .map(address -> {
                    Optional.of(address.getCity())
                            .map(city->{return address.getCity();})
                            .orElse("City no available");
                    String city = address.getCity();
                    String state = address.getState();
                    return "City: " + city + "\nState: " + state;
                }).orElse("Address not available.");
        };

        // Hint (How to use map)
        // Optional.ofNullable(student)
        //    .map(Student::getAddress)
        //    .map(address -> {
        //        String city = address.getCity();
        //        String state = address.getState();
        //        return "City: " + city + "\nState: " + state;
        //    })
        //    .orElse("Address not available.")
        //    .ifPresent(System.out::println);

}
