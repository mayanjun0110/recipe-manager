package assn3;
/**File name:  Recipe.java
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
public class Recipe {
    private String name;
    private double flour;
    private double sugar;
    private double eggs;
    private double butter;
    private double yeast;
    private int quantity;
    
    /**
     * Constructs a new Recipe instance with the specified details.
     * 
     * @param name the name of the recipe
     * @param flour the amount of flour required (in grams)
     * @param sugar the amount of sugar required (in grams)
     * @param eggs the number of eggs required
     * @param butter the amount of butter required (in grams)
     * @param yeast the amount of yeast required (in grams)
     * @param quantity the quantity of the dish produced
     */
	public Recipe(String name, double flour, double sugar, double eggs, double butter, double yeast, int quantity) {
		
		this.name = name;
		this.flour = flour;
		this.sugar = sugar;
		this.eggs = eggs;
		this.butter = butter;
		this.yeast = yeast;
		this.quantity = 0;
	}

	/**
     * @return the name of the recipe
     */
	public String getName() {
		return name;
	}

	/**
     * @param name the new name of the recipe
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * @return the amount of flour
     */
	public double getFlour() {
		return flour;
	}

	 /** 
     * @param flour the new amount of flour
     */
	public void setFlour(double flour) {
		this.flour = flour;
	}

	 /**
     * @return the amount of sugar
     */
	public double getSugar() {
		return sugar;
	}

	 /**
     * @param sugar the new amount of sugar
     */
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	/** 
     * @return the number of eggs
     */
	public double getEggs() {
		return eggs;
	}

	 /**
     * @param eggs the new number of eggs
     */
	public void setEggs(double eggs) {
		this.eggs = eggs;
	}
	
	 /**
     * @return the amount of butter
     */
	public double getButter() {
		return butter;
	}

	 /**
     * @param butter the new amount of butter
     */
	public void setButter(double butter) {
		this.butter = butter;
	}

	 /**
     * @return the amount of yeast
     */
	public double getYeast() {
		return yeast;
	}

	/**
     * @param yeast the new amount of yeast
     */
	public void setYeast(double yeast) {
		this.yeast = yeast;
	}
	
	 /**
     * @return the quantity of the dish
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the new quantity of the dish
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
	