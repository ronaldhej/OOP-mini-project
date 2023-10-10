package Models;

import Utils.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Group implements GroupRepository {

    private List<Group> groups = new ArrayList();

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private static int id;

    private List<Student> students;

    private Types.Course course;

    public Group(Types.Course course) {
        this.course = course;
    }

    public Group(Types.Course course, List<Student> students) {
        this.course = course;
        this.students = students;
    }

    public Types.Result<Group, Exception> create(Group group) {
        try {
            groups.add(group);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
        return new Types.Result<>(group, null);
    }

    public Types.Result<Group, Exception> read(int id) {
        try {
            Group group = groups.stream().filter(g -> g.id == id).findFirst().get();
            return new Types.Result<>(group, null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<List<Group>, Exception> readAll() {
        if (groups.size() == 0) {
            return new Types.Result<>(null, new Exception("No groups"));
        } else {
            return new Types.Result<>(groups, null);
        }
    }

    public Types.Result<Group, Exception> update(int id, Group group) {
        Group currentGroup = groups.stream().filter(g -> g.id == id).findFirst().map(g -> {
            g.course = group.course;
            g.students = group.students;
            return g;
        }).get();

        return new Types.Result<>(currentGroup, null);
    }

    public Types.Result<Boolean, Exception> delete(Group group) {
        try {
            groups.remove(group);
            return new Types.Result<>(true, null);
        } catch (Exception e) {
            return new Types.Result<>(false, e);
        }
    }
}
