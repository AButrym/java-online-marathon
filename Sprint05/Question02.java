import java.time.LocalDateTime;

/* •Implement class User with protected fields name, surname and public method getFullName()
 *  which returns name and surname with one space between them. Constructor of User
 *  define as public with two parameters – for initialization name and surname
 * •Implement also class Student that is inherited from User. This class must have
 *  integer protected field year – the year of entering university, and
 *  public method getCourseNumber() – evaluates year of studying based on year property
 *  and current year: the difference between years + 1. (If the value is not from the range from 1 to 6,
 *  the metod should return -1). Conctructor of Student should be public and
 *  have 3 parameters: year, name and surname and pass name and surname to the base class.
 *  (year parameter should be the first.)
 */
class User {
    protected String name;
    protected String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }
}

class Student extends User {
    protected int year;

    public Student(int year, String name, String surname) {
        super(name, surname);
        this.year = year;
    }

    public int getCourseNumber() {
        int currentYear = LocalDateTime.now().getYear();
        int courseNumber = currentYear - year + 1;
        courseNumber = 1 <= courseNumber && courseNumber <= 6 ? courseNumber : -1;
        return courseNumber;
    }
}
