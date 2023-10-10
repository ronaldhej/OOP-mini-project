package Models;

import Utils.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Course implements CourseRepository {

    private List<Course> courses = new ArrayList();

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    int id;

    String name;

    public Course(String courseName) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.name = courseName;
    }

    public Types.Result<Course, Exception> create(Course course) {
        try {
            courses.add(course);
            return new Types.Result<>(course, null);
        } catch (Exception e) {
            courses.add(course);
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<Course, Exception> read(int id) {
        try {
            Course course = courses.stream().filter(c -> c.id == id).findFirst().get();
            return new Types.Result<>(course, null);
        }catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<Course, Exception> getByName(String name) {
        try {
            Course course = courses.stream().filter(c -> c.name == name).findFirst().get();
            return new Types.Result<>(course, null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<List<Course>, Exception> readAll() {
        if (courses.size() == 0) {
            return new Types.Result<>(null, new Exception("No courses"));
        } else {
            return new Types.Result<>(courses, null);
        }
    }

    public Types.Result<Course, Exception> update(int id, Course course) {
        try {
            Course currentCourse = courses.stream().filter(c -> c.id == id).findFirst().map(c -> {
                c.name = course.name;
                return c;
            }).get();

            return new Types.Result<>(currentCourse, null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<Boolean, Exception> delete(Course course) {
        try {
            courses.remove(course);
            return new Types.Result<>(true, null);
        } catch (Exception e) {
            return new Types.Result<>(false, e);
        }
    }
}
