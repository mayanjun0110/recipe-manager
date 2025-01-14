package assn3;
/**File name:  RecipeManagerTest.java
@author: Yanjun Ma 041141539
Course: CST8284 – 321
Assignment: Assignment 03
Date:  Dec 1st
Professor: Moshiur Rahman
Purpose: Collection,List,Map,fileIO
Class list: Recipe.java
            RecipeManager.java
            RecipeManagerTest.java
*/
import java.util.*;

/**
 * RecipeManagerTest is an application that interacts with the RecipeManager class
 * to display recipes, take user orders, and generate a shopping list based on the selected recipes.
 * 
 * It provides a menu-driven interface to:
 * - Display available recipes.
 * - Place and update orders for specific recipes.
 * - Generate and print a shopping list for ordered recipes.
 * - Save the shopping list to a file.
 * 
 * This class demonstrates file reading, user input handling, and simple map-based operations.
 */

public class RecipeManagerTest {
		
	RecipeManager manager = new RecipeManager(); 
	List<Recipe> recipes = manager.getRecipes();
	Scanner scanner = new Scanner(System.in);
	
	// Tracks user orders (recipeIndex -> quantity)
    Map<Integer, Integer> orders = new HashMap<>();  

	public static void main(String[] args) {
	      RecipeManagerTest test = new RecipeManagerTest();
	      test.go();
	}
	/**
     * Main loop of the application.
     * Presents a menu to the user and processes their choices until they decide to exit.
     */
	public void go() {      
		manager.readRecipes("recipelist.txt");
        boolean running = true;
        System.out.println("\nWelcome to Yanjun Recipe Manager");
        
        while (running) {
        	System.out.println("Please select one of the following options:");
            System.out.println("1. Show available recipes.");
            System.out.println("2. Create Shopping List.");
            System.out.println("3. Print Shopping List.");
            System.out.println("4. Quit Program.");
            System.out.println("0. to reprint this menu."); 
            
            try {
                int choice = getValidInput("Please enter your choice (0-4): ", 0, 4);

                if (choice == 1) {
                    displayRecipes();    
                } else if (choice == 2) {
                    placeOrder(); 
                } else if (choice == 3) {
                    createShoppingList();  
                } else if (choice == 4) {
                	running = false;  
                    System.out.println("Exiting Recipe Manager.");
                } else if (choice == 0) {
                    continue;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please only type digits. Valid inputs are numbers from 0 to 4.");
                scanner.nextLine(); 
            }
        }
	}
       
    private void displayRecipes() {
        System.out.println("Available Recipes:");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getName());
        }
    }


    /**
     * Allows the user to place or update an order for a recipe.
     * Prompts the user to select a recipe and specify the quantity. 
     * Updates the orders map object.
     */
    private void placeOrder() {
        int recipeIndex = getValidInput("Which bread would you like? (1-" + recipes.size() + "): ", 1, recipes.size());
        int inputQuantity = getValidInput("How much of this bread would you like? (Negative to reduce): ", Integer.MIN_VALUE, Integer.MAX_VALUE);

        int currentQuantity = orders.getOrDefault(recipeIndex, 0); 
        int updatedQuantity = currentQuantity + inputQuantity;

        if (updatedQuantity < 0) {
            System.out.println("Invalid order: resulting quantity cannot be negative.");
        } else {
            orders.put(recipeIndex, updatedQuantity);
            System.out.println("Order updated successfully.\n");
        }
    }

    /**
     * Generates a shopping list based on the user's orders.
     * totalIngredients(ingredient -> total gram)
     */
    private void createShoppingList() {
        Map<String, Double> totalIngredients = new HashMap<>();
        StringBuilder shoppingList = new StringBuilder("Shopping List:\n");

        orders.forEach((recipeIndex, quantity) -> {
            if (quantity > 0) {
                Recipe recipe = recipes.get(recipeIndex - 1);
                shoppingList.append(quantity).append(" ").append(recipe.getName()).append(" loaf/loaves.\n");
                //accumulate 
                totalIngredients.merge("flour", recipe.getFlour() * quantity, Double::sum);
                totalIngredients.merge("sugar", recipe.getSugar() * quantity, Double::sum);
                totalIngredients.merge("eggs", recipe.getEggs() * quantity, Double::sum);
                totalIngredients.merge("butter", recipe.getButter() * quantity, Double::sum);
                totalIngredients.merge("yeast", recipe.getYeast() * quantity, Double::sum);
            }
        });

        if (totalIngredients.isEmpty()) {
            System.out.println("No bread ordered. Shopping list is empty.");
            return;
        }

        shoppingList.append("\nYou will need a total of:\n");
        totalIngredients.forEach((ingredient, total) -> {
            if (total > 0) {
                if ("eggs".equals(ingredient)) {
                    shoppingList.append(total).append(" egg(s)\n");
                } else {
                    shoppingList.append(total).append(" grams of ").append(ingredient).append("\n");
                }
            }
        });

        System.out.println(shoppingList);
        System.out.print("Do you want to save this list (Y/n)? ");
        scanner.nextLine();  
        String saveList = scanner.nextLine();
        if (saveList.equalsIgnoreCase("Y")) {
            manager.saveShoppingList("shoppinglist.txt", shoppingList.toString());
        }
    }

    /**
     * Prompts the user for an integer input choice within a specified range.
     * Ensures the input is valid and falls within the range.
     *
     * @param prompt The message to display to the user.
     * @param min    The minimum valid input value.
     * @param max    The maximum valid input value.
     * @return A valid integer input within the range.
     */
    private int getValidInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Invalid input: Please enter a number between " + min + " and " + max + ".");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); 
            } catch (IllegalStateException e) {
                System.out.println("Scanner is closed. Unable to read input.");
            }          
        }
    }
}       

