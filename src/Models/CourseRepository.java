package Models;

import Utils.Types;

import java.util.List;

public interface CourseRepository {
    public Types.Result<Course, Exception> create(Course course);
    public Types.Result<Course, Exception> read(int id);
    public Types.Result<List<Course>, Exception> readAll();
    public Types.Result<Course, Exception> update(int id, Course course);
    public Types.Result<Boolean, Exception> delete(Course course);

}
