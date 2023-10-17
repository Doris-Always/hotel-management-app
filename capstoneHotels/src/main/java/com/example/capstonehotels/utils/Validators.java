package com.example.capstonehotels.utils;

public class Validators {
    public static boolean validatePhoneNumber(String phoneNumber) {
//        return phoneNumber.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        return phoneNumber.length() == 11;
    }

    public static boolean validateEmailAddress(String email) {
        return email.matches("^(.+)@(\\S+)$");
    }
}
