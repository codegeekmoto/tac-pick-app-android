package com.tac.pickapp.util;

public class Constants {

    public static String APP_NAME = "PICK_APP";
    public static String HOST = "https://tacpickapp.herokuapp.com";

    public static class Prefs {

        public static String COOKIE = "COOKIE";
        public static String TOKEN = "TOKEN";
        public static String USER = "USER";
    }

    public static class Endpoint {

        public final static String USER_CREATE = "auth/create";
        public final static String USER_LOGIN = "auth/login";
        public final static String USER_RESET_PASSWORD = "auth/reset";
        public final static String USER_RESEND_OTP = "auth/resend-otp";
        public final static String USER_CHANGE_PASSWORD = "auth/change-password";
        public final static String USER_UPDATE_EMAIL = "user/update-email";
        public final static String USER_UPDATE_PHONE = "user/update-phone";
        public final static String USER_UPDATE_PASSWORD = "user/change-password";

        public final static String CAR_ALL = "car";
        public final static String CAR_CREATE = "car/create";
        public final static String CAR_DELETE = "car/delete";

    }

    public static class Message {

        public static String SOMETHING_WENT_WRONG = "Sorry, something went wrong there. Please try again.";

        public static String LOGIN_INTERNET_ERROR = "Sorry, unable to login. Please check your internet connection and try again.";

        public static String REGISTER_SUCCESS = "Thank you for registering with Pick App. Please use your account email and password to login.";
        public static String REGISTER_INTERNET_ERROR = "Sorry, unable to register. Please check your internet connection and try again.";

        public static String CANNOT_RESOLVE_HOST_ERROR = "Unable to resolve host. Please check your internet connection and try again.";

        public static String EMAIL_EXISTS = "The email has already been taken.";
        public static String PHONE_EXISTS = "The phone number has already been taken.";
        public static String EMAIL_SUCCESS = "Email has been updated successfully.";
        public static String PHONE_SUCCESS = "Phone number has been updated successfully.";
        public static String CHANGE_PASSWORD_SUCCESS = "Password has been changed successfully.";
        public static String CHANGE_PASSWORD_ERROR = "Password is not valid.";
    }
}
