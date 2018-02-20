package edu.towson.cis.cosc442.project2.vendingmachine;

public class Driver {

	public static void main(String[] args) 
	{
		VendingMachine vendMachine = new VendingMachine();
		vendMachine.addItem(new VendingMachineItem("pringles", .3), "A");
		System.out.println(vendMachine.getBalance());
		vendMachine.insertMoney(5.00);
		System.out.println(vendMachine.makePurchase("A"));
		System.out.println(vendMachine.makePurchase("A"));
		//VendingMachineItem item = vendMachine.getItem("A");
		//item.getName();
		//System.out.println(vendMachine.removeItem("A"));
	}

}
