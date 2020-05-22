package model;

import java.io.Serializable;

public class Issue implements Serializable {
    private  int status;
    private  String title;
    private  String address;
    private  String description;
    private  String date;
    private  String time;
    public Issue() {
    }

    public Issue(int status, String title, String address, String description,String date,String time) {
        this.status = status;
        this.title = title;
        this.address = address;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
