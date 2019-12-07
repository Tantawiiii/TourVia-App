package com.example.tourism.Data;

public class DataModel {

    private String img;
    private String name;
    private String des;

    public DataModel(String imge, String name, String describtion) {
        this.img = imge;
        this.name = name;
        this.des = describtion;
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
}
