import java.time.LocalDate;

/*
Write a method to check if a year is a leap year or not, using for this the LocalDate class.
If a year is leap then method should return true, otherwise - false.
*/

class Question02 {
    public static boolean isLeapYear(int year) {
        return LocalDate.ofYearDay(year, 1).isLeapYear();
    }

    public static void main(String[] args) {
        System.out.println(isLeapYear(2020));
    }
}
