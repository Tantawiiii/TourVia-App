package com.example.tourism.Data;

/**
 * Created by : Ahmed Ramadan
 * date : 9 / 2019
 * ahmedtramadan4@gmail.com
 */


public class DataModel {

    private String img;
    private String name;
    private String des;
    private String location;


    public DataModel(String imge, String name, String describtion, String location) {
        this.img = imge;
        this.name = name;
        this.des = describtion;
        this.location = location;
    }

    public DataModel() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
