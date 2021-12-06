package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class GumTest {

    private Gum gum;

    @Before
    public void setup() {
        gum = new Gum("","", new BigDecimal(0));
    }

    @Test
    public void test_get_noise_not_sold_out() {
        String result = gum.getNoise();
        Assert.assertEquals("Chew Chew, Yum!", result);
    }

    @Test
    public void test_get_noise_sold_out(){
        gum.setInventory(0);
        String result = gum.getNoise();
        Assert.assertEquals("SOLD OUT", result);
    }

}
