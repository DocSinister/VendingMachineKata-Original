import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

public class VendorMachineTest {
	
	@InjectMocks
	private VendingMachine vMach;
	
	@Mock
	private VendingDisplay vendingDisplay;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);		
	}
	
	@Test
	public void validateCoinWillReceiveANickelAndReturnTrue() {
		assertEquals(true, vMach.validateCoin("Nickel"));
	}
	
	@Test
	public void validateCoineWillReceiveADimeAndReturnTrue() {
		assertEquals(true, vMach.validateCoin("Dime"));	
	}

	@Test
	public void validateCoineWillReceiveAQuarterAndReturnTrue() {
		assertEquals(true, vMach.validateCoin("Quarter"));	
	}
	
	@Test
	public void validateCoineWillReceiveAPennyAndReturnFalse() {
		assertEquals(false, vMach.validateCoin("Penny"));	
	}
	
	@Test
	public void addCoinWillReceiveNickelAndReturnFiveCents() {
		assertEquals(5, vMach.addCoin("Nickel"));	
	}
	
	@Test
	public void addCoinWillReceiveDimeAndReturnTenCents() {
		assertEquals(10, vMach.addCoin("Dime"));	
	}
	
	@Test
	public void addCointWillReceiveQuarterAndReturnTwentyFiveCents() {
		assertEquals(25, vMach.addCoin("Quarter"));
	}
	
	@Test
	public void dispensePoductWillDispenseColaIfAmountGreaterOrEqualTo100() {
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		
		assertEquals(true, vMach.dispenseProduct("Cola"));
	}
	
	@Test
	public void dispensePoductWillDispenseCandyIfAmountGreaterOrEqualTo65() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Dime");
		vMach.addCoin("Nickel");
		assertEquals(true, vMach.dispenseProduct("Candy"));
	}
	
	@Test
	public void dispenseProductWillDispenseChipsIfAmountGreaterOrEqualTo50() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		
		assertEquals(true, vMach.dispenseProduct("Chips"));		
	}
	
	@Test
	public void addCoinWillReceiveANickelDimeAndQuarterAndGetAmountWillReturnFourtyCents() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Nickel");
		vMach.addCoin("Dime");
		vMach.addCoin("Quarter");
		
		assertEquals(40, vMach.getAmount());
	}
	
	@Test
	public void addQuarterToVendingMachineThenReturnCoinsWillReturnOneQuarter() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Quarter");
		assertEquals(1, vMach.returnCoins("Quarter"));
	}
	
	@Test
	public void addDimeToVendingMachineThenReturnCoinsWillReturnOneDime() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Dime");
		assertEquals(1, vMach.returnCoins("Dime"));
	}
	
	@Test
	public void addNickelToVendingMachineThenReturnCoinsWillReturnOneNickel() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Nickel");
		assertEquals(1, vMach.returnCoins("Nickel"));
	}
	
	@Test
	public void returnChangeReturnsAllCoinsAndZerosOutAmount() {
		VendingMachine vMach = new VendingMachine();

		vMach.addCoin("Quarter");
		vMach.addCoin("Dime");
		vMach.addCoin("Nickel");
		
		assertEquals(0, vMach.returnChange());
	}
	
	@Test
	public void dispenseProductWillDisplaySoldOutMessageIfNoProudctIsLeft() {
		VendingMachine vMach = new VendingMachine();
		
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		
		vMach.dispenseProduct("Cola");
		
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		
		assertEquals(false, vMach.dispenseProduct("Cola"));
	}
	
	@Test
	public void dispenseProductWillDisplayExactChangeRequiredMessageIfMoreChangeAddedThanRequired() {
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		vMach.addCoin("Quarter");
		
		assertEquals(true, vMach.dispenseProduct("Cola"));
	}
	
	@Test
	public void shouldDisplayExactChangeOnlyWhenBankIsNotSufficientToMakeChange() {
		// given insufficient coins
		vMach.availableChange = 99;
		
		// when I turn on the machine
		vMach.init();
		
		// the display will read (EXACT CHANGE ONLY)
		Mockito.verify(vendingDisplay).displayMessage("EXACT CHANGE ONLY");
	}
	
	@Test
	public void shouldDisplayInsertCoinWhenBankIsSufficientToMakeChange() {
		// given insufficient coins
		vMach.availableChange = 101;
		
		// when I turn on the machine
		vMach.init();
		
		// the display will read (EXACT CHANGE ONLY)
		Mockito.verify(vendingDisplay).displayMessage("INSERT COIN");
	}
}