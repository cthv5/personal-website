package org.cth.gmweb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestMain {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        LocalDate newDate = LocalDate.parse("2019-05-26", dtf2);
        System.out.println(newDate);
    }
}
