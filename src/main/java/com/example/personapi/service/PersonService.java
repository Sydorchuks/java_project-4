package com.example.personapi.service;

import com.example.personapi.model.Person;
import com.example.personapi.model.Student;
import com.example.personapi.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();
    private long id = 1;

    public PersonService() {
        Student s = new Student();
        s.id = id++;
        s.name = "Nazar";
        s.age = 20;
        s.group = "CS-1";
        s.type = "student";

        Teacher t = new Teacher();
        t.id = id++;
        t.name = "Alice";
        t.age = 40;
        t.subject = "Math";
        t.type = "teacher";

        persons.add(s);
        persons.add(t);
    }

    public List<Person> getAll() {
        return persons;
    }

    public Person add(Person p) {
        if ("student".equals(p.type)) {
        }
        if ("teacher".equals(p.type)) {
        }
        p.id = id++;
        persons.add(p);
        return p;
    }


    public void remove(Long id) {
        for (int i = 0; i < persons.size(); i++) {
            Person p = persons.get(i);
            if (p.id.equals(id)) {
                persons.remove(i);
                break;
            }
        }
    }
}
