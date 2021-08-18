package com.mycompany.logBook;

public class Log {

    private String date;
    private String name;
    private String email;
    private String message;

    public Log() {
    }

    public Log(String date, String name, String email, String message) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
