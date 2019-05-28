package com.jackoftech.androidproject.Cards;

public class cards {
    // just a comment will be deleted
    private String userId;
    private String name;
    private String profileImageUrl;


    public cards (String userId, String name, String profileImageUrl){
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;

    }

    public String getUserId(){
        return userId;
    }
    public void setUserID(String userID){
        this.userId = userID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getprofileImageUrl(){
        return profileImageUrl;
    }
    public void setprofileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

}
