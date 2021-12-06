package com.techelevator.view;

import java.math.BigDecimal;

public abstract class MenuItem {

    private String name = "";
    private BigDecimal price = new BigDecimal(0);
    private String slot = "";
    private int inventory = 5;

    public String getName() {return name;}
    public BigDecimal getPrice() {return price;}
    public String getSlot() {return slot;}
    public int getInventory() {return inventory;}

    public void setName(String name) {this.name = name;}
    public void setPrice(BigDecimal price) {this.price = price;}
    public void setSlot(String slot) {this.slot = slot;}
    public void setInventory(int inventory) {this.inventory = inventory;}

    protected abstract String getNoise();

    public MenuItem(String slot, String name, BigDecimal price) {
        this.slot = slot;
        this.name = name;
        this.price = price;
    }

    public String dispenseItem() {
        if(inventory == 0) {
            return "SOLD OUT";
        } else {
            inventory -= 1;
            return getNoise();
        }

    }

}
