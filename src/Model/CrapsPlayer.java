package Model;
/*
 * TCSS 305 - Fall 2024
 * Instructor: Tom Capaul
 * Assignment 6 - Game of Craps
 */
import java.util.Random;

/**
 * This class generates a CrapsPlayer object which stores
 * all the code required for the backend portion of this
 * project.
 * @ author Nikita Bizyuk - nbizyu@uw.edu
 * @version December 10th 2024
 */
public class CrapsPlayer  {

    // Boolean representing the status of the game.
    private boolean myIsActive;

    // Integer representing the value
    // of dice one
    private int myDiceOne;

    // Integer representing the value
    // of dice two.
    private int myDiceTwo;

    // Integer representing the sum
    // of the two dices.
    private int mySum;

    // Integer representing the
    // point value.
    private int myPoint;

    // double value representing
    // the users balance.
    private double myBalance;

    //double value representing the
    // amount of money being bet on.
    private double myBet;

    // Integer value representing the
    // number of wins the user has.
    private int myWins;

    // Integer value representing
    // the number of wins the house has.
    private int myHouseWins;

    // Integer value representing
    // the number of rolls thrown.
    private int myRolls;

    /**
     * Constructor for the CrapsPlayer object.
     * Gives definition to the instance fields.
     */
    public CrapsPlayer() {
        super();
        myIsActive = true;
        mySum = 0;
        myWins = 0;
        myHouseWins = 0;
        myRolls = 0;
        myDiceOne = 0;
        myDiceTwo = 0;
    }

    /**
     * setActive() sets the myActive instance field
     * to true or false depending on the status of the game.
     * @param endGame passes a boolean value
     */
    public void setActive(boolean endGame) {
        myIsActive = endGame;
    }

    /**
     * getActive() returns the boolean value
     * stored in the myActive instance Field.
     * @return True or False
     */
    public boolean getActive() {
        return myIsActive;
    }

    /**
     * rollDice() rolls the dice, which changes
     * the values stored in myDiceOne, myDiceTwo and then
     * calls the setSum() method which adds
     * the two newly generated numbers.
     */
    public void rollDice() {
        myRolls++;
        Random rand = new Random();
        int diceOneRoll = rand.nextInt(5) + 1;
        int diceTwoRoll = rand.nextInt(5) + 1;
        myDiceOne = diceOneRoll;
        myDiceTwo = diceTwoRoll;
        setSum(myDiceOne + myDiceTwo);
    }

    /**
     * getDiceOne() returns the value stored
     * in myDiceOne instance Field.
     * @return an Integer value
     */
    public int getDiceOne() {
        return myDiceOne;
    }

    /**
     * getDiceTwo() returns the value stored
     * in the myDiceTwo instance field.
     * @return an Integer value.
     */
    public int getDiceTwo() {
        return myDiceTwo;
    }

    /**
     * setSum() updates the sum of two dice
     * after the users roll.
     * @param theSum passes the sum of two dice.
     */
    public void setSum(int theSum) {
        mySum = theSum;
    }

    /**
     * getSum() returns an integer value stored
     * in the mySum instance field.
     * @return an integer value.
     */
    public int getSum() {
        return mySum;
    }

    /**
     * setPoint() stores a new integer
     * value in the myPoint instance field
     * depending on what sum value the first
     * roll produced.
     * @param thePoint passes an integer value.
     */
    public void setPoint(int thePoint) {
        myPoint = thePoint;
    }

    /**
     * getPoint returns the value stored in
     * the myPoint instance field.
     * @return an integer value.
     */
    public int getPoint() {
        return myPoint;
    }

    /**
     * setBalance() updates the user balance, which is
     * the value stored in the myBalance instance field.
     * @param theBalance passes a double value
     *                   representing the new balance.
     */
    public void setBalance(double theBalance) {
        if(theBalance == (int)theBalance) {
            myBalance = theBalance;
        }
    }

    /**
     * getBalance() return the value stored in the
     * myBalance instance field.
     * @return a double representing the users balance.
     */
    public double getBalance() {
        return myBalance;
    }

    /**
     * setBet() updates the state of the myBet
     * instance field.
     * @param theBet passes a double value that
     *               represents the players bet.
     */
    public void setBet(double theBet) {
        if(getBalance() - theBet < 0) {
            throw new IllegalArgumentException();
        } else {
            myBet = theBet;
        }
    }

    /**
     * getBet() returns the value stored in the
     * myBet instance field.
     * @return a double value representing the players bet.
     */
    public double getBet() {
        return myBet;
    }

    /**
     * incrementWins() updates the amount of
     * wins the player has. This value is stored
     * in the myWins instance field.
     */
    public void incrementWins() {
        myWins++;
    }

    /**
     * geetWins() returns the value stored in the
     * myWins instance field.
     * @return an integer value representing the
     * amount of wins the player has.
     */
    public int getWins() {
        return myWins;
    }

    /**
     * getRolls() return an integer representing
     * the number of rolls that have been thrown.
     * @return an integer value.
     */
    public int getRolls() {
        return myRolls;
    }

    /**
     * incrementHouse() updates the amount of wins
     * the house has. this value is stored in
     * the myHouseWins instance field.
     */
    public void incrementHouse() {
        myHouseWins++;
    }

    /**
     * getHouseWins() return the integer value
     * stored in the myHouseWins instance field.
     * @return an integer value.
     */
    public int getHouseWins() {
        return myHouseWins;
    }

    /**
     * setHouseWins() updates the value stored in the
     * myHouseWins instance field.
     * @param theNum passes an integer value.
     */
    public void setHouseWins(int theNum) {
        myHouseWins = theNum;
    }

    /**
     * calculateMoney() calculates the amount of money
     * the player has either won or lost.
     * @param theWin passes a boolean value that states
     *               whether the player won or lost.
     */
    public void calculateMoney(boolean theWin) {
        if(theWin == true) {
            double bet = getBet();
            double originalAmount = getBalance();
            setBalance((bet * 2) + originalAmount);
        } else {
            setBalance(getBalance() - getBet());
        }
    }

    /**
     * setRoll() updates the amount of rolls that
     * have been thrown.
     * @param theNum passes a value storing the
     *               amount of rolls.
     */
    public void setRoll(int theNum) {
        myRolls = theNum;
    }
}
