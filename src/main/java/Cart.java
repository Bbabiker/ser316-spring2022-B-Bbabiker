package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    public List<Product> cart;
    public int cartStorage;

    /**
     * Calculates the final cost after all savings and tax has been applied.Also checks that the user is of age to purchase alcohol if it is in their
 c   * art at checkout.
     * Sales tax is always AZ tax.

 Calculation is based off of the following prices and deals: Dairy -> $3
     * Meat -> $10 Produce -> $2 or 3 for $5 Alcohol -> $8 Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     *
     * If there is an alcohol product in the cart and the user is under 21, then
     * an UnderAgeException should be thrown.
     *
     * @return double totalCost
     * @throws main.java.UnderAgeException
     */
    public double calcCost() throws UnderAgeException {

        double subTotal = 0;  // hold the cart subtotal
        // counter for the products
        int produce_counter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;
        int meatCounter = 0;

        for (int i = 0; i < cart.size(); i++) {

            subTotal += cart.get(i).getCost();//add products price to subtotal
                // check for Produce
            if (cart.get(i).getClass() == Produce.class) {

                produce_counter++;

                if (produce_counter >= 3) { //if produce >=3 then discount applies

                    subTotal = subTotal - 1; //takes $1 off
                    produce_counter = 0; // resest counter
                }
                    // check for Alcohol
            } else if (cart.get(i).getClass() == Alcohol.class) {

                if (this.userAge >= 21) { // if age is legal to purchase Alcohol
                    alcoholCounter++; // increment alcohol counter
                     // check for alcoho+FrozenFood discount
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
        }

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
        int produce_counter = 0;  // change type to int
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;
        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            if (cart.get(i).getClass() == Produce.class) {
                produce_counter++;
                if (produce_counter >= 3) {
                    costAfterSavings -= 1;
                    produce_counter = 0;
                }
            } else if (cart.get(i).getClass() == Alcohol.class) {//change the type checking from string to class type
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            } else if (cart.get(i).getClass() == Dairy.class) {// change to Dairy (logica; error)
                dairyCounter++; // change from frozenFoodCounter to dairyCounter
            } else if (cart.get(i).getClass() == FrozenFood.class) {//change the type checking from string to class type
                frozenFoodCounter++;// change from dairyCounter  to frozenFoodCounter
                if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                    costAfterSavings = costAfterSavings - 3; //changed (-) to (+) to calculate the amount saved (logical error)
                    alcoholCounter--;
                    frozenFoodCounter--;
                }
            }
        }
        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        double newTotal = 0;
        switch (twoLetterUSStateAbbreviation) {
            case "AZ":
                newTotal = totalBT * .08;
                break;
            case "CA":
                newTotal = totalBT * .09;
                break;
            case "NY":
                newTotal = totalBT * .1;
            case "CO":
                newTotal = totalBT * .07;
                break;
            default:
            //return totalBT; // ommiting Default this way if a user enter an invalid State
            // the method returns 0
        }
        return newTotal;
    }

    public void addItem(Product np) {
        cart.add(np);
    }

    public boolean RemoveItem(Product productToRemove) {
        boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) == productToRemove) {
                cart.remove(i);
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
