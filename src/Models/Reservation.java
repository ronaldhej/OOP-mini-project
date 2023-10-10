package Models;

import Utils.Types;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Reservation implements ReservationRepository {

    private List<Reservation> reservations = new ArrayList();

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    int id;

    LocalDate date = LocalDate.now();

    LocalTime startTime = LocalTime.of(00,00,00);
    LocalTime endTime = LocalTime.of(00,00,00);

    Group group;

    public Reservation(String start, String end, Group group) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.startTime = LocalTime.parse(start);
        this.endTime = LocalTime.parse(end);
        this.group = group;
    }

    public Types.Result<Reservation, Exception> create(Reservation reservation) {
        reservations.add(reservation);
        return new Types.Result<>(reservation, null);
    }

    public Types.Result<Reservation, Exception> read(int id) {
        Reservation reservation = reservations.stream().filter(r -> r.id == id).findFirst().get();
        return new Types.Result<>(reservation, null);
    }

    public Types.Result<List<Reservation>, Exception> readAll() {
        if (reservations.size() == 0) {
            return new Types.Result<>(null, new Exception("No reservations"));
        } else {
            return new Types.Result<>(reservations, null);
        }
    }

    public Types.Result<Reservation, Exception> update(int id, Reservation reservation) {
        Reservation currentReservation = reservations.stream().filter(r -> r.id == id).findFirst().map(r -> {
            r.startTime = reservation.startTime;
            r.endTime = reservation.endTime;
            r.group = reservation.group;
            return r;
        }).get();

        return new Types.Result<>(currentReservation, null);
    }

    public Types.Result<Boolean, Exception> delete(Reservation reservation) {
        try {
            reservations.remove(reservation);
            return new Types.Result<>(true, null);
        } catch (Exception e) {
            return new Types.Result<>(false, e);
        }
    }
}
