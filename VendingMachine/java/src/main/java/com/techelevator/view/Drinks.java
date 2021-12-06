package com.techelevator.view;

import java.math.BigDecimal;

public class Drinks extends MenuItem {

    public Drinks(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    @Override
    protected String getNoise() {
        if(getInventory() <= 0) {
            return "SOLD OUT";
        }
        return "Glug Glug, Yum!";
    }
}
