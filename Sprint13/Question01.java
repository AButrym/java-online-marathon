import java.util.Calendar;
import java.util.Date;

/* Write a method to convert date and time, represented via Date object to object of Calendar type. */

class Question01 {
    public static Calendar convertDateToCalendar(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convertDateToCalendar(new Date(0L)).getTime());
    }
}
