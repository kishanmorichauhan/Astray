package com.example.astray.Models;

public class Users {

    String username,mail,password,mobailno;

    public Users(String username, String mail, String password,String mobailno) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.mobailno = mobailno;
    }

    public Users(){}

    //signup constructor
    public Users(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getMobailno() {
        return mobailno;
    }

    public void setMobailno(String password) {
        this.mobailno = mobailno;
    }
}
