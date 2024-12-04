package com.example.flight_project_1.Base_classes;

import java.io.Serializable;

public class Admin implements Serializable {
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //    public abstract void addFlights(Flight flight);
//    public abstract void addAirports();
//    public abstract void update_Flight_Schedule(Flight flight);
}