package com.midooabdaim.ardak.data.model;

public class Users {
    String Name;
    String Email;
    String Photo;
    String Phone;
    String id;

    public Users(String name, String email, String photo, String phone, String id) {
        Name = name;
        Email = email;
        Photo = photo;
        Phone = phone;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
