import java.util.*;
import java.time.*;
import java.util.stream.Collectors;

public class BookingSystem {

    public static List<Group> groups;
    public static int NUM_ROOMS = 10;
    public static final int MAX_STUDENTS_PER_GROUP = 6;
    public enum Course {
        OOP,
        DEB,
        SU,
        BEEP,
        BOP
    }

    public static void main(String[] args) {

    }

    public class Result<K, V> {
        private K first;
        private V second;

        public Result(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {return first; }
        public V getSecond() { return second; }
    }

    public class Student {
        private String name;
        private List<Group> groups;


        public Student(String name) {
            this.name = name;
            this.groups = Arrays.asList(new Group[Course.values().length]);
        }

        public List<Group> getGroups() {
            return groups;
        }
    }

    public class Reservation{
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(00,00,00);
        LocalTime endTime = LocalTime.of(00,00,00);
        Group group;

        public Reservation(String start, String end, Group group){
            this.startTime = LocalTime.parse(start);
            this.endTime = LocalTime.parse(end);
            this.group = group;
        }
    }

    public class Group{
        public int id;
        private Course course;
        private List<String> members;
        private int hoursBooked = 0;

        public Group() {};

        public Group createGroup(Course course) {
            Group group = new Group();
            group.members = Arrays.asList(new String[MAX_STUDENTS_PER_GROUP]);
            group.course = course;
            groups.add(group);
            return group;
        }

        public Group createGroup(Course course, List<String> members) {
            Group group = new Group();
            group.members = Arrays.asList(new String[MAX_STUDENTS_PER_GROUP]);
            if (members.size() > 6) {
                System.out.printf("Group size too large, max size is: &i", MAX_STUDENTS_PER_GROUP);
                return null;
            }
            group.members.addAll(members);
            group.course = course;
            groups.add(group);
            return group;
        }

        public void deleteGroup() {
            try {
                groups.remove(this);
            } catch(Exception e) {
                System.out.printf("Exception: %s", e);
            }
        }

        public List<Group> listGroups() {
            return groups;
        }

        public Course getCourse() {
            return this.course;
        }

        public void updateHoursBooked(int hoursBooked) {
            this.hoursBooked += hoursBooked;
        }

        public void resetHoursBooked() {
            this.hoursBooked = 0;
        }

        public double getPriority() {
            if (this.hoursBooked < 4) return 1.0;
            return 1/this.hoursBooked;
        }

        public List<String> getGroupMembers() {
            try {
                return this.members;

            } catch(Exception e) {
                System.out.printf("Exception: %s", e);
            }
            return null;
        }

        public int getGroupSize() {
            return this.members.size();
        }
    }

    public class Room {
        List<Reservation> reservationList = new ArrayList<>();
        int roomID;

        public void addReservation(String start, String end, Group group){
            Reservation x = new Reservation(start, end, group);

            if(checkConflicts(x)){
                System.out.println("Your desired reservation conflicts with another reservation.");
                if(group.getPriority() > getConflict(x).group.getPriority()){
                    this.reservationList.remove(this.reservationList.indexOf(getConflict(x)));
                    this.reservationList.add(x);
                    System.out.println("Your reservation has priority. The conflicting reservation has been removed.");
                }
            }

            if(!checkConflicts(x)){
                this.reservationList.add(x);
                System.out.println("Your reservation was added.");
            }
        }

        boolean checkConflicts(Reservation x){
            for(int i = 0; i < this.reservationList.size(); i++){
                Reservation y = this.reservationList.get(i);

                if(x.startTime.equals(y.startTime) || x.endTime.equals(y.endTime)) return true;
                if(x.startTime.isBefore(y.startTime) && x.endTime.isAfter(y.startTime)) return true;
                if(x.startTime.isAfter(y.startTime) && y.endTime.isAfter(x.startTime)) return true;
            }

            return false;
        }

        Reservation getConflict(Reservation x){
            for(int i = 0; i < this.reservationList.size(); i++){
                Reservation y = this.reservationList.get(i);

                if(x.startTime.equals(y.startTime) || x.endTime.equals(y.endTime)) return y;
                if(x.startTime.isBefore(y.startTime) && x.endTime.isAfter(y.startTime)) return y;
                if(x.startTime.isAfter(y.startTime) && y.endTime.isAfter(x.startTime)) return y;
            }

            return null;
        }
    }
}