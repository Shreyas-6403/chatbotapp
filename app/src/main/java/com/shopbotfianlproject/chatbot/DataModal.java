package com.shopbotfianlproject.chatbot;

public class DataModal {

    // variables for storing our image and name.
    private String name;
    private String imgUrl;
    private String price;

    public DataModal() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    public DataModal(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.price=price;
    }

    // getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getprice(){ return price; }

    public void setprice(String price){
        this.price=price;
    }
}

