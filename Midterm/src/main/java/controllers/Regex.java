package controllers;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static Pattern passwordPattern = Pattern.compile("^[0-9]{1,3}[A-Z]{1}[a-z]{5,10}$");
    public static Pattern birthdayPattern = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
    public static Pattern deadlinePattern = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4} ([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    public static Pattern onlyDigitPattern = Pattern.compile("^\\d+$");
    public static Pattern onlyLetterOrDigitPattern = Pattern.compile("^[A-Za-z0-9]+$");



}
