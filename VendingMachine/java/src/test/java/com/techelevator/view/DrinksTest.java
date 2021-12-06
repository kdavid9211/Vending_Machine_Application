package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinksTest {

    private Drinks drinks;

    @Before
    public void setup() {
        drinks = new Drinks("","", new BigDecimal(0));
    }

    @Test
    public void test_get_noise_not_sold_out(){
        String result = drinks.getNoise();
        Assert.assertEquals("Glug Glug, Yum!", result);
    }

    @Test
    public void test_get_noise_sold_out(){
        drinks.setInventory(0);
        String result = drinks.getNoise();
        Assert.assertEquals("SOLD OUT", result);
    }

}
