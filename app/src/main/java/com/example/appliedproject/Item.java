package com.example.appliedproject;

public class Item {
    String title;
    String subtitle;

    String innerExplain;
    int image;

    public Item(String title, String subtitle, String innerExplain, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.innerExplain = innerExplain;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getInnerExplain() {
        return innerExplain;
    }

    public void setInnerExplain(String innerExplain) {
        this.innerExplain = innerExplain;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
