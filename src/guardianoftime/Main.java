package guardianoftime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
    TEST CASES:

    2020-01-16 14:30:00;2020-01-17 08:30:00

    2020-02-28 02:30:00;2020-03-01 23:30:00

 */

public class Main {
    public static void main(String[] args) {

        // getting input
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(";");

        String startDate = input[0];
        String startDateOnly = startDate.split("\\s+")[0];
        String startTimeOnly = startDate.split("\\s+")[1];

        String endDate = input[1];
        String endDateOnly = endDate.split("\\s+")[0];
        String endTimeOnly = endDate.split("\\s+")[1];

        // this will store the dates
        List<Date> dates = new ArrayList<>();

        Date beginDate = null;
        Date finalDate = null;

        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // parsing the dates from input
            beginDate = dateTimeFormat.parse(startDate);
            finalDate = dateTimeFormat.parse(endDate);

            // estimating next midnight date
            Date nextMidnight = getNextMidnightDate(startDateOnly, dateOnlyFormat);
            // estimating final midnight date
            Date finalMidnight = getFinalMidnightDate(endDateOnly, dateOnlyFormat);


            // printing row with start date and next day midnight
            System.out.printf("%s - %s %d\n",
                    dateTimeFormat.format(beginDate),
                    dateTimeFormat.format(nextMidnight),
                    getMinutes(beginDate, nextMidnight));


            // iterating through all the days between nextMidnight and finalMidnight
            Calendar firstMidDate = Calendar.getInstance();
            firstMidDate.setTime(nextMidnight);
            Calendar finalMidDate = Calendar.getInstance();
            finalMidDate.setTime(finalMidnight);

            for (Date date = firstMidDate.getTime(); firstMidDate.before(finalMidDate); firstMidDate.add(Calendar.DATE, 1), date = firstMidDate.getTime()) {

                String tempDateAsString = dateOnlyFormat.format(date);
                Date temp = getNextMidnightDate(tempDateAsString, dateOnlyFormat);
                System.out.printf("%s - %s %d\n",
                        dateTimeFormat.format(date),
                        dateTimeFormat.format(temp),
                        getMinutes(date, temp));
            }


            // printing row with start date and next day midnight
            System.out.printf("%s - %s %d\n",
                    dateTimeFormat.format(finalMidnight),
                    dateTimeFormat.format(finalDate),
                    getMinutes(finalMidnight, finalDate));


            // total minutes
            System.out.println(getMinutes(beginDate, finalDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Date getFinalMidnightDate(String endDateOnly, DateFormat dateOnlyFormat) throws ParseException {
        Date finalMidnight;
        Calendar c = Calendar.getInstance();
        c.setTime(dateOnlyFormat.parse(endDateOnly));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        finalMidnight = c.getTime();
        return finalMidnight;
    }

    private static Date getNextMidnightDate(String startDateOnly, DateFormat dateOnlyFormat) throws ParseException {
        Date nextMidnight;
        Calendar c = Calendar.getInstance();
        c.setTime(dateOnlyFormat.parse(startDateOnly));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.DATE, 1);
        nextMidnight = c.getTime();
        return nextMidnight;
    }

    private static long getMinutes(Date beginDate, Date finalDate) {
        long difference = finalDate.getTime() - beginDate.getTime();

        long minutesDifference = difference / 60000;

        return minutesDifference;
    }
}
