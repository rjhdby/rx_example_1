package com.example.rjhdby.omsk.network.model;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    public int id;
    public int userId;
    public int restaurantId;
    public List<Integer> contentOrdersId = new ArrayList<Integer>();
    public String comment;
    public int totalPrice;

    private int status;

    public void setStatus(int status){
        this.status=status;
    }
}
