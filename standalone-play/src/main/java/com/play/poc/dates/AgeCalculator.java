package com.play.poc.dates;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {
    private static final int MAX_MEMBER_AGE = 80;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static void main(String[] args) {
        try {
            LocalDate today = LocalDate.now();                          //Today's date
            LocalDate birthday = LocalDate.of(1937, Month.JANUARY, 1);  //Birth date

            String dateOfBirth = "1929/12/22";
            LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
            LocalDate currentDate = LocalDate.now();

            LocalDate computedDateOfBirth = calculateAge(birthDate, currentDate);
            System.out.println("Calculated Date :: " + computedDateOfBirth.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    public static LocalDate calculateAge(LocalDate dateOfBirth, LocalDate currentDate) {
        /** Compute for member age. */
        Period period = Period.between(dateOfBirth, currentDate);
        int calculatedAge = period.getYears();

        if (calculatedAge > MAX_MEMBER_AGE) {
            /** Calculate age variation for given maximum age 80. */
            int ageVariation = calculatedAge - MAX_MEMBER_AGE;

            /** Reset and optimize age for member year to match maximum age 80.  */
            int optimizedYear = dateOfBirth.getYear() + ageVariation;
            LocalDate optimizedDateOfBirth = LocalDate.of(optimizedYear, dateOfBirth.getMonthValue(), dateOfBirth.getDayOfMonth());
            return optimizedDateOfBirth;
        } else {
            return dateOfBirth;
        }
    }


    public static LocalDate calculateAge(String dateOfBirth) {
        LocalDate currentDate = LocalDate.now();

        /** Convert String to LocalDate */
        LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);

        /** Compute for member age. */
        Period period = Period.between(birthDate, currentDate);
        int calculatedAge = period.getYears();

        if (calculatedAge > 80) {
            /** Calculate age variation for given maximum age 80. */
            int ageVariation = calculatedAge - 80;

            /** Reset and optimize age for member year to match maximum age 80.  */
            int optimizedYear = birthDate.getYear() + ageVariation;
            LocalDate optimizedDateOfBirth = LocalDate.of(optimizedYear, birthDate.getMonthValue(), birthDate.getDayOfMonth());
            return optimizedDateOfBirth;
        } else {
            return birthDate;
        }
    }
}
