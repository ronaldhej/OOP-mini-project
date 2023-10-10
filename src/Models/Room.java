package Models;

import Utils.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Room implements RoomRepository {

    private List<Room> rooms = new ArrayList();

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    int id;

    String roomNum;

    List<Reservation> reservationList = new ArrayList();

    public Room(String roomNum) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.roomNum = roomNum;
    };

    public Types.Result<Room, Exception> create(Room room) {
        try {
            rooms.add(room);
            return new Types.Result<>(room, null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<Room, Exception> read(int id) {
        try {
            Room room = rooms.stream().filter(r -> r.id == id).findFirst().get();
            return new Types.Result<>(room, null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<List<Room>, Exception> readAll() {
        if (rooms.size() == 0) {
            return new Types.Result<>(null, new Exception("No rooms"));
        } else {
            return new Types.Result<>(rooms, null);
        }
    }

    public Types.Result<Room, Exception> update(int id, Room room) {
        try {
            Room currentRoom = rooms.stream().filter(r -> r.id == id).findFirst().map(r -> {
                r.reservationList = room.reservationList;
                return r;
            }).get();

            return new Types.Result<>(currentRoom,  null);
        } catch (Exception e) {
            return new Types.Result<>(null, e);
        }
    }

    public Types.Result<Boolean, Exception> delete(Room room) {
        try {
            rooms.remove(room);
            return new Types.Result<>(true, null);
        } catch (Exception e) {
            return new Types.Result<>(false, e);
        }
    }
}
