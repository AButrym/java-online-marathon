import java.util.Calendar;
import java.util.GregorianCalendar;

/*
Write a method to get the name of last day of the given month in given year.
Use GregorianCalendar class to solve this exercise.
The name of day must corresponds next format: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday.
If month isn't correct then name is "Wrong Month".
 */

class Question02 {
    public static String lastDayOfMonth(int month, int year) {
        if (month < Calendar.JANUARY || Calendar.DECEMBER < month) {
            return "Wrong Month";
        }

        //Calendar calendar = new GregorianCalendar(year, month + 1, 1); // next month
        //calendar.add(Calendar.DAY_OF_MONTH, -1); // one day back

        //Alternative:
        Calendar calendar = new GregorianCalendar(year, month, 1);
        calendar.set(Calendar.DAY_OF_MONTH,
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getDisplayName(
                Calendar.DAY_OF_WEEK, Calendar.LONG, java.util.Locale.US);
    }

    public static void main(String[] args) {
        System.out.println(lastDayOfMonth(Calendar.DECEMBER, 2020));
        System.out.println(lastDayOfMonth(Calendar.JANUARY, 2020));
    }
}
