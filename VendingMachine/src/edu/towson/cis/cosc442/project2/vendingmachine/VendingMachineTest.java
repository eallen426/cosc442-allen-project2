package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/*
 * This class tests the various functions and exceptions 
 * of the VendingMachine class
 */
public class VendingMachineTest {
	
	/** The vend machine. */
	VendingMachine vendMachine;

	/**
	 * Sets up a VendingMachine object to be used in all tests
	 */
	@Before
	public void setUp() throws Exception {
		vendMachine = new VendingMachine();
	}

	/**
	 * Sets the VendingMachine object to null
	 */
	@After
	public void tearDown() throws Exception {
		vendMachine = null;
	}

	/**
	 * Test add and remove item functionality
	 */
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
	
	/**
	 * Test for exception being thrown when an item is trying to \
	 * be entered in an occupied slot
	 */
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

	/**
	 * Test to make sure inserted money is accounted for
	 */
	@Test
	public void testInsertMoney() {
		vendMachine.insertMoney(5.00);
		assertEquals(5.00, vendMachine.getBalance(), .001);
	}
	
	/**
	 * Test to verify the inserted money is not a negative value
	 */
	@Test
	public void insertMoneyException_LessThanZero() {
		try {
			vendMachine.insertMoney(-2);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Invalid amount.  Amount must be >= 0";
	        assertEquals( expected, e.getMessage());
	    } 
	}
	
	/**
	 * Test to verify the inserted money is in increments no smaller than 1 cent
	 */
	@Test
	public void insertMoneyException_InvalidIncrement() {
		try {
			vendMachine.insertMoney(.0033);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Invalid amount. Must be in increments of 1 cent";
	        assertEquals( expected, e.getMessage());
	    } 
	}

	/**
	 * Test for exception thrown when an invalid item code is entered
	 */
	@Test
	public void invalidCodeException() {
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
	
	/**
	 * Test to verify the correct balance is calculated and returned
	 */
	@Test
	public void testGetBalance() {
		vendMachine.insertMoney(2.01);
		assertEquals(2.01, vendMachine.getBalance(), .001);
	}

	/**
	 * Test to verify item is removed when purchased successfully
	 */
	@Test
	public void testMakePurchase() {
		vendMachine.insertMoney(5.00);
		vendMachine.addItem(new VendingMachineItem("pringles", .3),  "A");
		assertEquals(true, vendMachine.makePurchase("A"));
	}
	
	/**
	 * Test for exception thrown when entered slot is empty
	 */
	@Test
	public void testMakePurchase_EmptySlotException() {
		try {
	        vendMachine.makePurchase("A");
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Slot A is empty -- cannot remove item";
	        assertEquals( expected, e.getMessage());
	    } 
	}
	
	/**
	 * Test for Insufficient Funds Exception error
	 */
	@Test
	public void testMakePurchase_InsufficientFundsException() {
		vendMachine.insertMoney(.2);
		vendMachine.addItem(new VendingMachineItem("pringles", .3),  "A");
		try {
	        vendMachine.makePurchase("A");
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Insufficient funds.";
	        assertEquals( expected, e.getMessage());
	    } 
	}
	
	/**
	 * Tests to make sure the balance is updated correctly after a 
	 * transaction is completed
	 * 
	 */
	@Test
	public void testMakePurchaseNewBalance() {
		vendMachine.insertMoney(5.00);
		vendMachine.addItem(new VendingMachineItem("pringles", .3),  "A");
		vendMachine.makePurchase("A");
		assertEquals(4.7, vendMachine.getBalance(), .0001);
		
	}

	/**
	 * Test return change
	 */
	@Test
	public void testReturnChange() {
		vendMachine.insertMoney(5.00);
		assertEquals(5, vendMachine.getBalance(), .0001);
	}

}
