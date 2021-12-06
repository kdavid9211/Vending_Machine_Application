package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingMachine {

    Map<String, MenuItem> menuChoices = new LinkedHashMap<>();
    private BigDecimal balance = new BigDecimal(0);

    public BigDecimal getBalance() {return balance;}

    public Map<String, MenuItem> getMenuChoices() {return menuChoices;}

    public VendingMachine() {
        balance = balance.setScale(2, RoundingMode.CEILING);
    }

    public void stockInventory() {
        String fileName = "vendingmachine.csv";
        File menuNames = new File("vendingmachine.csv");

        try (Scanner fileScanner = new Scanner(menuNames)) {
            while (fileScanner.hasNextLine()) {
                String line1 = fileScanner.nextLine();
                String[] array = line1.split("\\|");
                if (array[3].equals("Chip")) {
                    Chips chip = new Chips(array[0], array[1], new BigDecimal(array[2]));
                    menuChoices.put(array[0], chip);
                } else if (array[3].equals("Candy")) {
                    Candy candy = new Candy(array[0], array[1], new BigDecimal(array[2]));
                    menuChoices.put(array[0], candy);
                } else if (array[3].equals("Drink")) {
                    Drinks drink = new Drinks(array[0], array[1], new BigDecimal(array[2]));
                    menuChoices.put(array[0], drink);
                } else if (array[3].equals("Gum")) {
                    Gum gum = new Gum(array[0], array[1], new BigDecimal(array[2]));
                    menuChoices.put(array[0], gum);
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to stock");
        }
    }


    public void addBalance(BigDecimal moneyToAdd) {
        BigDecimal currentBalance = balance;
        balance = balance.add(moneyToAdd);
        purchaseAudit("FEED MONEY", currentBalance, getBalance());
    }


public boolean isValidSlot(String slot) {
        if(this.menuChoices.containsKey(slot)){
            return true;
        } else {
            return false;
        }
}


    public String purchaseItem(String slot) {
        if(isValidSlot(slot)) {
            String title = menuChoices.get(slot).getName() + " " + menuChoices.get(slot).getSlot();
            BigDecimal itemPrice = menuChoices.get(slot).getPrice();
            if (itemPrice.compareTo(balance) <= 0) {
                BigDecimal currentBalance = balance;
                if (menuChoices.get(slot).dispenseItem() != "SOLD OUT") {
                    purchaseAudit(title, currentBalance, balanceUpdate(slot));
                    return menuChoices.get(slot).getNoise();
                } else {
                    return "SOLD OUT";
                }
            }
            if (itemPrice.compareTo(balance) > 0) {
                return "Insufficient Funds. Please insert more money.";
            }
        }
            return "Item Does Not Exist";
    }


    public BigDecimal balanceUpdate (String slot) {
        BigDecimal itemPrice = menuChoices.get(slot).getPrice();
        if(this.menuChoices.containsKey(slot) && itemPrice.compareTo(balance) > 0) {
            System.out.println("Insufficient Funds. Please insert more money.");
        } else {
            balance = balance.subtract(itemPrice);
        }
         return balance;
    }


   public String returnChange() {
       BigDecimal currentBalance = balance;
        BigDecimal totalChange = new BigDecimal(0);
        int quarterCounter = 0;
        int dimeCounter = 0;
        int nickelCounter = 0;
        while(balance.compareTo(new BigDecimal(0)) > 0) {
            if(balance.compareTo(new BigDecimal(0.25)) >= 0) {
                quarterCounter++;
                balance = balance.subtract(new BigDecimal(0.25));
            }
            else
                if(balance.compareTo(new BigDecimal(0.10)) >= 0) {
                dimeCounter++;
                balance = balance.subtract(new BigDecimal(0.10));
            }
            else
                if(balance.compareTo(new BigDecimal(0.01)) >= 0) {
                nickelCounter++;
                balance = BigDecimal.ZERO;
                }
        }
       purchaseAudit("GIVE CHANGE:", currentBalance, getBalance());
       return "Your change is: "+ quarterCounter + " quarters, " + dimeCounter + " dimes, " + nickelCounter + " nickel";
    }


    public void purchaseAudit(String title, BigDecimal beginningBalance, BigDecimal endingBalance) {
        Date date = new Date();
        File destinationFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-blue-t3\\java\\Log.txt");
        try(PrintWriter dataOutput = new PrintWriter(
                new FileOutputStream(destinationFile,true))) {
            dataOutput.write(date.toString() + " " + title + " $" + beginningBalance + " $" + endingBalance + "\n");
        } catch (Exception e) {
            System.err.println("Cannot open the file for writing.");
        }
    }
}