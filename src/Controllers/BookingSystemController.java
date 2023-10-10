package Controllers;

import Models.*;
import Utils.Types;
import View.BookingSystemView;

import java.beans.PropertyEditorSupport;
import java.util.List;

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
                //Rooms
                case "1" -> {
                    switch (view.getUserInput("Would you like to: (1) Create, (2) Update, (3) Delete, (4) View or (0) Return to previous prompt?")) {
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
                //Courses
                case "2" -> {
                    switch (view.getUserInput("Would you like to: (1) Create, (2) Update, (3) Delete, (4) View or (0) Return to previous prompt?")) {
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
                //Student Groups
                case "3" -> {
                    switch (view.getUserInput("Would you like to: (1) Create, (2) Update, (3) Delete, (4) View or (0) Return to previous prompt?")) {
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
                //Reservations
                case "4" -> {
                    switch (view.getUserInput("Would you like to: (1) Create, (2) Update, (3) Delete, (4) View or (0) Return to previous prompt?")) {
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
                //Exit
                case "0" -> {
                    return;
                }
                default -> {
                    view.printToView("Invalid option");
                }
            }
        } while (true);


    }

    public Types.Result<Room, Exception> createRoom() {
        String roomNum = view.getUserInput("Enter room number: ");
        Room room = new Room(roomNum) {
        };
        Types.Result result = roomRepository.create(room);

        if (result.getFirst() == null) {
            return new Types.Result<>(null, (Exception) result.getSecond());
        } else {
            return new Types.Result<>(room, null);
        }
    }

    public Types.Result<Course, Exception> createCourse() {
        String courseName = view.getUserInput("Enter course name: ");
        Course course = new Course(courseName);
        Types.Result result = courseRepository.create(course);

        if (result.getFirst() == null) {
            return new Types.Result<>(null, (Exception) result.getSecond());
        } else {
            return new Types.Result<>(course, null);
        }
    }

    public Types.Result<Student, Exception> createStudent() {
        String studentName = view.getUserInput("Enter student's name: ");
        Student student = new Student(studentName);
        Types.Result result = studentRepository.create(student);

        if (result.getFirst() == null) {
            return new Types.Result<>(null, (Exception) result.getSecond());
        } else {
            return new Types.Result<>(student, null);
        }
    }

    public Types.Result<Group, Exception> createGroup(List<Student> students) {
        Types.Result result = courseRepository.getByName(view.getUserInput("Course for the group: "));
        if (result.getFirst() != null) {
            Group group = new Group((Course) result.getFirst(), students);
            return new Types.Result<>(group, null);
        } else {
            return new Types.Result<>(null, (Exception) result.getSecond());
        }
    }

    public Types.Result<List<Group>, Exception> getGroups() {

    }

    public Types.Result<Group, Exception> assignStudentsToGroup() {

    }
}
