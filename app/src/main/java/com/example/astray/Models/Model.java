package com.example.astray.Models;

public class  Model {

    String name,age,gender,missingplace,aboutchild,mno;
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Model(){}

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getMno() {
        return mno;
    }

    public String getGender() {
        return gender;
    }
    public String getMissingplace() {
        return missingplace;
    }
    public String getAboutchild() {
        return aboutchild;
    }
}
