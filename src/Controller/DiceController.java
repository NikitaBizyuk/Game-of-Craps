package Controller;
/*
 * TCSS 305 - Fall 2024
 * Instructor: Tom Capaul
 * Assignment 6 - Game of Craps
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.CrapsPlayer;
import View.CrapsView;

/**
 * This class is the controller for the
 * MVC architecture that this game of craps
 * is made of. The responsibility for
 * this class is to mediate communication
 * between the front and back end of
 * the program.
 * @author Nikita Bizyuk - nbizyu@uw.edu
 * @version 12-12-2024
 */
public class DiceController {

    //CrapsView object
    private CrapsView myView;

    //CrapsPlayer object
    private CrapsPlayer myModel;

    // Stores the previous balance
    private String prev;

    /**
     * diceController() is the constructor for the
     * controller class.
     * @param theView passes a CrapsView object.
     * @param theModel passes a CrapsPlayer object.
     */
    public DiceController(CrapsView theView, CrapsPlayer theModel){
        myView = theView;
        myModel = theModel;
        myView.getRoll().setEnabled(false);
        myView.getExitItem().setEnabled(false);
        myView.getBetField().setEditable(false);
        myView.getBalanceField().setEditable(false);
        addListeners();
        prev = String.valueOf(myView.getBalanceField().getText());
    }

    /**
     * addListeners() adds listeners to all the
     * components in the GUI.
     */
    private void addListeners() {
        balanceFieldListener();
        betFieldListener();
        startListener();
        rollListener();
        myView.getRoll().addActionListener(e -> {
            // Play the system beep sound
            Toolkit.getDefaultToolkit().beep();
        });
        resetListener();
        exitListener();
        aboutListener();
        rulesListener();
    }

    /**
     * balanceFieldListener() adds a listener to
     * one of the JTextFields in the GUI.
     */
    public void balanceFieldListener() {
        myView.getBalanceField().addActionListener(e -> {
            // TODO Auto-generated method stub

            try {
                if(Integer.parseInt(myView.
                        getBalanceField().getText()) > 0) {
                    myModel.setBalance(Integer.
                            parseInt(myView.
                                    getBalanceField().getText()));
                    myView.getBalanceField().setEditable(false);
                    myView.getBalanceField().
                            setBackground(Color.lightGray);
                } else {
                    JOptionPane.
                            showMessageDialog(null,
                            "Invalid input! Please enter an " +
                                    "integer greater than zero.");
                }
            } catch(NumberFormatException bad) {
                JOptionPane.
                        showMessageDialog(null,
                                "Invalid input! Please enter an" +
                                        " integer greater than zero.");
            }
        });
    }

    /**
     * betFieldListener() adds a listener to
     * one of the JTextField components in the GUI.
     */
    public void betFieldListener() {
        myView.getBetField().addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                if(Integer.parseInt(myView.getBetField().getText()) <=
                        Integer.parseInt(myView.getBalanceField().getText()) &&
                        Integer.parseInt(myView.getBetField().getText()) > 0) {
                    myModel.setBet(Integer.
                            parseInt(myView.getBetField().getText()));
                    myView.getBetField().
                            setEditable(false);
                    myView.getBetField().
                            setBackground(Color.lightGray);
                    myView.getRoll().
                            setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input! Please enter an" +
                                    " integer greater than zero \n"
                            + "and less than or equal to the balance.");
                }
            } catch(NumberFormatException bad) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input! Please enter an " +
                                "integer greater than zero \n"
                        + "and less than or equal to the balance.");
            }
        });
    }

    /**
     * startListener() adds a listener to the
     * start JButton component in the GUI.
     */
    public void startListener() {
        myView.getStartItem().addActionListener(e -> {
            // TODO Auto-generated method stub
            myView.getBalanceField().setEditable(true);
            myView.getBetField().setEditable(true);
            myView.getExitItem().setEnabled(true);
            myModel.setActive(true);
        });
    }

    /**
     * continueRolling is a helper method that will be called in
     * rollListener method if the user did not win or lose
     * on their first roll.
     */
    public void continueRolling() {
        if(myModel.getSum() == myModel.getPoint()) {
            myModel.incrementWins();
            myView.setGamesWon(myModel.getWins());
            myModel.calculateMoney(true);
            myView.setBalanceField((int)myModel.getBalance());
            myView.setBetting(0);
            myView.getBetField().setEditable(true);
            myView.getBetField().setBackground(Color.WHITE);
            myView.getRoll().setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "Cogratulations! you rolled your" +
                            " point value before\n"
                            + "rolling a 7. 2 times your betting " +
                            "amount has been added to your balance.\n"
                            + "Please enter a new bet to continue " +
                            "playing or press exit to end the game.");
            myModel.setPoint(0);
            myView.setPoint(0);
            myModel.setRoll(0);
        }
        if(myModel.getSum() == 7) {
            myModel.calculateMoney(false);
            myView.setBalanceField((int)myModel.getBalance());
            myView.setBetting(0);
            JOptionPane.showMessageDialog(null,
                    "Game Over! You rolled a 7 " +
                            "before your point value!\n"
                            + "Your betting amount has been withdrawn" +
                            " from your balance.\n"
                            + "Please enter a new bet to continue playing" +
                            " or click exit to end the game.");
            myView.getBetField().setEditable(true);
            myView.getBetField().setBackground(Color.WHITE);
            myView.getRoll().setEnabled(false);
            myModel.incrementHouse();
            myView.setHouseWins(myModel.
                    getHouseWins());
            myModel.setPoint(0);
            myView.setPoint(0);
            myModel.setRoll(0);
            if(myModel.getBalance()==0) {
                myView.getBalanceField().
                        setEditable(true);
                myView.getBalanceField().
                        setBackground(Color.WHITE);
            }
        }
    }

    /**
     * rollListener() adds a listener to the
     * roll JButton component in the GUI.
     */
    public void rollListener() {
        myView.getRoll().addActionListener(e -> {
            // TODO Auto-generated method stub
            myModel.rollDice();
            System.out.println(myModel.getRolls());
            myView.setDiceOne(myModel.getDiceOne());
            myView.setDiceTwo(myModel.getDiceTwo());
            myView.setSum(myModel.getSum());
            if(myModel.getRolls() == 1 && (myModel.getSum() == 7
                    || myModel.getSum() == 11)) {
                myModel.incrementWins();
                myView.setGamesWon(myModel.getWins());
                myModel.calculateMoney(true);
                myView.setBalanceField((int)myModel.getBalance());
                myView.setBetting(0);
                JOptionPane.showMessageDialog(null,
                        "Congratulations! you rolled either a \n"
                        + "1,7, or 11 on your first roll. 2 times your " +
                                "betting amount\n"
                        + "has been added to your balance.\n"
                        + "Please enter a new bet to play again or hit " +
                                "exit to end the game.");
                myModel.setPoint(0);
                myView.setPoint(0);
                myModel.setRoll(0);
                myView.getBetField().setEditable(true);
                myView.getBetField().setBackground(Color.WHITE);
            } else if(myModel.getRolls() == 1 && (myModel.getSum()== 2
                    || myModel.getSum() == 3
                    || myModel.getSum() == 12)) {
                myModel.calculateMoney(false);
                myView.setBalanceField((int)myModel.getBalance());
                myView.setBetting(0);
                myView.getRoll().setEnabled(false);
                myView.getBetField().setEditable(true);
                myView.getBetField().setBackground(Color.WHITE);
                myModel.incrementHouse();
                myView.setHouseWins(myModel.getHouseWins());
                myModel.setRoll(0);
                myModel.setPoint(0);
                myView.setPoint(0);
                JOptionPane.showMessageDialog(null,
                        "Game Over! You rolled either a 1,2, or 3\n"
                        + "on your first roll." +
                                " There for the game is now over. \n"
                        + "The amount you" +
                                " betted on has been withdrawn from your balance.\n"
                        + "Please enter a new bet, or click exit to end the game.");
                if(myModel.getBalance()==0) {
                    myView.getBalanceField().setEditable(true);
                    myView.getBalanceField().setBackground(Color.WHITE);
                }
            }
            else {
                //continue rolling
                continueRolling();
            }
            if(myModel.getRolls() == 1) {
                myView.setPoint(myModel.getSum());
                myModel.setPoint(myModel.getSum());
            }
        });
    }

    /**
     * resetListener() adds a listener to the
     * reset JButton component on the GUI.
     */
    public void resetListener() {
        myView.getResetItem().addActionListener(e -> {
            // TODO Auto-generated method stub
            myView.getRoll().setEnabled(false);
            myView.setBalanceField(0);
            myView.setBetting(0);
            myView.setHouseWins(0);
            myView.setGamesWon(0);
            myView.setSum(0);
            if(myModel.getSum() == 0) {
                myView.getBalanceField().setEditable(false);
                myView.getBetField().setEditable(false);
            } else {
                myView.getBalanceField().setEditable(true);
                myView.getBetField().setEditable(true);
            }
        });
    }

    /**
     * exitListener() adds a listener to the exit
     * JMenuItem component.
     */
    public void exitListener() {
        myView.getExitItem().addActionListener(e -> {
            // TODO Auto-generated method stub
            int option = JOptionPane.showConfirmDialog(null,
                    "Are you sure you would like to exit?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.out.println("User selected YES");
                System.exit(0);
            } else {
                System.out.println("User selected NO");
            }
        });
    }

    /**
     * aboutListener() adds a listener to the about
     * JMenuItem.
     */
    public void aboutListener() {
        myView.getABout().addActionListener(e -> {
            // TODO Auto-generated method stub
            JOptionPane.showMessageDialog(null,
                    "Nikita Bizyuk\n"
                    + "Version: 12-6-2024\n"
                    + "openjdk version \"21.0.1\" 2023-10-17");
        });
    }

    /**
     * rulesListener() adds a listener to the
     * rules JMenuItem component in the GUI.
     */
    public void rulesListener() {
        myView.getRules().addActionListener(e -> {
            // TODO Auto-generated method stub
            JOptionPane.showMessageDialog(null,"Rules:\n"
                    + "If the player rolls a sum of 7 or 11 on the"
                    + " first throw, than the player wins.\n"
                    + "if the sum is 2, 3, or 12, the house wins.\n"
                    + "if the sum is 4, 5, 6, 8, 9, or 10, that sum\n"
                    + "becomes the players 'point'. The player" +
                    " must now keep rolling\n"
                    + "the dice. If the player rolls their point " +
                    "value again before rolling a\n"
                    + "7 than they win. If the player rolls a 7 prior" +
                    " to rolling the point value\n"
                    + "than the house wins.");
        });
    }
}

