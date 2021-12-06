package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineTest {

    private VendingMachine vm2;

    @Before
    public void setup() {
        vm2 = new VendingMachine();
    }

    @Test
    public void test_stock_inventory(){
        int originalMenuSize = vm2.getMenuChoices().entrySet().size();
        vm2.stockInventory();
        int newMenuSize = vm2.getMenuChoices().entrySet().size();
        Assert.assertEquals(0, originalMenuSize);
        Assert.assertEquals(16, newMenuSize);
    }


    @Test
    public void test_add_balance() {

    }

    @Test
    public void test_is_valid_slot_true(){
        vm2.stockInventory();
        boolean result = vm2.isValidSlot("A1");
        Assert.assertTrue(result);
    }

    @Test
    public void test_is_valid_slot_false(){
        vm2.stockInventory();
        boolean result = vm2.isValidSlot("Z5");
        Assert.assertFalse(result);
    }

    @Test
    public void test_purchase_item_insufficient_fund() {
        vm2.stockInventory();
       String result = vm2.purchaseItem("A1");
        Assert.assertEquals("Insufficient Funds. Please insert more money.", result);
    }

    @Test
    public void test_purchase_item_sufficient_fund() {
        vm2.stockInventory();
        vm2.addBalance(new BigDecimal(5));
        String result = vm2.purchaseItem("A1");
        Assert.assertEquals("Crunch Crunch, Yum!", result);
    }

    @Test
    public void test_purchase_item_does_not_exist() {
        vm2.addBalance(new BigDecimal(5));
        String result = vm2.purchaseItem("A1");
        Assert.assertEquals("Item Does Not Exist", result);
    }

     @Test
    public void test_balance_update() {
        vm2.stockInventory();
        vm2.addBalance(new BigDecimal(5.05));
        vm2.purchaseItem("A1");
        BigDecimal result = vm2.balanceUpdate("A1");
        result = result.setScale(0, RoundingMode.CEILING);
        Assert.assertEquals(new BigDecimal(2), result);
    }


    @Test
    public void test_return_change() {
        BigDecimal currentBalance = vm2.getBalance();
        String result = vm2.returnChange();
        Assert.assertEquals("Your change is: 0 quarters, 0 dimes, 0 nickel", result);
    }


}
