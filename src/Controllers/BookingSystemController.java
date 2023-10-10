package Controllers;

import Models.*;
import Utils.Types;
import View.BookingSystemView;

public class BookingSystemController {
    private CourseRepository courseRepository;
    private GroupRepository groupRepository;
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private StudentRepository studentRepository;

    private BookingSystemView view;

    public BookingSystemController(CourseRepository courseRepository, GroupRepository groupRepo, ReservationRepository repRepo, RoomRepository roomRepo, StudentRepository studentRepo) {
        this.courseRepository = courseRepository;
        this.groupRepository = groupRepo;
        this.reservationRepository = repRepo;
        this.roomRepository = roomRepo;
        this.studentRepository = studentRepo;
    }

    public void runCLI() {
        view.printToView("Starting CLI");

        do {
            view.printToView("Which would you like to manage?");
            view.printToView("(1) Rooms");
            view.printToView("(2) Courses");
            view.printToView("(3) Student Groups");
            view.printToView("(4) Reservations");
            view.printToView("(0) Exit program");

            switch (view.getUserInput("Enter Option: ")) {
                case "1" -> {
                    String input = view.getUserInput("Would you like to: (1) Create, (2) Update, (3) Delete or (4) View?");
                    switch (input) {
                        case "1" -> {

                        }
                        case "2" -> {

                        }
                        case "3" -> {

                        }
                        case "4" -> {

                        }
                        default -> {
                            view.printToView("Invalid option");
                        }

                    }

                }
                case "2" -> {
                    view.printToView("Would you like to: (1) Create, (2) Update, (3) Delete or (4) View?");

                }
                case "3" -> {
                    view.printToView("Would you like to: (1) Create, (2) Update, (3) Delete or (4) View?");

                }
                case "4" -> {
                    view.printToView("Would you like to: (1) Create, (2) Update, (3) Delete or (4) View?");

                }
                case "0" -> {
                    return;
                }
                default -> {
                    view.printToView("Invalid option");
                }
            }
        } while(true);


    }

    public Types.Result<Room, Exception> createRoom() {
        String roomNum = view.getUserInput("Enter room number:");
        Room room = new Room(roomNum){};
        Types.Result result = roomRepository.create(room);

        if (result.getFirst() == null) {
            return new Types.Result<>(null, (Exception) result.getSecond());
        } else {
            return new Types.Result<>(room, null);
        }
    }

    public Types.Course createCourse(String courseName) {
        switch (courseName) {
            case "OOP" -> {

            }
        }

    }
}
