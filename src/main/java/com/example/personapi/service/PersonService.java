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

    public Person addPerson(java.util.Map<String, Object> body) {
        if (body == null) throw new IllegalArgumentException("There is nothing");

        Object nameObj = body.get("name");
        Object ageObj = body.get("age");

        if (!(nameObj instanceof String) || ((String) nameObj).isBlank()) {
            throw new IllegalArgumentException("Field 'name' is required");
        }

        Integer age = parseInt(ageObj);
        if (age == null || age < 0) {
            throw new IllegalArgumentException("Field 'age' must be a non-negative number");
        }

        boolean hasStudentFields = body.containsKey("group") || body.containsKey("grades");
        boolean hasTeacherFields = body.containsKey("subject");

        if (hasStudentFields && hasTeacherFields) {
            throw new IllegalArgumentException("Ambiguous data: both student and teacher fields provided");
        }

        if (hasStudentFields) {
            Student s = new Student();
            s.name = (String) nameObj;
            s.age = age;

            Object groupObj = body.get("group");
            if (!(groupObj instanceof String) || ((String) groupObj).isBlank()) {
                throw new IllegalArgumentException("Field 'group' is required for student");
            }
            s.group = (String) groupObj;

            s.grades.clear();
            Object gradesObj = body.get("grades");
            if (gradesObj instanceof java.util.List<?> list) {
                for (Object x : list) {
                    Integer g = parseInt(x);
                    if (g == null) throw new IllegalArgumentException("Grades must be numbers");
                    s.grades.add(g);
                }
            }

            return addStudent(s);
        }

        if (hasTeacherFields) {
            Teacher t = new Teacher();
            t.name = (String) nameObj;
            t.age = age;

            Object subjectObj = body.get("subject");
            if (!(subjectObj instanceof String) || ((String) subjectObj).isBlank()) {
                throw new IllegalArgumentException("Field 'subject' is required for teacher");
            }
            t.subject = (String) subjectObj;

            return addTeacher(t);
        }

        throw new IllegalArgumentException("Unknown person type: provide student fields (group/grades) or teacher field (subject)");
    }

    private Integer parseInt(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Integer i) return i;
        if (obj instanceof Long l) return (int) (long) l;
        if (obj instanceof Double d) return (int) Math.floor(d);
        if (obj instanceof String s) {
            try { return Integer.parseInt(s.trim()); } catch (Exception ignored) { return null; }
        }
        return null;
    }

}
