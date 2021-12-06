package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyTest {

    private Candy candy;

    @Before
    public void setup() {
        candy = new Candy("","", new BigDecimal(0));
    }

    @Test
    public void test_get_noise_not_sold_out() {
        String result = candy.getNoise();
        Assert.assertEquals("Munch Munch, Yum!", result);
    }

    @Test
    public void test_get_noise_sold_out() {
        candy.setInventory(0);
        String result = candy.getNoise();
        Assert.assertEquals("SOLD OUT", result);
    }

}