package com.huntingCube.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class QuickPasswordEncodingGenerator {
  
    /**
     * @param args
     */
    public static void main(String[] args) {
        encryptPassword();
    }

    public static void removeCharacters() {
        String s = "9 0-5- Lac";
        System.out.println(">>>>>>>" + (s.replaceAll("[^0-9.]", "")) + "<<<<<<<<<<<");
    }

    public static void convertDate(String day, String month, String year) throws Exception {
        String dateString = month + " " + day + ", " + year;
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    }

    public static void encryptPassword() {
        String password = "dravit";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }
}