package View;

import Models.GroupRepository;
import Models.ReservationRepository;
import Models.RoomRepository;
import Models.StudentRepository;

import java.util.Scanner;

public class BookingSystemView {

    public void printTest() {
        System.out.println("Test from view");
    }

    public String getUserInput(String prompt) {
        Scanner scannerObj = new Scanner(System.in);
        System.out.println(prompt);
        return scannerObj.nextLine();
    }

    public void printToView(String message) {
        System.out.println(message);
    }

}
