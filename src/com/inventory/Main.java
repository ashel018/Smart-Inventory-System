package com.inventory;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SMART INVENTORY SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Stock");
            System.out.println("4. Delete Product");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
           System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> ProductDAO.addProduct();
                case 2 -> ProductDAO.viewProducts();
                case 3 -> ProductDAO.updateStock();
                case 4 -> ProductDAO.deleteProduct();
                case 5 -> OrderDAO.placeOrder();
                case 6 -> OrderDAO.viewOrders();
                case 7 -> {
                    System.out.println("👋 Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice");
            }
        }

	}

}
