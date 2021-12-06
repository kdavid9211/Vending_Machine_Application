package com.techelevator.view;

import java.math.BigDecimal;

public class Chips extends MenuItem {

    public Chips(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    @Override
    protected String getNoise() {
        if(getInventory() <= 0) {
            return "SOLD OUT";
        }
        return "Crunch Crunch, Yum!";
    }
}
