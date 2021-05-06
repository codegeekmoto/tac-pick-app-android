package com.tac.pickapp.util;

public class Constants {

    public static final String APP_NAME = "PICK_APP";
    public static final String HOST = "https://tacpickapp.herokuapp.com";
    public static final String BASE_URI = "";
//    public static final String HOST = "http://192.168.8.101";
//    public static final String BASE_URI = "/pickapp/public";

    public static class Prefs {

        public static final String COOKIE = "COOKIE";
        public static final String TOKEN = "TOKEN";
        public static final String USER = "USER";
    }

    public static class Message {

        public static final String SOMETHING_WENT_WRONG = "Sorry, something went wrong there. Please try again.";

        public static final String LOGIN_INTERNET_ERROR = "Sorry, unable to login. Please check your internet connection and try again.";

        public static final String REGISTER_SUCCESS = "Thank you for registering with Pick App. Please use your account email and password to login.";
        public static final String REGISTER_INTERNET_ERROR = "Sorry, unable to register. Please check your internet connection and try again.";

        public static final String CANNOT_RESOLVE_HOST_ERROR = "Unable to resolve host. Please check your internet connection and try again.";

        public static final String EMAIL_EXISTS = "The email has already been taken.";
        public static final String PHONE_EXISTS = "The phone number has already been taken.";
        public static final String EMAIL_SUCCESS = "Email has been updated successfully.";
        public static final String PHONE_SUCCESS = "Phone number has been updated successfully.";
        public static final String CHANGE_PASSWORD_SUCCESS = "Password has been changed successfully.";
        public static final String CHANGE_PASSWORD_ERROR = "Password is not valid.";
    }
}
