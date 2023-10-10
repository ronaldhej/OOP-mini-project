package Models;

import Utils.Types;

import java.util.List;

public interface RoomRepository {
    public Types.Result<Room, Exception> create(Room room);
    public Types.Result<Room, Exception> read(int id);
    public Types.Result<List<Room>, Exception> readAll();
    public Types.Result<Room, Exception> update(int id, Room room);
    public Types.Result<Boolean, Exception> delete(Room room);
}
