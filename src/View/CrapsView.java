package View;
/*
 * TCSS 305 - Fall 2024
 * Instructor - Tom Capaul
 * Assignment 6 - Game of Craps
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * This class creates a Game of Craps
 * graphical user interface.
 * @author Nikita Bizyuk - nbizyu@uw.edu
 * @version December 10th 2024
 */
public class CrapsView extends JFrame {

    // myFrame stores the frame of the GUI.
    private JFrame myFrame;

    // Bottom object that gives the user
    //ability to roll the dice.
    private JButton myRollButton;

    // Help menu
    private JMenu myHelp;

    //Text area that allows the
    //user to type in a balance.
    private JTextField myBalanceField;

    // Text field that allows the user
    //to place bets.
    private JTextField myBetField;

    // label that displays the number
    // of user wins.
    private JLabel myGamesWon;

    // label displaying the amount of
    // house wins.
    private JLabel myHouseWins;

    // label displaying the value stored
    // in dice one.
    private JLabel myDiceOne;

    //label displaying the value stored
    // in dice two.
    private JLabel myDiceTwo;

    //label displaying the value stored
    // in point.
    private JLabel myPoint;

    //label displaying the sum value.
    private JLabel mySum;

    //menu that stores options that
    //can be helpful to the user.
    private JMenu myGameMenu;

    // help menu that assists the user with
    //common issues.
    private JMenu myHelpMenu;

    //menu items that start the game, reset the game
    // exit the game, and displays the rules.
    private JMenuItem myStartItem, myResetItem,
            myExitItem, myAboutItem, myRulesItem;

    /**
     * CrapsView() is a constructor that
     * gives the instance fields definition which
     * are then used to create the GUI.
     */
    public CrapsView() {
        super();
        createFrame();
        myBetField = new JTextField();
        myBalanceField = new JTextField();
        myRollButton = new JButton("Roll");
        createJLabels();
        myGameMenu = new JMenu("Menu");
        myHelpMenu = new JMenu("Help");
        myStartItem = new JMenuItem("Start");
        myResetItem = new JMenuItem("Reset");
        myExitItem = new JMenuItem("Exit");
        myAboutItem = new JMenuItem("About");
        myRulesItem = new JMenuItem("Rules");
        setMnemonics();
        layOut();
        myFrame.setVisible(true);
        JOptionPane.showMessageDialog(null,"Steps to play\n"
                + "1. Press start button found in game menu\n"
                + "2. Enter a valid balance, followed by the enter key\n"
                + "3. Enter a valid bet, followed by the enter key\n"
                + "4. Roll until either you win, or the house wins.");
    }

    /**
     * createFrame() instantiates and creates
     * the JFrame.
     */
    public void createFrame() {
        myFrame = new JFrame("Lets play some craps!!");
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setSize(400,360);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create the gradient paint with your chosen colors
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Tomato Red at the top-left
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Dark Blue at the bottom-right
                );
                // Apply the gradient paint and fill the entire content pane
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the whole pane with the gradient
            }
        });
        myFrame.setLayout(new GridLayout(5,1));
    }

    /**
     * createJLabels() creates all the labels used
     * in the graphical interface.
     */
    public void createJLabels() {
        myDiceOne = new JLabel("Dice One: " + 0 + " |");
        myDiceOne.setForeground(new Color(255,255,255));
        myDiceTwo = new JLabel("Dice Two: " + 0 + " |");
        myDiceTwo.setForeground(new Color(255, 255, 255));
        myGamesWon = new JLabel("Games won: " + 0);
        myGamesWon.setFont(new Font("Georgia",Font.PLAIN,18));
        myGamesWon.setForeground(new Color(255, 255, 255));
        myHouseWins = new JLabel("House Wins: " + 0);
        myHouseWins.setFont(new Font("Georgia",Font.PLAIN,18));
        myHouseWins.setForeground(new Color(255, 255, 255));
        myPoint = new JLabel("Point: " + 0);
        myPoint.setFont(new Font("Georgia",Font.PLAIN,18));
        myPoint.setForeground(new Color(255, 255, 255));
        mySum = new JLabel("Sum: " + 0);
        mySum.setFont(new Font("Georgia",Font.PLAIN,18));
        mySum.setForeground(new Color(255, 255, 255));
    }

    /**
     * layOut() calls all the panel methods
     * that create the panels and adds it to the frame.
     */
    public void layOut() {
        myFrame.setJMenuBar(menuBar());
        myFrame.add(panel1());
        myFrame.add(panel2());
        myFrame.add(panel3());
        myFrame.add(panel4());
    }

    /**
     * panel1() creates panel 1 out of 4.
     * @return a JPanel that displays the state
     * of panel 1.
     */
    public JPanel panel1() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create a linear gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Top-left corner: tomato color
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Bottom-right corner: deep sky blue color
                );
                // Apply the gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the entire panel
            }
        };
        panel.setLayout(new FlowLayout());
        JLabel cover = new JLabel("The Game Of Craps");
        cover.setForeground(new Color(255, 255, 255));
        cover.setFont(new Font("Georgia",Font.PLAIN,35));
        panel.add(cover,BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates panel 2 out of 4.
     * @return a JPanel storing the state of JPanel 2.
     */
    public JPanel panel2() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create a linear gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Top-left corner: tomato color
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Bottom-right corner: deep sky blue color
                );
                // Apply the gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the entire panel
            }
        };
        panel.setLayout(new FlowLayout());
        JLabel balance = new JLabel("Balance: $");
        balance.setFont(new Font("Georgia",Font.PLAIN,20));
        balance.setForeground(new Color(255, 255, 255));
        panel.add(balance);
        myBalanceField.setPreferredSize(new Dimension(50,20));
        panel.add(myBalanceField);
        JLabel bet = new JLabel("Betting:");
        bet.setFont(new Font("Georgia",Font.PLAIN,20));
        bet.setForeground(new Color(255, 255, 255));
        panel.add(bet);
        myBetField.setPreferredSize(new Dimension(50,20));
        panel.add(myBetField);
        panel.add(myGamesWon);
        panel.add(myHouseWins);
        panel.add(myPoint);
        panel.add(mySum);
        return panel;
    }

    /**
     * creates inner panel 1 of 2. These panels are both
     * combined to form panel 3.
     * @return a panel that makes up for half of panel 3.
     */
    public JPanel innerPanel1() {
        JPanel panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create a linear gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Top-left corner: tomato color
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Bottom-right corner: deep sky blue color
                );
                // Apply the gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the entire panel
            }
        };
        panel1.setLayout(new BorderLayout());
        return panel1;
    }

    /**
     * Creates inner panel 1 of 2.
     * these panels are then combined to form
     * panel 3.
     * @return a JPanel that makes up half of
     * panel 3.
     */
    public JPanel innerPanel2() {
        JPanel panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create a linear gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Top-left corner: tomato color
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Bottom-right corner: deep sky blue color
                );
                // Apply the gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the entire panel
            }
        };
        panel2.setLayout(new BorderLayout());
        return panel2;
    }

    /**
     * Creates panel 3. This panel is then added to
     * the frame of the GUI.
     * @return a JPanel that displays a portion of the GUI.
     */
    public JPanel panel3() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create a linear gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Top-left corner: tomato color
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Bottom-right corner: deep sky blue color
                );
                // Apply the gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the entire panel
            }
        };
        JPanel panel1 = innerPanel1();
        JPanel panel2 = innerPanel2();
        getDiceOne().setFont(new Font("Roboto", Font.BOLD, 30));
        panel1.add(myDiceOne, BorderLayout.LINE_END);
        myDiceTwo = getDiceTwo();
        myDiceTwo.setFont(new Font("Roboto", Font.BOLD, 30));
        panel2.add(myDiceTwo,BorderLayout.LINE_START);
        panel.add(panel1);
        panel.add(panel2);
        return panel;
    }

    /**
     * Creates panel 4. This panel is than added to
     * the frame to create the final portion
     * of the game.
     * @return a JPanel storing the lower 1/4th of the frame.
     */
    public JPanel panel4() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Create a linear gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(139, 0, 0),
                        // Top-left corner: tomato color
                        getWidth(), getHeight(), new Color(0, 0, 128)
                        // Bottom-right corner: deep sky blue color
                );
                // Apply the gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                // Fill the entire panel
            }
        };
        panel.setLayout(new FlowLayout());
        panel.add(myRollButton);
        return panel;
    }

    /**
     * getRoll() return the roll JButton.
     * @return a JButton value.
     */
    public JButton getRoll() {
        return myRollButton;
    }

    /**
     * getHelp() returns the help menu option.
     * @return a JMenu object for helping
     * the user.
     */
    public JMenu getHelp() {
        return myHelp;
    }

    /**
     * getBalanceField() retrieves the balance textField.
     * @return a JTextField object.
     */
    public JTextField getBalanceField() {
        return myBalanceField;
    }

    /**
     * setBalanceField() sets the value stored in
     * the value field JLabel.
     * @param theNum
     */
    public void setBalanceField(int theNum) {
        myBalanceField.setText(String.valueOf(theNum));
    }

    /**
     * getBetField() returns the JTextField used
     * for placing a bet.
     * @return
     */
    public JTextField getBetField() {
        return myBetField;
    }

    /**
     * setBetting() sets the bet in the Text Field.
     * @param theNum passes an integer value
     *               for the bet.
     */
    public void setBetting(int theNum) {
        myBetField.setText(String.valueOf(theNum));
    }

    /**
     * setDiceOne() sets the state of the dice one object.
     * @param theNum passes the number used for dice one.
     */
    public void setDiceOne(int theNum) {
        myDiceOne.setText("Dice One: " + theNum + " |");
    }

    /**
     * getDiceOne() returns the value stored in dice
     * one.
     * @return a JLabel with an update value for dice one.
     */
    public JLabel getDiceOne() {
        return myDiceOne;
    }

    /**
     * getDiceTwo() returns a JLabel value that represents
     * dice two.
     * @return a JLabel object.
     */
    public JLabel getDiceTwo() {
        return myDiceTwo;
    }

    /**
     * setDiceTwo() changes the state of the dice two
     * JLabel.
     * @param theNum an integer value that is stored
     *               in dice two.
     */
    public void setDiceTwo(int theNum) {
        myDiceTwo.setText("Dice Two: " + theNum + " |");
    }

    /**
     * getGamesWon() returns a JLabel that shows how many
     * games the user has won.
     * @return a JLabel object.
     */
    public JLabel getGamesWon() {
        return myGamesWon;
    }

    /**
     * setGamesWon() changes the state of the
     * myGamesWon isntance field.
     * @param theNum
     */
    public void setGamesWon(int theNum) {
        myGamesWon.setText("Games won: " + theNum);
    }

    /**
     * getHouseWins() retrieves a label that depicts
     * how many games the house has won.
     * @return a JLabel object.
     */
    public JLabel getHouseWins() {
        return myHouseWins;
    }

    /**
     * setHouseWins() changes the state of the
     * myHouseWins instance field.
     * @param theNum passes an integer value of wins.
     */
    public void setHouseWins(int theNum) {
        myHouseWins.setText("House Wins: " + theNum);
    }

    /**
     * getPoint() retrieves a JLabel that stores
     * the data stored in point.
     * @return a JLabel object.
     */
    public JLabel getPoint() {
        return myPoint;
    }

    /**
     * setPoint() changes the state of the
     * myPoint instance field.
     * @param theNum passes a new integer value for point.
     */
    public void setPoint(int theNum) {
        myPoint.setText("Point : " + theNum);
    }

    /**
     * getSum() retrieves a label that stores the
     * sum value for the dice.
     * @return a JLabel for sum.
     */
    public JLabel getSum() {
        return mySum;
    }

    /**
     * setSum() updates the state of the
     * mySum label.
     * @param theNum passes a new integer representing
     *               the sum.
     */
    public void setSum(int theNum) {
        mySum.setText("Sum: " + theNum);
    }

    /**
     * getStartItem() returns a JMenuItem
     * for starting the game.
     * @return a JMenuItem object.
     */
    public JMenuItem getStartItem() {
        return myStartItem;
    }

    /**
     * getResetItem() retrieves the reset menu item object.
     * @return a JMenuItem object.
     */
    public JMenuItem getResetItem() {
        return myResetItem;
    }

    /**
     * getExitItem() will return a JMenuItem object for
     * @return
     */
    public JMenuItem getExitItem() {
        return myExitItem;
    }

    /**
     * getFrame() returns the data stored
     * in the myFrame instance field.
     * @return a JFrame object
     */
    public JFrame getFrame() {
        return myFrame;
    }

    /**
     * getAbout() returns the data stored in
     * the myAbout() instance field.
     * @return a JMenuItem object
     */
    public JMenuItem getABout() {
        return myAboutItem;
    }

    /**
     * getRules() returns the data stored in the
     * myRules instance field.
     * @return a JMenuItem object.
     */
    public JMenuItem getRules() {
        return myRulesItem;
    }

    /**
     * menuBar() creates a menu bar and adds menuItem objects
     * to it.
     * @return a JMenuBar object.
     */
    public JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();
        myGameMenu.add(myStartItem);
        myGameMenu.add(myResetItem);
        myGameMenu.add(myExitItem);
        myHelpMenu.add(myAboutItem);
        myHelpMenu.add(myRulesItem);
        menuBar.add(myGameMenu);
        menuBar.add(myHelpMenu);
        return menuBar;
    }

    /**
     * setMnemonics() creates keyboard shortcuts for all
     * the buttons and menuItems.
     */
    public void setMnemonics() {
        myStartItem.setAccelerator(KeyStroke.
                getKeyStroke(KeyEvent.VK_A,ActionEvent.SHIFT_MASK));//SHIFT A
        myResetItem.setAccelerator(KeyStroke.
                getKeyStroke(KeyEvent.VK_B,ActionEvent.SHIFT_MASK));//SHIFT B
        myExitItem.setAccelerator(KeyStroke.
                getKeyStroke(KeyEvent.VK_C,ActionEvent.SHIFT_MASK));//SHIFT C
        myAboutItem.setAccelerator(KeyStroke.
                getKeyStroke(KeyEvent.VK_D,ActionEvent.SHIFT_MASK));//SHIFT D
        myRulesItem.setAccelerator(KeyStroke.
                getKeyStroke(KeyEvent.VK_E,ActionEvent.SHIFT_MASK));//SHIFT E
        myRollButton.setMnemonic(KeyEvent.VK_R);// ALT R
    }
}
