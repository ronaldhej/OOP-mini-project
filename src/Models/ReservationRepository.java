package Models;

import Utils.Types;

import java.util.List;

public interface ReservationRepository {
    public Types.Result<Reservation, Exception> create(Reservation reservation);
    public Types.Result<Reservation, Exception> read(int id);
    public Types.Result<List<Reservation>, Exception> readAll();
    public Types.Result<Reservation, Exception> update(int id, Reservation reservation);
    public Types.Result<Boolean, Exception> delete(Reservation reservation);
}
