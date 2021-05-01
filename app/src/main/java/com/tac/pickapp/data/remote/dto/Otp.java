package com.tac.pickapp.data.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Otp {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("csrf_token")
    @Expose
    private String csrfToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

}
