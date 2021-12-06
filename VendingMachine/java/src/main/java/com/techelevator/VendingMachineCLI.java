package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.MenuItem;
import com.techelevator.view.VendingMachine;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private VendingMachine vm = new VendingMachine();
	private Scanner userInput = new Scanner(System.in);


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void mainMenuRun() {
		vm.stockInventory();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				this.displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseRun();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				break;
			}
		}
	}


	public void purchaseRun() {
		try {
			while (true) {
				System.out.println("Your Current Balance is: " + vm.getBalance());
				String purchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (purchaseMenuChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					System.out.println("Please Enter Whole Dollar Amounts: ");
					String strUserDollarInput = userInput.nextLine();
					BigDecimal userDollarInput = new BigDecimal(strUserDollarInput);
					if(userDollarInput.compareTo(new BigDecimal(1)) < 0) {
						System.out.println("Please Enter Whole Dollar Amounts: ");
						strUserDollarInput = userInput.nextLine();
						userDollarInput = new BigDecimal(strUserDollarInput);
					}
					vm.addBalance(userDollarInput);
				} else if (purchaseMenuChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					if (vm.getBalance().compareTo(new BigDecimal(0)) <= 0) {
						System.out.println("Please Deposit Money Before Making A Selection");
					} else {
						System.out.println("Please Enter the Letter and Number of the Item You Wish To Purchase :");
						this.displayItems();
						String strSlotInput = userInput.nextLine();
						System.out.println(vm.purchaseItem(strSlotInput));
					}
				} else if (purchaseMenuChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					System.out.println(vm.returnChange());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Not a valid input. Please try again.");
		}

	}


	public void displayItems() {
		for (Map.Entry<String, MenuItem> display : vm.getMenuChoices().entrySet()) {
			String slotNumber = display.getKey();
			MenuItem nameAndPrice = display.getValue();
			if (nameAndPrice.getInventory() > 0) {
				System.out.println(slotNumber + " |" + " $" + nameAndPrice.getPrice() + " | " + nameAndPrice.getName() + " - " + nameAndPrice.getInventory());
			} else {
				System.out.println(slotNumber + " |" + " $" + nameAndPrice.getPrice() + " | " + nameAndPrice.getName() + " - " + "SOLD OUT");
			}
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.mainMenuRun();
	}
}



