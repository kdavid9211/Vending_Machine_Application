package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ChipsTest {

    private Chips chips;

    @Before
    public void setup(){
        chips = new Chips("", "", new BigDecimal(0));
    }

    @Test
    public void test_get_noise_not_sold_out() {
        String result = chips.getNoise();
        Assert.assertEquals("Crunch Crunch, Yum!", result);
    }

    @Test
    public void test_get_noise_sold_out(){
        chips.setInventory(0);
        String result = chips.getNoise();
        Assert.assertEquals("SOLD OUT", result);
    }

}