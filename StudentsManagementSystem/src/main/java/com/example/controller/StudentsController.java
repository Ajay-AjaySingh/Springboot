package com.example.controller;

import com.example.entity.Students;
import com.example.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
//@RequestMapping("/api/students")
@Controller
public class StudentsController {

    @Autowired
    StudentsService studentsService;

    @PostMapping
    public Students createStudent(@RequestBody Students students){
       return studentsService.addStudent(students);
    }

//    @GetMapping
//    public List<Students> getAllStudents(){
//        return studentsService.getAllStudents();
//    }

    @GetMapping("/")
    public String home(){
        return "st";
    }

    @GetMapping
    public String getAllStudents(Model model){
        List<Students> studentsList=studentsService.getAllStudents();
        model.addAttribute("students",studentsList);
        return "stt";
    }

    @GetMapping("{id}")
    public ResponseEntity<Students> getStudentsById(@PathVariable long id){
        return studentsService.getStudentsById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Students> updateStudents(@PathVariable long id,@RequestBody Students students){
        return studentsService.updateStudents(id, students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudents(@PathVariable long id){
        return studentsService.deleteStudent(id);
    }

}
