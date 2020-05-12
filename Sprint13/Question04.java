import java.time.LocalDate;
import java.time.Period;

/*
Write a method to get the date n-years m-months and k-days after today using new DateTime API.
Return the obtained date in the format ISO_LOCAL_DATE.
*/

class Question04 {
    public static String getDateAfterToday(int years, int months, int days) {
        return LocalDate.now().plus(Period.of(years, months, days)).toString();
    }

    public static void main(String[] args) {
        System.out.println(getDateAfterToday(3, 18, 27));
    }
}
