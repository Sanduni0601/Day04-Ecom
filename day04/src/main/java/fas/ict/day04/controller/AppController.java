package fas.ict.day04.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fas.ict.day04.model.Student;

    @RestController
    @Controller
    @RequestMapping("/app")
    public class AppController {
        Student s1 = new Student("Ict01","John",23,"ICT",3.7);
    Student s2 = new Student("Ict02","Alice",25,"ICT",3.2);
    Student s3 = new Student("Ict03","Bob",24,"CS",2.9);
    Student s4 = new Student("Ict04","Johnny",23,"ICT",3.5);
    
    private static List<Student> students = new ArrayList<Student>();
    private Map<String, Student> mstudents = new HashMap<String,Student>(); 
    
    public AppController() {
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        
        mstudents.put(s1.getRegNo(),s1);
        mstudents.put(s2.getRegNo(),s2);
        mstudents.put(s3.getRegNo(),s3);
        mstudents.put(s4.getRegNo(),s4);
    
    }
    @GetMapping("/student")
    public Student getStudent() {
        return s1;
    }
    
    //Display all students
    @GetMapping("/students")
    public Map<String, Student> getStudents(){
        return mstudents;
    }
    //List a student from the list 
    @GetMapping("/student/{id}")
        public Student getStudent(@PathVariable("id") String regno) {
        return mstudents.get(regno);
        }
    //Add a new student
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        mstudents.put(student.getRegNo(), student);
        return "New student added";
    }
    
    //Delete a student
    @DeleteMapping("/student/{id}")
    public String DeleteStudent(@PathVariable("id") String regno) {
        if(mstudents.get(regno)!= null) {
            mstudents.remove(regno);
            return "The student removed";
        }
        return "404 Couldn't find the student";
    }
    
    
    
    //Update the student
    @PutMapping("/student/{id}")
    public String updateStudent(@PathVariable("id") String regno,@RequestBody Student student) {
        if(mstudents.get(regno)!=null) {
            mstudents.put(student.getRegNo(), student);
            return "The student details are updated";
        }
        return "404 Couldn't find the student";
        
    }
    
}
