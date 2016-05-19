
/**
 * @author James M. Brewer
 * 
 * 2016.01.21
 * 
 */
class VendingMachine {
	private int amount = 0;
	private int[] itemCounts = {1, 1, 1}; //0 = Cola, 1 = Chips, 2 = Candy;
	private int[] prices = {100, 65, 50}; //0 = Cola, 1 = Chips, 2 = Candy;
	
	int availableChange = 2000;
	
	public boolean exactChange = false;
	
	private VendingDisplay vendingDisplay = new VendingDisplay();
	
	public boolean init() {
		exactChange = requireExactChange();
		
		if(exactChange) {
			vendingDisplay.displayMessage("EXACT CHANGE ONLY");
		} else {
			vendingDisplay.displayMessage("INSERT COIN");
		}
		
		return exactChange;
	}
	
	public boolean requireExactChange() {
		boolean required = false;
		
		if(availableChange < 100) {
			required = true;
		}
		
		return required;
	}
	
	
	public boolean validateCoin(String coin) {
		Boolean isValid = false;
		
		switch(coin) {
		case "Nickel":
		case "Dime":
		case "Quarter":
			isValid = true;
			break;
		}
		
		if(isValid) {
			
		}
		
		return isValid;
	}
	
	public int addCoin(String coin) {
		switch(coin) {
		case "Nickel":
			amount += 5;
			break;
		case "Dime":
			amount += 10;
			break;
		case "Quarter":
			amount += 25;
			break;
		}

		
		return getAmount();
	}
	
	public int returnChange() {
		int[] numCoins = {0, 0, 0};
		
		numCoins[0] = returnCoins("Quarter");
		amount -= numCoins[0] * 25;

		numCoins[1] = returnCoins("Dime");
		amount -= numCoins[1] * 10;
		
		numCoins[2] = returnCoins("Nickel");
		amount -= numCoins[2] * 5;
		
		System.out.println(numCoins[0] + " Quarter(s) returned");
		System.out.println(numCoins[1] + " Dime(s) returned");
		System.out.println(numCoins[2] + " Nickle(s) returned");
		
		return amount;
	}
		
	public int returnCoins(String denomination){
		int value = 0;
		int numCoins = 0;
		
		switch (denomination) {
		case "Quarter":
			value = 25;
			break;
		case "Dime":
			value = 10;
			break;
		case "Nickel":
			value = 5;
			break;
		}
		
		if(getAmount() != 0) {
			numCoins = getAmount() / value;
		}
			
		return numCoins;
	}
	
	public boolean dispenseProduct(String selection) {
		Boolean dispense = false;
		Boolean soldOut = false;
		String productMessage = "Insert Coins";
		
		if(!exactChange || hasExactChange(selection)) {
			switch(selection) {
			case "Cola":
				if(getAmount() >= prices[0]) {
					if(itemCounts[0] > 0) {
						
						itemCounts[0]--;
						amount -= 100;
						dispense = true;
					} else {
						soldOut = true;
					}
				}
				break;
			case "Candy":
				if(getAmount() >= prices[1]) {
					if(itemCounts[1] > 0) {
						itemCounts[1]--;
						amount -= 65;
						dispense = true;
					} else {
						soldOut = true;
					}
				}
				break;
			case "Chips":
				if(getAmount() >= prices[2]) {
					if(itemCounts[2] > 0 ) {
						itemCounts[2]--;
						amount -= 50;
						dispense = true;
					} else {
						soldOut = true;
					}
				}
				break;
			}
		} else {
			productMessage = "Exact Change Required!";
		}
		if(dispense) {
			productMessage = "Please remove your " + selection + " from the bin below";
		} else if(soldOut) {
			productMessage = "Sold Out";
		}
		
		System.out.println(productMessage);
		System.out.println("Amount: " + amount);
		
		return dispense;
	}

	public int getAmount() {
		return amount;
	}
	
	public boolean hasExactChange(String selection) {
		boolean exact = true;
		
		if(selection == "Cola" && amount != prices[0])
			exact = false;
		
		if(selection == "Chips" && amount != prices[1])
			exact = false;
		
		if(selection == "Candy" && amount != prices[2])
			exact = false;
			
		return exact;
	}
}