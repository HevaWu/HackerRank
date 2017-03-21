/*Java Date and Time
by DOSHI
Problem
Submissions
Leaderboard
Discussions
Editorial
The Calendar class is an abstract class that provides methods for converting between a specific instant in time and a set of calendar fields such as YEAR, MONTH, DAY_OF_MONTH, HOUR, and so on, and for manipulating the calendar fields, such as getting the date of the next week.

You are given a date. Your task is to find what the day is on that date.

Input Format

A single line of input containing the space separated month, day and year, respectively, in   format.

Constraints

Output Format

Output the correct day in capital letters.

Sample Input

08 05 2015
Sample Output

WEDNESDAY
Explanation

The day on August 5th  was WEDNESDAY.*/






/*
use LocalDate.of() to get the localDate format element
then use ld.getDayOfWeek() to get the day in the week
 */

////////////////////////////////////////////////////////////////////////
//Java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.time.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String month = in.next();
        String day = in.next();
        String year = in.next();

        //transfer day month year from String to Integer
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);
        int yy = Integer.parseInt(year);
        LocalDate ld = LocalDate.of(yy, mm, dd);
        System.out.println(ld.getDayOfWeek());
    }
}
