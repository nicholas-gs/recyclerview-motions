package com.example.user.recyclerviewanimationstransitions;

public class EmailModel {
    private int emailId;
    private boolean isRead, isImportant;
    private String imageUrl, from, subject, message, timeStamp;

    //Constructor
    public EmailModel(int emailId, boolean isRead, boolean isImportant,
                      String imageUrl, String from, String subject, String message, String timeStamp) {
        this.emailId = emailId;
        this.isRead = isRead;
        this.isImportant = isImportant;
        this.imageUrl = imageUrl;
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    // Getters
    public int getEmailId() {
        return emailId;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public boolean getIsImportant() {
        return isImportant;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
