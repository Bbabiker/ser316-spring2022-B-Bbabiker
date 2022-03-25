package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import main.java.Cart;
import main.java.Cart1;
import main.java.Cart2;
import main.java.Cart3;
import main.java.Cart4;
import main.java.Cart5;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart0.class},
            {Cart1.class},
            {Cart2.class},
            {Cart3.class},
            {Cart4.class},
            {Cart5.class}
        };
        return Arrays.asList(classes);
    }

    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }

   // A sample Cart

    Cart cart1, cart2, cart3, cart4, cart5, cart6;
    double cart1Expected, cart2Expected, cart3Expected, cart4Expected, cart5Expected, cart6Expected;


    
    // check for Alcohol+Frozen along with other items
    @org.junit.Before
    public void setUp() throws Exception {

        // all carts should be set up like this

        // cart created with an age 40 shopper
        cart1 = createCart(40);
        for (int i = 0; i < 2; i++) {
            cart1.addItem(new Alcohol());
            
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }

        cart1Expected = 70.2;
    }
    
    
    // test when atlest one item from each product is in the cart
    @org.junit.Before
    public void setUp2() throws Exception {

        
        // cart created with an age 40 shopper
        cart2 = createCart(40);
        
        cart2.addItem(new Alcohol());
     
        cart2.addItem(new Dairy());
      
        cart2.addItem(new Meat());
        
        cart2.addItem(new Produce());
        cart2.addItem(new FrozenFood());

        cart2Expected = 27;
    }
    
    
    
    
     // test when atlest one dicount is applicaple for items in the cart
    @org.junit.Before
    public void setUp3() throws Exception {

       
        
         cart3 = createCart(40);
         
        for (int i = 0; i < 2; i++) {
            cart3.addItem(new Alcohol());
       }
       for(int i = 0; i < 2; i++) {
            cart3.addItem(new Dairy());
        }
       for(int i = 0; i < 2; i++) {
            cart3.addItem(new Meat());
       }
       
       for(int i = 0; i < 2; i++) {
        cart3.addItem(new Produce());
       }
       
       for(int i = 0; i < 2; i++) {
        cart3.addItem(new FrozenFood());
       }
        
        
       cart3Expected = 54;
    }
    

    // check for large number of items in the cart and if a discount is applicaple
     @org.junit.Before
    public void setUp4() throws Exception {

        
         cart4 = createCart(40);
         
        for (int i = 0; i < 100; i++) {
            cart4.addItem(new Alcohol());
       }
       
       for(int i = 0; i < 100; i++) {
            cart4.addItem(new Meat());
       }
       
       for(int i = 0; i < 100; i++) {
        cart4.addItem(new Produce());
       }
       
       for(int i = 0; i < 100; i++) {
        cart4.addItem(new FrozenFood());
       }
        
         cart4Expected = 2340.36;
    }
    
    
    // check for Alcohol age restruction
     @org.junit.Before
    public void setUp5() throws Exception {

        // cart created with an age 21 shopper
        cart5 = createCart(21);
        
        cart5.addItem(new Alcohol());
       

        cart5Expected = 8.64;
    }
    
    
    // check for Alcohol age restruction
      @org.junit.Before
    public void setUp6() throws Exception {

         // cart created with an age 20 shopper
        cart6 = createCart(20);
       
        cart6.addItem(new Alcohol());
      

        cart6Expected = 0;
    }
    
    
    
    

    // check for Alcohol+Frozen along with other items
    @Test
    public void calcCostCart1() throws UnderAgeException {
        
        double amount = cart1.calcCost();
        
        assertEquals(cart1Expected, amount, .01);
       
    }
    
    
    // test when atlest one item from each product is in the cart
    // used EP and BVA
    @Test
    public void calcCostCart2() throws UnderAgeException {
        
        double amount = cart2.calcCost();
        
        assertEquals(cart2Expected, amount, .01);
        
        
    }
    
     // test when atlest one dicount is applicaple for items in the cart
    // used EP and BVA
    @Test
    public void calcCostCart3() throws UnderAgeException {
        
        double amount = cart3.calcCost();
        
        assertEquals(cart3Expected, amount, .01);
    
       
    }
    
    
    // check for large number of items in the cart and if a discount is applicaple
    //used EP and BVA
    @Test
    public void calcCostCart4() throws UnderAgeException {
       
        double amount = cart4.calcCost();
        
        assertEquals(cart4Expected, amount, .01);
    }
    
    
    // check for Alcohol age restruction
    //used EP and BVA
    @Test
    public void calcCostCart5() throws UnderAgeException {
       
        double amount = cart5.calcCost();
       
        assertEquals(cart5Expected, amount, .01);
    }
    
    
    // check for Alcohol age restruction
    // used EP and BVA
    @Test
    public void calcCostCart6() throws UnderAgeException {
        
        double amount = cart6.calcCost();
        
        assertEquals(cart6Expected, amount, .01);
    }
    
    
    
}
