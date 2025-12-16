package com.example.personapi.service;

import com.example.personapi.model.Person;
import com.example.personapi.model.Student;
import com.example.personapi.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();
    private long id = 1;

    public PersonService() {
        Student s1 = new Student();
        s1.id = id++;
        s1.name = "Nazar";
        s1.age = 20;
        s1.group = "CS-1";
        s1.grades.add(90);
        s1.grades.add(85);

        Student s2 = new Student();
        s2.id = id++;
        s2.name = "Oleh";
        s2.age = 21;
        s2.group = "CS-2";
        s2.grades.add(75);

        Teacher t1 = new Teacher();
        t1.id = id++;
        t1.name = "Alice";
        t1.age = 40;
        t1.subject = "Math";

        Teacher t2 = new Teacher();
        t2.id = id++;
        t2.name = "Bob";
        t2.age = 45;
        t2.subject = "Physics";

        persons.add(s1);
        persons.add(s2);
        persons.add(t1);
        persons.add(t2);
    }

    public List<Person> getAll() {
        return persons;
    }

    public Student addStudent(Student s) {
        s.id = id++;
        persons.add(s);
        return s;
    }

    public Teacher addTeacher(Teacher t) {
        t.id = id++;
        persons.add(t);
        return t;
    }

    public void remove(Long id) {
        persons.removeIf(p -> p.id.equals(id));
    }


    public List<Student> getStudentsByGroup(String group) {
        return persons.stream()
                .filter(p -> p instanceof Student)
                .map(p -> (Student) p)
                .filter(s -> s.group.equals(group))
                .collect(Collectors.toList());
    }

    public List<Integer> getStudentGrades(Long studentId) {
        return persons.stream()
                .filter(p -> p instanceof Student)
                .map(p -> (Student) p)
                .filter(s -> s.id.equals(studentId))
                .findFirst()
                .map(s -> s.grades)
                .orElse(new ArrayList<>());
    }


    public List<Teacher> getTeachersBySubject(String subject) {
        return persons.stream()
                .filter(p -> p instanceof Teacher)
                .map(p -> (Teacher) p)
                .filter(t -> t.subject.equals(subject))
                .collect(Collectors.toList());
    }
}
