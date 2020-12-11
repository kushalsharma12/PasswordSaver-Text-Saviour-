package com.problemsolvers.textsaviour;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saviour_table")
public class Saviour {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String platformName;

    private String email;

    private String password;



    public Saviour(String platformName, String email, String password) {
        this.platformName = platformName;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
