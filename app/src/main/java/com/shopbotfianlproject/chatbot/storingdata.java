package com.shopbotfianlproject.chatbot;

public class storingdata {


    String name,mobileno,email,usern,passwo;

    public storingdata() {
    }

    public storingdata(String name, String mobileno, String email, String usern, String passwo) {
        this.name = name;
        this.mobileno = mobileno;
        this.email = email;
        this.usern = usern;
        this.passwo = passwo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public String getPasswo() {
        return passwo;
    }

    public void setPasswo(String passwo) {
        this.passwo = passwo;
    }
}
