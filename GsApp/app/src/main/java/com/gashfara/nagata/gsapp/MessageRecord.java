//1つのセルにあるデータを保存するためのデータクラスです。
package com.gashfara.nagata.gsapp;

public class MessageRecord {
    //保存するデータ全てを変数で定義します。
    private String id;
    private String imageUrl;
    private String comment;
    private int goodCount;

    //データを１つ作成する関数です。項目が増えたら増やしましょう。
    public MessageRecord(String id, String imageUrl, String comment, int goodCount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.goodCount = goodCount;
    }
    //それぞれの項目を返す関数です。項目が増えたら増やしましょう。
    public String getId() {
        return id;
    }
    public String getComment() {
        return comment;
    }
    public String getImageUrl() {

        return imageUrl;
    }
    public int getGoodCount() {
        return goodCount;
    }
    //セットする関数.項目が増えたら追加しましょう
    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }
}
