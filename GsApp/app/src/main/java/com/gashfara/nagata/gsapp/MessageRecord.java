package com.gashfara.nagata.gsapp;

public class MessageRecord {

    private String imageUrl;
    private String comment;

    public MessageRecord(String imageUrl, String comment) {
        this.imageUrl = imageUrl;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
