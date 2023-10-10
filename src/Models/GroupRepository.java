package Models;

import Utils.Types;

import java.util.List;

public interface GroupRepository {
    public Types.Result<Group, Exception> create(Group group);
    public Types.Result<Group, Exception> read(int id);
    public Types.Result<List<Group>, Exception> readAll();
    public Types.Result<Group, Exception> update(int id, Group group);
    public Types.Result<Boolean, Exception> delete(Group group);
}
