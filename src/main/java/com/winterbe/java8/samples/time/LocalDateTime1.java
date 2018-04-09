package com.winterbe.java8.samples.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalDateTime1 {

    public static void main(String[] args) {
//        LocalDate nowDate = LocalDate.now();
//        LocalTime nowTime = LocalTime.now();
//        System.out.println(nowDate+" "+nowTime.format( DateTimeFormatter.ofPattern("HH:mm:ss")));
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println(nowDateTime.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();
        System.out.println("Instant:" + instant);
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014


        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = parsed.format(formatter);
        System.out.println(string);     // Nov 03, 2014 - 07:13
    }

}