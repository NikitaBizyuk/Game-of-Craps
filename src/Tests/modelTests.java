package Tests;
/*
 * TCSS 305 - Fall 2024
 * Instructor - Tom Capaul
 * Assignment 6 - Game Of Craps
 */
import Model.CrapsPlayer;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a tester for the CrapsPlayer class
 * found in the Model package.
 * @author Nikita Bizyuk - nbizyu@uw.edu
 * @version 12-12-2024
 */
public class modelTests {

    //CrapsPlayer object
    CrapsPlayer myModel;

    //constructor for modelTest
    public modelTests(){
        myModel = new CrapsPlayer();
    }

    /**
     * setActive() tests both set and
     * getActive() in the model class.
     */
    @Test
    public void testActive() {
        myModel.setActive(true);
        assertTrue(myModel.getActive());
        myModel.setActive(false);
        assertFalse(myModel.getActive());
    }

    /**
     * This method test to make sure addition and
     * subtraction is handled correctly for the users
     * bank account.
     */
    @Test
    public void testBalance() {
        /*
         * At the beginning of the game
         * the balance should be zero.
         */
        assertEquals(0,myModel.getBalance());
        /*
         * After a balance is inputted by the user
         * the myBalance instance field should be
         * updated to reflect the users input.
         */
        myModel.setBalance(100);
        assertEquals(100,myModel.getBalance());
        /*
         * If the player places a 10 dollar bet and wins
         * then 10 should be multiplied by two and added to
         * the users balance. The new balance should be
         * equal to $120.
         */
        myModel.setBet(10);
        myModel.calculateMoney(true);
        assertEquals(120,myModel.getBalance());
        /*
         * If the player places a $50 bet and loses
         * then 50 should be deducted from the balance.
         * The new balance in this example would be
         *  $70.
         */
        myModel.setBet(50);
        myModel.calculateMoney(false);
        assertEquals(70,myModel.getBalance());
    }

    /**
     * This method checks to make sure
     * bets are being placed correctly and follow
     * the rules of the game.
     */
    @Test
    public void testBet() {
        myModel.setBalance(500);
        myModel.setBet(500);
        assertEquals(500, myModel.getBet());
        /*
         * If the user places a bet that's higher
         * than the balance, an exception should be thrown.
         */
        assertThrows(IllegalArgumentException.class, () -> {
            myModel.setBet(1000);
        });
    }

    /**
     * testRoll() checks to make sure that the program
     * only rolls numbers between1 and 6.
     */
    @Test
    public void testRoll() {
        ArrayList<Integer> list = new ArrayList<>();
        while(list.size() < 6) {
            myModel.rollDice();
            if(!(list.contains(myModel.getSum()))) {
                list.add(myModel.getSum());
            }
        }
        list.forEach(value -> assertTrue(value <= 12,
                "Sum greater than 12 found: " + value));
    }

    /**
     * testWinLoss() test win and loss
     * scenarios.
     */
    @Test
    public void testWinLoss() {
        CrapsPlayer test = new CrapsPlayer();
        int cntr = 0;
        System.out.println(test.getWins() + " "
                + test.getHouseWins());
        test.rollDice();
        if(test.getSum() == 7 || test.getSum() == 11){
            cntr++;
            test.incrementWins();
            assertEquals(1,test.getWins());
        }
        if (test.getSum() == 2 || test.getSum() == 3
                || test.getSum() == 12){
            cntr++;
            test.incrementHouse();
           assertEquals(1,test.getHouseWins());
        }
        if(cntr == 0){
            test.setPoint(test.getSum());
            do {
                test.rollDice();
                if(test.getSum() == 7){
                    cntr++;
                    test.incrementHouse();
                    assertEquals(1,test.getHouseWins());
                    break;
                }
            } while(test.getSum() != test.getPoint());
                if(cntr == 0) {
                    test.incrementWins();
                    assertEquals(1, test.getWins());
                }
        }
    }
}


