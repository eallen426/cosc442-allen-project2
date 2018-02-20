package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	
	VendingMachine vendMachine;

	@Before
	public void setUp() throws Exception {
		vendMachine = new VendingMachine();
	}

	@After
	public void tearDown() throws Exception {
		vendMachine = null;
	}

	@Test
	public void testAddAndRemoveItem() {
		VendingMachineItem item1 = new VendingMachineItem("pringles", .3);
		VendingMachineItem item2 = new VendingMachineItem("doritos", .25);
		VendingMachineItem item3 = new VendingMachineItem("chips ahoy", .5);
		VendingMachineItem item4 = new VendingMachineItem("pop tarts", .4);
		vendMachine.addItem(item1, "A");
		vendMachine.addItem(item2, "B");
		vendMachine.addItem(item3, "C");
		vendMachine.addItem(item4, "D");
		assertEquals(item1, vendMachine.removeItem("A"));
		assertEquals(item2, vendMachine.removeItem("B"));
		assertEquals(item3, vendMachine.removeItem("C"));
		assertEquals(item4, vendMachine.removeItem("D"));
	}
	
	@Test
	public void addItemException() {
		VendingMachineItem item3 = new VendingMachineItem("chips ahoy", .5);
		try {
			vendMachine.addItem(item3, "C");
			vendMachine.addItem(item3, "C");
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Slot C already occupied";
	        assertEquals( expected, e.getMessage());
	    } 
	}

	/*@Test
	public void testRemoveItem() {
		vendMachine.addItem(new VendingMachineItem("pop tarts", .4), "D");
		//assertEquals(vendMachine.getItemArray()[3], vendMachine.removeItem("D"));
	}*/

	@Test
	public void testInsertMoney() {
		vendMachine.insertMoney(5.00);
		assertEquals(5.00, vendMachine.getBalance(), .001);
	}
	
	@Test
	public void insertMoneyException() {
		try {
			vendMachine.insertMoney(-2);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Invalid amount.  Amount must be >= 0";
	        assertEquals( expected, e.getMessage());
	    } 
	}

	@Test
	public void invalidCodeMessage() {
		VendingMachineItem item3 = new VendingMachineItem("chips ahoy", .5);
		try {
			vendMachine.addItem(item3, "E");
			fail();
		}
		catch (Exception e) {
	        final String expected = ("Invalid code for vending machine item");
	        assertEquals( expected, e.getMessage());
		}
	}
	@Test
	public void testGetBalance() {
		vendMachine.insertMoney(2.01);
		assertEquals(2.01, vendMachine.getBalance(), .001);
	}

	@Test
	public void testMakePurchase() {
		vendMachine.insertMoney(5.00);
		vendMachine.addItem(new VendingMachineItem("pringles", .3),  "A");
		assertEquals(true, vendMachine.makePurchase("A"));
	}
	
	@Test
	public void testMakePurchaseException() {
		try {
	        vendMachine.makePurchase("A");
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Slot A is empty -- cannot remove item";
	        assertEquals( expected, e.getMessage());
	    } 
	}
	
	@Test
	public void testMakePurchaseInsufficientFunds() {
		vendMachine.insertMoney(.2);
		vendMachine.addItem(new VendingMachineItem("pringles", .3),  "A");
		assertEquals(false, vendMachine.makePurchase("A"));
	}
	
	@Test
	public void testMakePurchaseNewBalance() {
		vendMachine.insertMoney(5.00);
		vendMachine.addItem(new VendingMachineItem("pringles", .3),  "A");
		vendMachine.makePurchase("A");
		assertEquals(4.7, vendMachine.getBalance(), .0001);
		
	}

	@Test
	public void testReturnChange() {
		vendMachine.insertMoney(5.00);
		assertEquals(5, vendMachine.getBalance(), .0001);
	}

}
