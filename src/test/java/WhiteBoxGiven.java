package test.java;

import main.java.Alcohol;
import main.java.Cart;
import main.java.Dairy;
import main.java.FrozenFood;
import main.java.Produce;
import main.java.Product;
import main.java.UnderAgeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxGiven {

    Cart cart, cart1, cart2, cart3, cart4,cart5,cart6,cart7,cart8,cart9,cart10;

    @Before
    public void setUp() throws Exception {
        cart = new Cart(45);
         cart.addItem(new Produce());
    }
    
    @Before
    public void setUp1() throws Exception {
        cart1 = new Cart(45);
        
        for (int i = 0; i < 4; i++) {
            cart1.addItem(new Produce());
       }
        
    }
    
    
    
    @Before
    public void setUp2() throws Exception {
        cart2 = new Cart(20);
        
        
            cart2.addItem(new Alcohol());
       }
        
    @Before
    public void setUp3() throws Exception {
        cart3 = new Cart(21);
        
        
            cart3.addItem(new Alcohol());
       }
    
    
    @Before
    public void setUp4() throws Exception {
        cart4 = new Cart(21);
        
        
            cart4.addItem(new Dairy());
       }
    
    
    
    @Before
    public void setUp5() throws Exception {
        cart5 = new Cart(45);
        
        for (int i = 0; i < 4; i++) {
            cart5.addItem(new Alcohol());
            cart5.addItem(new FrozenFood());
      }
        
    }
    
    @Before
    public void setUp6() throws Exception {
        cart6 = new Cart(45);
        for (int i = 0; i < 4; i++) {
            cart6.addItem(new Alcohol());
        }
        
    }
    
    
    @Before
    public void setUp7() throws Exception {
        cart7 = new Cart(45);
         
    }
    
    
    @Before
    public void setUp8() throws Exception {
        cart8 = new Cart(45);
        cart8.addItem(new Alcohol());
        cart8.addItem(new Alcohol());
         
    }
    
    @Before
    public void setUp9() throws Exception {
        cart9 = new Cart(45);
         Alcohol alcohol = new Alcohol();
         FrozenFood frozen = new FrozenFood();
        cart9.addItem(alcohol);
      cart9.RemoveItem(frozen);
        cart9.RemoveItem(alcohol);
         
    }
    
    
    @Before
    public void setUp10() throws Exception {
        cart10 = new Cart(45);
         for (int i = 0; i < 4; i++) {
            cart10.addItem(new Alcohol());
            cart10.addItem(new FrozenFood());
             cart10.addItem(new Produce());
        }
         
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTax() {
        assertEquals(3.5, cart.getTax(50, "CO"), .01);
    }

    @Test
    public void Amount_save() throws UnderAgeException {
        double saving =cart.Amount_saved();
        assertEquals(0, saving, .01);
    }
    
     @Test
    public void Amount_save1() throws UnderAgeException {
        double saving =cart1.Amount_saved();
        assertEquals(1, saving, .01);
    }
    
    
    
    
    @Test(expected = UnderAgeException.class)
    public void Amount_save2() throws UnderAgeException {
        double saving =cart2.Amount_saved();
        assertEquals(0, saving, .01);
    }
    
    
    
    @Test
    public void Amount_save3() throws UnderAgeException {
        double saving =cart3.Amount_saved();
        assertEquals(0, saving, .01);
    }
    
    @Test
    public void Amount_save4() throws UnderAgeException {
        double saving =cart4.Amount_saved();
        assertEquals(0, saving, .01);
    }
    
    
    @Test
    public void Amount_save5() throws UnderAgeException {
        double saving =cart5.Amount_saved();
        assertEquals(12, saving, .01);
    }
    
    
    @Test
    public void Amount_save6() throws UnderAgeException {
        double saving =cart6.Amount_saved();
        assertEquals(0, saving, .01);
    }
    
    @Test
    public void Amount_save7() throws UnderAgeException {
        double saving =cart7.Amount_saved();
        assertEquals(0, saving, .01);
    }
    
    @Test
    public void Amount_save10() throws UnderAgeException {
        double saving =cart10.Amount_saved();
        assertEquals(13, saving, .01);
    }
    @Test
    public void addItem() throws UnderAgeException {
        int length =cart8.cart.size();
       // length+=1;
        assertEquals(2, length, .01);
    }
    
    @Test
    public void removeItem() throws UnderAgeException {
       
        int length =cart9.cart.size();
       // length+=1;
        assertEquals(0, length, .01);
    }
    
    
}