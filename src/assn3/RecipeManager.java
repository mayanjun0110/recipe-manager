package assn3;
/**File name:  RecipeManager.java
@author: Yanjun Ma 041141539
Course: CST8284 – 321
Assignment: Assignment 03
Date:  Dec 1st
Professor: Moshiur Rahman
Purpose: Collection,List,Map,fileIO，try-catch blocks
Class list: Recipe.java
            RecipeManager.java
            RecipeManagerTest.java
*/
import java.io.*;
import java.util.*;
import java.io.IOException;

/**
 * Manages a collection of recipes by reading from a file, generating shopping lists,
 * and saving them to a file. This class provides functionality to handle recipe data
 * and calculate required ingredients based on order quantities.
 * 
 * <p>Usage includes reading recipes from a formatted text file, generating a shopping list
 * based on requested quantities of recipes, and saving the resulting shopping list to a file.</p>
 * 
 * <p>The recipe file should be formatted as follows:
 * <pre>
 * Recipe [RecipeName]
 * flour 200
 * sugar 50
 * eggs 2
 * butter 100
 * yeast 10
 * </pre>
 * Each recipe should be separated by an empty line.</p>
 * 
 */
public class RecipeManager {
     private List<Recipe> recipes = new ArrayList<>(); 
     
     /**
      * Reads recipes from a specified file and populates the list of recipes.
      * @param filename the name of the file containing recipe data
      */
    public void readRecipes(String filename) {
    	try (Scanner scan = new Scanner(new File(filename))) {
    		
    		//loop through each line and starts with "Recipe",extracts the recipe name after "Recipe"
            while (scan.hasNextLine()) {
            	String line = scan.nextLine().trim();
            	if(line.startsWith("Recipe")) {
            		String recipeName = line.substring(7).trim();
            		
                    //ingredients(ingredient -> gram)
            		Map<String, Double> ingredients= new HashMap<>(); 
            		while (scan.hasNextLine()) {
            			line = scan.nextLine().trim();
            			if (line.isEmpty()) break;
            			
            			//Splits each line into two parts: the ingredient name and its quantity.
            			String[] parts = line.split(" ");
            			if (parts.length ==2) {
            				try {
            					String ingredient = parts[0];
            					double number = Double.parseDouble(parts[1]);//convert String to double
            					ingredients.put(ingredient, number);
          
            				}catch (NumberFormatException e) {
                                System.err.println("invalid data " + parts[1]);
            			    }
            		    }
            	    }
            		// get the value for each key, if an ingredient is missing, a default value of 0.0
            		double flour = ingredients.getOrDefault("flour",0.0);  
                    double sugar = ingredients.getOrDefault("sugar",0.0);  
                    double eggs = ingredients.getOrDefault("eggs",0.0);    
                    double butter = ingredients.getOrDefault("butter",0.0); 
                    double yeast = ingredients.getOrDefault("yeast",0.0);  

            		Recipe recipe=new Recipe(recipeName,flour,sugar,eggs,butter,yeast,1);
            		recipes.add(recipe);
                    }
                }          
    	} catch (FileNotFoundException e) {
            System.err.println("Error: Recipe file not found: " + filename);
        } 
    }
    /**
     * @return the list of Recipe objects.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }
    
    /**
     * Saves a shopping list to a specified file.
     * 
     * @param fileName the name of the file to save the shopping list to
     * @param shoppingList the shopping list content to save
     */
    public void saveShoppingList(String fileName, String shoppingList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(shoppingList);
            System.out.println("Shopping list saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error: Could not save shopping list.");
        }
    }

}