/*
	Sabas Rojas
	March 17, 2022

	This project is meant to simulate the menu that a customer 
	from an airline would use to add a flight, view their trip, 
	manage their trip, checkout, and/or exit the menu/program.
*/

import java.util.Scanner;
import java.io.*;

public class AirlineMenu {

    public static void main (String [] args) throws IOException{

    String city = "";
    String cityX = "";
    String typeOfFlight = "";
    int option_Selected = 0;
    int one_or_round = 0;
    int numOfSeats = 0;
    double one_way = 0.0;
    double round_trip = 0.0;
    double totalPrice = 0.0;
    double price = 0.0;
    
    // Menu is always true
    while (true) {
      
	    // Welcome message
	    System.out.println("\nWELCOME TO ROJAS AIRLINES!");
	    System.out.println("Please select a choice below [1 - 5]: ");
	    System.out.println("  1. Add Flight\n" + "  2. View Trip\n" + "  3. Manage Trip\n" + "  4. Checkout\n" + "  5. Exit ROJAS Airlines");
	      
	    Scanner user_input = new Scanner(System.in); // Scanner for user input
	    System.out.print(">> ");
	    option_Selected = user_input.nextInt();   // Get the option number from the user
	      
        if (option_Selected == 1) {
	        File flight_data = new File ("flights.txt"); // Scanner for the txt file
	        Scanner file_Scanner = new Scanner (flight_data);
	        
	        file_Scanner.nextLine();
	        System.out.printf("+-------------------------------------------+%n");
	        System.out.printf("|          AVAILABLE FLIGHTS FOR EP         |%n");
	        System.out.printf("+----------------+-----------+--------------+%n");
	        System.out.printf("| %-7s  | %-3s | %-5s |\n", "     CITY    ", " ONE WAY ", " ROUND TRIP ");
	        System.out.printf("+----------------+-----------+--------------+%n");
        
	        // Read the file and show flights
	        while(file_Scanner.hasNext()){
	          city = file_Scanner.next();
	          one_way = file_Scanner.nextDouble();
	          round_trip = file_Scanner.nextDouble();
	          
	          System.out.printf("| %-14s | $ %-3.2f  |  $ %-7.2f   |\n", city, one_way, round_trip);
	          System.out.printf("+----------------+-----------+--------------+%n");
	        }
        
        	System.out.println("What flight would you like to get? Enter the name of the city");
        
	        // Get the flight from the user
	        file_Scanner = new Scanner(flight_data);
	        System.out.print(">> ");
	        cityX = user_input.next();
        
	        while (file_Scanner.hasNextLine()) {
	          String cityN = file_Scanner.next();
	          // If the city entered by the user matches the city in the file
		        if (cityN.equals(cityX)) {
		            System.out.println("What are you purchasing?");
		            System.out.println("  1. One-Way");
		            System.out.println("  2. Round Trip");
		            System.out.print(">> ");
		            double oneWay = file_Scanner.nextDouble();
		            double round = file_Scanner.nextDouble();
		        	
		            // Ask user if it is a one way or round trip
		            one_or_round = user_input.nextInt();
		            if (one_or_round == 1) {
		              price = oneWay;
		              typeOfFlight = "One-Way";
		            } 
		            else if (one_or_round == 2) {
		              price = round;
		              typeOfFlight = "Round Trip";
		            }
		            
		            // Ask how many seats will be needed
		            System.out.println("How many seats will you be purchasing? ");
		            System.out.print(">> ");
		            numOfSeats = user_input.nextInt();
		            totalPrice = price * numOfSeats;
		        }
	        }
        } 

	    else if (option_Selected == 2) {
	        // Give information about the trip
	        System.out.printf("----------------------------TRIP INFORMATION----------------------------%n");
	        System.out.println("EL PASO to " + cityX + "      [Total] " + totalPrice + "      [Type] " + typeOfFlight + "      [Seats] " + numOfSeats);

	    }
        
	    else if (option_Selected == 3) {
	        System.out.println("This option clears your cart.");
	        System.out.println("Are you sure you want to clear your trips? [type in YES or NO] ");
	        System.out.print(">> ");
	        String clear_cart = user_input.next();
	        boolean check_cart = true;
	        
	        if (clear_cart.equals("YES") || clear_cart.equals("yes")) {
	          totalPrice = 0.0;
	          cityX = "";
	          city = "";
	          typeOfFlight = "";
	          numOfSeats = 0;
	          check_cart = false;
	          System.out.println("Cart cleared.");
	        } 
	        else if (clear_cart.equals("NO") || (clear_cart.equals("no"))) {
	          System.out.println("Cart not changed.");
	          System.out.println("Going back to menu...");
	          check_cart = false;
	        } 
	        else if (check_cart) {
	          System.out.println("Enter YES or NO either in upper case or lower case");
	        }
	    } 

	    else if (option_Selected == 4) {
	        totalPrice = totalPrice * 1.0725;
	        System.out.println(" ");
	        System.out.println("Your current total is: $" + totalPrice);
	    
	        boolean card = true;
	        String cardNumber = "";
	        
	        System.out.println("Please enter a 16 digit visa/master card number");
	        System.out.print(">> ");
	        cardNumber = user_input.next();
	        while (card) {
	        	int cardNumber_Size = cardNumber.length();
		        if (cardNumber_Size < 16 || cardNumber_Size > 16) {
		            System.out.println("Enter a valid card number");
		            cardNumber = user_input.next();
		        } 
		        else if (cardNumber_Size == 16) {
		            System.out.println("Your purchase for:");
		            System.out.println("EL PASO to " + cityX + "      [Total] " + totalPrice + "      [Type] " + typeOfFlight + "       [Seats] " + numOfSeats);
		            System.out.println("was successfull. You paid $" + totalPrice +" using the card: " + cardNumber);
		            System.out.println("Have fun on your trip!");
		            card = false;
		            System.exit(0);
		        }
	        }
	    } 
	    
	    else if (option_Selected == 5) {
	        System.out.println("Thank you for using Rojas Airlines.");
	        System.out.println("Stay Safe! Stay Hydrated!");
	        System.exit(0);
	    } 

	    else if (option_Selected < 1 || option_Selected > 5) {
	        System.out.println("Not a valid option try again!");
	    }

	    }
	}
}
