package com.battleship201.model;

public class User {
    private int id;
    private String username;
    private String hashedPassword;

    public User(){
        
    }
    
    public User(String username, String hashedPassword){
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return hashedPassword;
    }

    public void setPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }
}

