package com.example.user.recyclerviewanimationstransitions.SlideFade;

public class CardItemModel {

    private String title, datatime, message;
    private int image;

    public CardItemModel(String title, String datatime, String message, int image) {
        this.title = title;
        this.datatime = datatime;
        this.message = message;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDatatime() {
        return datatime;
    }

    public String getMessage() {
        return message;
    }

    public int getImage() {
        return image;
    }
}
