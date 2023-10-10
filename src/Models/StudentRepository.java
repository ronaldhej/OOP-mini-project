package Models;

import Utils.Types;

import java.util.List;

public interface StudentRepository {
    public Types.Result<Student, Exception> create(Student student);

    public Types.Result<Student, Exception> read(int id);

    public Types.Result<Student, Exception> getByName(String name);

    public Types.Result<List<Student>, Exception> readAll();

    public Types.Result<Student, Exception> update(int id, Student student);

    public Types.Result<Boolean, Exception> delete(Student student);
}
