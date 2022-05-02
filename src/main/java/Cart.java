package main.java;

import java.util.ArrayList;
import java.util.List;
import main.java.UnderAgeException;

public class Cart {

    //changed from protected to private and added getter method. Removing 'smell', Task 5 in assignment 6.
    private int userAge;

    //changed from protected to private and added getter method. Removing 'smell', Task 5 in assignment 6.
    private List<Product> cart;
    

    /**
     * Calculates the final cost after all savings and tax has been applied.
     * Also checks that the user is of age to purchase alcohol if it is in their
     * cart at checkout. Sales tax is always AZ tax. Calculation is based off of
     * the following prices and deals: Dairy -> $3 Meat -> $10 Produce -> $2 or
     * 3 for $5 Alcohol -> $8 Frozen Food -> $5 Alcohol + Frozen Food -> $10 If
     * there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     *
     * @return double totalCost
     * @throws main.java.UnderAgeException if age less than 21
     */
    public double calcCost() throws UnderAgeException {

        double subTotal = 0;  // hold the cart subtotal
        double saving=0;

        //refactoring duplicate code and eliminates  instance variables. Task 3.2
       /* int producecounter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;
        int meatCounter = 0;*/

		//added for-each instead of for-loop to calculate all items' total.  Task 5 in the assignment 6
        for(Product p: cart){
            subTotal +=p.getCost();

        }

       /* for (int i = 0; i < cart.size(); i++) {

            subTotal += cart.get(i).getCost();//add products price to subtotal
            // check for Produce
            if (cart.get(i).getClass() == Produce.class) {

                producecounter++;

                if (producecounter >= 3) { //if produce >=3 then discount applies

                    subTotal = subTotal - 1; //takes $1 off
                    producecounter = 0; // resest counter
                }
                // check for Alcohol
            } else if (cart.get(i).getClass() == Alcohol.class) {

                if (this.userAge >= 21) { //if age is legal to purchase Alcohol
                    alcoholCounter++; // increment alcohol counter
                    //check for alcoho+FrozenFood discount
                    if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {

                        alcoholCounter = 0;//reset the counter

                        subTotal = subTotal - 3;//apply $3 discount

                    }
                } else {          // otherwise buyer is underage and an exception is thrown
                    throw new UnderAgeException("The User is not of"
                            + " age to purchase alcohol!");
                }

            } else if (cart.get(i).getClass() == FrozenFood.class) {// check for FrozenFood

                frozenFoodCounter++;// increment the counter

                if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {//again, check for alcoho+FrozenFood discount

                    frozenFoodCounter = 0; //reset the counter
                    subTotal = subTotal - 3;// apply discount

                }

            } else if (cart.get(i).getClass() == Dairy.class) {// check for Dairy

                dairyCounter++;//increment the counter

            } else if (cart.get(i).getClass() == Meat.class) {//check for meat

                meatCounter++; // increment the counter

            }
        }*/
		
		//refactored the above codes to enhance flexibility and efficiency by eliminating conditional if-statement
        // and utilize the helper method instead conforming to Task 3.2 in assignment 6.
        saving=saving+singleProductSaving(getCart(),new Produce(),3,1);
        saving=saving+singleProductSaving(getCart(),new Alcohol(),0,0);
        saving=saving+singleProductSaving(getCart(),new FrozenFood(),0,0);
        saving=saving+singleProductSaving(getCart(),new Dairy(),0,0);
        saving=saving+singleProductSaving(getCart(),new Meat(),0,0);
        saving=saving+multipleProductSaving(getCart(),new Alcohol(),1, new FrozenFood(),1,3);
        subTotal=subTotal-saving;
        double tax = getTax(subTotal, "AZ"); // apply Arizona Tax
        double total = subTotal + tax;// add tax amount to total

        return total; //return Cart total with tax
    }

    // calculates how much was saved in the current shopping cart based on the deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: create white box tests and fix the method, reach at least 98% coverage
    public double Amount_saved() throws UnderAgeException {  // change teturn type to double to avoid precesion loss
        double subTotal = 0; // change type to double
        double costAfterSavings = 0;  // change type to double
		
		//refactoring the code and eliminating  instance variables. Task 3.1
       /* int producecounter = 0;  // change type to int
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;
        double saving=0;*/

		//added for-each instead of for-loop to calculate all items' total. Task 3.1 in the assignment 6
        for(Product p: getCart()){
            subTotal +=p.getCost();
            costAfterSavings +=p.getCost();

        }

        /*for (int i = 0; i < cart.size(); i++) {

            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            if (cart.get(i).getClass() == Produce.class) {
                producecounter++;
                if (producecounter >= 3) {
                    costAfterSavings -= 1;
                    producecounter = 0;
                }
            } else if (cart.get(i).getClass() == Alcohol.class) {//change the type checking from string to class type
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            } else if (cart.get(i).getClass() == Dairy.class) {// change to Dairy (logica; error)
                dairyCounter++; // change from frozenFoodCounter to dairyCounter
            } else {

                if (cart.get(i).getClass() == FrozenFood.class) {//change the type checking from string to class type
                    frozenFoodCounter++;// change from dairyCounter  to frozenFoodCounter
                }
                    if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                    costAfterSavings = costAfterSavings - 3; //changed (-) to (+) to calculate the amount saved (logical error)
                    alcoholCounter--;
                    frozenFoodCounter--;
                }
            }
        }*/
		
		//refactored the above codes to enhance flexibility and efficiency by eliminating conditional if-statement
        // and utilize the helper method instead. Task 3.1 in assignment 6.
        costAfterSavings=costAfterSavings-singleProductSaving(getCart(),new Produce(),3,1);

        costAfterSavings=costAfterSavings-singleProductSaving(getCart(),new Dairy(),0,0);

        costAfterSavings=costAfterSavings-singleProductSaving(getCart(),new Alcohol(),0,0);

        costAfterSavings=costAfterSavings-multipleProductSaving(getCart(),new Alcohol(),1, new FrozenFood(),1,3);
        return subTotal - costAfterSavings;
    }

    /**
     * This method is part of the refactoring of Task 3.1 in assignment 6.
     * This is a helper method to add more flexibility to th cart class.
     * the method will calculate and returns the discount for any given item.
     * this method is used in both, calcCost() and amountSaved() methods.
     * @param cart shopping cart
     * @param product product to calculate the discount for.
     * @param itemNum number of product items to be eligible for a discount.
     * @param discount number of discount items
     * @return saved amount
     * @throws UnderAgeException derAgeException is thrown if the user is underage fo alcohol purchase
     */
    public double singleProductSaving( List<Product> cart, Product product,
                                      int itemNum, int discount)throws UnderAgeException{
       double subTotal =0;
       double costAfterSavings =0;
       int productCounter=0;

        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            if (cart.get(i).getClass() == product.getClass()) {

                productCounter++;
                if((cart.get(i).getClass() == Alcohol.class)&& this. getAge()<21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }else{
                    if (productCounter >= itemNum) {
                        costAfterSavings -= discount;
                        productCounter = 0;
                    }


                }
            }
        }

       return subTotal - costAfterSavings;
    }

    /**
     * This method is part of the refactoring of Task 3.1 in assignment 6.
     * This is a helper method to add more flexibility to th cart class.
     * the method will calculate and returns the discount for any given combination of two items.
     * this method is used in both, calcCost() and amountSaved() methods.
     * @param cart shopping cart
     * @param product1 first product to calculate the discount for.
     * @param itemNum1 number of the items of the first product to be eligible for a discount.
     * @param product2 second product to calculate the discount for.
     * @param itemNum2 number of the items of the second product to be eligible for a discount.
     * @param discount number of discount items
     * @return saved amount
     * @throws UnderAgeException is thrown if the user is underage fo alcohol purchase
     */
    public double multipleProductSaving( List<Product> cart, Product product1,int itemNum1,Product product2,
                                       int itemNum2, int discount)throws UnderAgeException{
        double subTotal =0;
        double costAfterSavings =0;
        int product1Counter=0;
        int product2Counter=0;

        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            if (cart.get(i).getClass() == product1.getClass()) {

                product1Counter++;
            }
            if (cart.get(i).getClass() == product2.getClass()) {

                product2Counter++;
            }
                if((product1.getClass() == Alcohol.class || product2.getClass() == Alcohol.class) && this. getAge()<21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }else{
                    if (product1Counter >= itemNum1 && product2Counter >= itemNum1) {
                        costAfterSavings = costAfterSavings - discount; //changed (-) to (+) to calculate the amount saved (logical error)
                        product1Counter--;
                        product2Counter--;
                    }


                }
            }


        return subTotal - costAfterSavings;


    }

    /**
     * getter method to return the shopping cart. Task 5 assignment 6
     * @return the shopping cart
     */
    public List<Product> getCart(){


        return this.cart;
    }

    /**
     * getter method to return the user age. Task 5 assignment 6
     * @return the user age
     */
    public int getAge(){


        return this.userAge;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        double newTotal = 0;

        //SER316 TASK 2 SPOT- BUGS FIX
        if (twoLetterUSStateAbbreviation.equals("AZ")) {

            newTotal = totalBT * .08;
        }

        //SER316 TASK 2 SPOT- BUGS FIX
        if (twoLetterUSStateAbbreviation.equals("CA")) {

            newTotal = totalBT * .09;
        }

        //SER316 TASK 2 SPOT- BUGS FIX
        if (twoLetterUSStateAbbreviation.equals("NY")) {

            newTotal = totalBT * .1;
        }

        //SER316 TASK 2 SPOT- BUGS FIX
        if (twoLetterUSStateAbbreviation.equals("CO")) {

            newTotal = totalBT * .07;
        }

        return newTotal;
    }

    public void addItem(Product np) {
        getCart().add(np);
    }

    public boolean removeItem(Product productToRemove) {//SER316 TASK 2 SPOT- BUGS FIX
        boolean test = false;
        for (int i = 0; i < getCart().size(); i++) {
            if (getCart().get(i) == productToRemove) {
                getCart().remove(i);
                test = true;
                return test;
            }
        }
        return false;
    }

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}
