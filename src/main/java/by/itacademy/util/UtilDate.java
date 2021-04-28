package by.itacademy.util;

import java.util.Calendar;

public class UtilDate {

    public static long[] getDayStartAndDayEndInMilliseconds(int dayShift) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long dayStart = calendar.getTimeInMillis();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        long dayEnd = calendar.getTimeInMillis();

        return new long[] {dayStart, dayEnd};
    }
}
