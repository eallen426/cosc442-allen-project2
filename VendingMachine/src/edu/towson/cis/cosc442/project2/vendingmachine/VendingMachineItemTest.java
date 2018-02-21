package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * This class tests the functionality and exceptions of the VendingMachineItem 
 * class
 */
public class VendingMachineItemTest {
	
	/** Instantiating the vending machine. */
	VendingMachine vendMachine;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() throws Exception {
		vendMachine = new VendingMachine();
	}

	/**
	 * Tear down
	 */
	@After
	public void tearDown() throws Exception {
		vendMachine = null;
	}

	/**
	 * Test to verify the name getter method
	 */
	@Test
	public void testGetName() {
		vendMachine.addItem(new VendingMachineItem("chips ahoy", .5), "C");
		VendingMachineItem item = vendMachine.getItem("C");
		assertEquals("chips ahoy", item.getName());
	}

	/**
	 * Test to verify price getter method
	 */
	@Test
	public void testGetPrice() {
		vendMachine.addItem(new VendingMachineItem("doritos", .4), "B");
		VendingMachineItem item = vendMachine.getItem("B");
		assertEquals(.4, item.getPrice(), .001);
	}
	
	/**
	 * Test to verify no negatives used when setting price
	 */
	@Test
	public void priceLessThanZeroException() {
		try {
			VendingMachineItem item = new VendingMachineItem("chips ahoy", -.5);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Price cannot be less than zero";
	        assertEquals(expected, e.getMessage());
	    } 
	}
	
	/**
	 * Test to verify correct increments being used in price
	 */
	@Test
	public void invalidIncrement() {
		try {
			VendingMachineItem item = new VendingMachineItem("chips ahoy", .0017);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Price has to be in increments of 1 cent";
	        assertEquals(expected, e.getMessage());
	    } 
	}
}
