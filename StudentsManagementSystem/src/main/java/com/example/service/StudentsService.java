package com.example.service;

import com.example.entity.Students;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired
    StudentsRepository studentsRepository;
    public Students addStudent(Students students){
        return studentsRepository.save(students);
    }

    public List<Students> getAllStudents(){
        return studentsRepository.findAll();
    }

    public ResponseEntity<Students> getStudentsById(long id){
      Students students=studentsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not exist student"));
      return ResponseEntity.ok(students);
    }

    public ResponseEntity<Students> updateStudents(long id,Students studentsDetails){
        Students updateStudents=studentsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Students not found"));
          updateStudents.setName(studentsDetails.getName());
          updateStudents.setEmail(studentsDetails.getEmail());
          updateStudents.setId(studentsDetails.getId());
            studentsRepository.save(updateStudents);
          return ResponseEntity.ok(updateStudents);
    }

    public ResponseEntity<String> deleteStudent(long id){
            Optional<Students> students =studentsRepository.findById(id);
            if (students.isPresent()){
                studentsRepository.deleteById(id);
                return ResponseEntity.ok("student deleted successfully");
            }else {
                throw  new ResourceNotFoundException("Student not found with "+id);
//                return (ResponseEntity<String>) ResponseEntity.notFound();
            }
    }

}
