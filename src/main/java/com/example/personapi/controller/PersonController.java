package com.example.personapi.controller;

import com.example.personapi.model.Person;
import com.example.personapi.model.Student;
import com.example.personapi.model.Teacher;
import com.example.personapi.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Person> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }


    @GetMapping("/students/group/{group}")
    public List<Student> getStudentsByGroup(@PathVariable String group) {
        return service.getStudentsByGroup(group);
    }

    @GetMapping("/students/{id}/grades")
    public List<Integer> getStudentGrades(@PathVariable Long id) {
        return service.getStudentGrades(id);
    }

    @GetMapping("/teachers/subject/{subject}")
    public List<Teacher> getTeachersBySubject(@PathVariable String subject) {
        return service.getTeachersBySubject(subject);
    }

    @PostMapping
    public Person addPerson(@RequestBody java.util.Map<String, Object> body) {
        return service.addPerson(body);
    }
}
