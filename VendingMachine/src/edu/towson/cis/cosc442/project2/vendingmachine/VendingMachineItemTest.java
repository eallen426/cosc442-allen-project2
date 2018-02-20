package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {
	
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
	public void testGetName() {
		vendMachine.addItem(new VendingMachineItem("chips ahoy", .5), "C");
		VendingMachineItem item = vendMachine.getItem("C");
		assertEquals("chips ahoy", item.getName());
	}

	@Test
	public void testGetPrice() {
		vendMachine.addItem(new VendingMachineItem("doritos", .4), "B");
		VendingMachineItem item = vendMachine.getItem("B");
		assertEquals(.4, item.getPrice(), .001);
	}
	
	@Test
	public void priceLessThanZeroException() {
		try {
			VendingMachineItem item3 = new VendingMachineItem("chips ahoy", -.5);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "Pricecannot be less than zero";
	        assertEquals( expected, e.getMessage());
	    } 
	}
}
