package Models;


import Utils.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Student implements StudentRepository {
    private List<Student> students = new ArrayList();
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static int id;
    private String name;
    private List<Group> groups = Arrays.asList(new Group[Types.Course.values().length]);

    public Student(String name) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.name = name;
    }

    public Types.Result<Student, Exception> create(Student student) {
        try {
            students.add(student);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
        return new Types.Result<>(student, null);
    }

    public Types.Result<Student, Exception> read(int id) {
        try {
            Student student = students.stream().filter(s -> s.id == id).findFirst().get();
            return new Types.Result<>(student, null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<List<Student>, Exception> readAll() {
        if (students.size() == 0) {
            return new Types.Result<>(null, new Exception("No students"));
        } else {
            return new Types.Result<>(students, null);
        }
    }

    public Types.Result<Student, Exception> update(int id, Student student) {
        Student currentStudent = students.stream().filter(s -> s.id == id).findFirst().map(s -> {
            s.name = student.name;
            s.groups = student.groups;
            return s;
        }).get();

        return new Types.Result<>(currentStudent, null);
    }

    public Types.Result<Boolean, Exception> delete(Student student) {
        try {
            students.remove(student);
            return new Types.Result<>(true, null);
        } catch (Exception e) {
            return new Types.Result<>(false, e);
        }
    }


}
