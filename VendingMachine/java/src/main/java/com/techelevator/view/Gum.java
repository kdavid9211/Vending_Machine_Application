package com.techelevator.view;

import java.math.BigDecimal;

public class Gum extends MenuItem {

    public Gum(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    @Override
    protected String getNoise() {
        if(getInventory() <= 0) {
            return "SOLD OUT";
        }
        return "Chew Chew, Yum!";
    }
}
