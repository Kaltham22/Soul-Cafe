/*
Name: Kaltham Mohsen Alshayeb
ID: 1915435
Section: I1
Email: kalshayeb.stu@uj.edu.sa
*/
package soulcafe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SoulCafe{
    public static void main(String[] args){
        Frame1 window1 = new Frame1();
    }
}

//Frame1
//----------------------------------------------------------------------------------------------------------------------

class Frame1 extends JFrame{
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem exitItem;
    private JRadioButton dineInRadio;
    private JRadioButton takeAwayRadio;
    private ButtonGroup group;
    private ImageIcon BackGroundIcon;
    private JLabel BackGroundLabel;
    private JLabel descriptionOfCafe;
    private JLabel userNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel errorLabel;
    private JTextField userNameTextField;
    private JTextField phoneNumberTextField;
    private JButton menuButton;
    private ImageIcon menuIcon;
    private ImageIcon image;
    private JLabel imageLabel;
    
    public Frame1(){
        setTitle("Soul Café");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        buildMenuBar();
        
        BackGroundIcon = new ImageIcon("Home.jpg");
        BackGroundLabel = new JLabel(BackGroundIcon);
        BackGroundLabel.setBounds(0, 0, 400, 610);
        
        descriptionOfCafe = new JLabel("for specialty coffee and sweets", SwingConstants.CENTER);
        descriptionOfCafe.setFont(new Font("Monospaced", Font.BOLD, 15));
        descriptionOfCafe.setForeground(new Color(89, 74, 71));
        descriptionOfCafe.setBounds(35, 120,300, 100);
        
        dineInRadio = new JRadioButton("Dine In");
        dineInRadio.setFont(new Font("DialogInput", Font.BOLD, 14));
        dineInRadio.setBounds(140, 178, 100, 50);
        dineInRadio.setBackground(new Color(243, 243, 233));
        dineInRadio.setForeground(new Color(89, 74, 71));
        
        takeAwayRadio = new JRadioButton("Take Away");
        takeAwayRadio.setFont(new Font("DialogInput", Font.BOLD, 14));
        takeAwayRadio.setBounds(140, 210, 100, 50);
        takeAwayRadio.setBackground(new Color(243, 243, 233));
        takeAwayRadio.setForeground(new Color(89, 74, 71));
        
        group = new ButtonGroup();
        group.add(dineInRadio);
        group.add(takeAwayRadio);
        
        userNameLabel = new JLabel("Enter your name");
        userNameLabel.setForeground(new Color(89, 74, 71));
        userNameLabel.setBounds(50, 220, 300, 100);
        userNameTextField = new JTextField(10);
        userNameTextField.setBounds(210, 260, 100, 25);
        
        phoneNumberLabel = new JLabel("Enter your phone number");
        phoneNumberLabel.setForeground(new Color(89, 74, 71));
        phoneNumberLabel.setBounds(50, 250, 300, 100);
        phoneNumberTextField = new JTextField(10);
        phoneNumberTextField.setBounds(210, 290, 100, 25);
        
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(50, 280, 350, 100);
        
        menuIcon = new ImageIcon("menuButton.jpg");
        menuButton = new JButton(menuIcon);
        menuButton.setBackground(Color.WHITE);
        menuButton.setToolTipText("Click here to go the menu.");
        menuButton.addActionListener(new menuButton());
        menuButton.setBounds(260, 340, 70, 30);
        
        image = new ImageIcon("image.jpg");
        imageLabel = new JLabel(image);
        imageLabel.setBounds(5, 310, 100, 100);
        
        add(descriptionOfCafe);
        add(dineInRadio);
        add(takeAwayRadio);
        add(userNameLabel);
        add(userNameTextField);
        add(phoneNumberLabel);
        add(phoneNumberTextField);
        add(imageLabel);
        add(menuButton);
        add(errorLabel);
        add(BackGroundLabel);
        
        setVisible(true);
    }
    
    private void buildMenuBar(){
        menuBar = new JMenuBar();
        
        buildFileMenu();
        
        menuBar.add(fileMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void buildFileMenu(){
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent. VK_F);
        
        fileMenu.add(exitItem);
    }
    
    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    
    private void inputValidation(){
        if(validEmpty()){
            errorLabel.setText("Please enter your information");
        }
        else{
            if (!validateName()) {
                errorLabel.setText("Please enter correct name");
            }
            if (!validateMobile()) {
                errorLabel.setText("Please enter correct phone number");
            }
            if (!dineInRadio.isSelected() && !takeAwayRadio.isSelected()) {
                errorLabel.setText("Please select where do you want your order");
            }
        }
    }
   
    private boolean validateName() {
        return userNameTextField.getText().matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }
    
    private boolean validateMobile() {
        return phoneNumberTextField.getText().matches("(05)[0-9]{8}");
    }
    
    private boolean validEmpty(){
        return userNameTextField.getText().trim().isEmpty() || 
                    phoneNumberTextField.getText().trim().isEmpty();
    }
    
    public void writeToFile(){
        BufferedWriter Customer_Order;
        try{
            Customer_Order = new BufferedWriter(new FileWriter("Customer_Order.txt", false));
            Customer_Order.write((String) "Customer Name: " + userNameTextField.getText() + "\n");
            Customer_Order.write((String) "Phone Number: " + phoneNumberTextField.getText() + "\n");
            if(dineInRadio.isSelected()){
                Customer_Order.write((String) "Dine In Or Take Away: " + dineInRadio.getText() + "\n");
            }
            else if(takeAwayRadio.isSelected()){
                Customer_Order.write((String) "Dine In Or Take Away: " + takeAwayRadio.getText() + "\n");
            }
            
            System.out.println("Customer information are added successfully");
            Customer_Order.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Error writing to file");
        }
        catch(IOException ex){
            System.out.println("Error writing to file");
        }
    }
    
    public class menuButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String in_out;
            inputValidation();
            
            if(validEmpty() == false && validateName() == true && validateMobile() == true 
                && (dineInRadio.isSelected() || takeAwayRadio.isSelected())){
                writeToFile();
                setVisible(false);
                Frame2 window2 = new Frame2();
                if(dineInRadio.isSelected())
                    in_out = "Dine In";
                else
                    in_out = "Take Away";
                
                window2.setCustomerData(userNameTextField.getText(), phoneNumberTextField.getText(), in_out);
            }
        }
    }
}
   
//Frame2
//----------------------------------------------------------------------------------------------------------------------

class Frame2 extends JFrame{
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu menuMenu;
    private JMenuItem exitItem;
    private JRadioButtonMenuItem Sweets;
    private JRadioButtonMenuItem Coffee;
    private ButtonGroup group;
    private ImageIcon BackGroundIcon;
    private JLabel BackGroundLabel;
    private ImageIcon menuIcon;
    private JLabel menuIconLabel;
    private JLabel sweetsLabel;
    private JLabel coffeeLabel;
    private JLabel menuLabel;
    private ImageIcon coffeeImage;
    private ImageIcon sweetImage;
    
    private String name;
    private String mobile;
    private String dineIn_takeAway;
    
    private double sweetPrice;
    private double coffeePrice;
    
    public Frame2(){
        setVisible(true);
        setTitle("Soul Café");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        buildMenuBar();
        
        BackGroundIcon = new ImageIcon("CheckOut.jpg");
        BackGroundLabel = new JLabel(BackGroundIcon);
        BackGroundLabel.setBounds(0, 0, 400, 610);
        
        menuIcon = new ImageIcon("menu.jpg");
        menuIconLabel = new JLabel(menuIcon);
        menuIconLabel.setBounds(140, 3, 100, 100);
        
        menuLabel = new JLabel("Menu", coffeeImage, SwingConstants.CENTER);
        menuLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        menuLabel.setBounds(140, 40, 100, 100);
        
        sweetImage = new ImageIcon("sweet.jpg");
        sweetsLabel = new JLabel(sweetImage);
        sweetsLabel.setBounds(30, 120, 150, 230);
        
        coffeeImage = new ImageIcon("coffee.jpg");
        coffeeLabel = new JLabel(coffeeImage);
        coffeeLabel.setBounds(200, 120, 150, 230);
        
        add(menuIconLabel);
        add(menuLabel);
        add(sweetsLabel);
        add(coffeeLabel);
        add(BackGroundLabel);
    }
    
    private void buildMenuBar(){
        menuBar = new JMenuBar();
        
        buildFileMenu();
        buildMenuMenu();
        
        menuBar.add(fileMenu);
        menuBar.add(menuMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void buildFileMenu(){
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent. VK_F);
        
        fileMenu.add(exitItem);
    }
     
    private void buildMenuMenu(){
        Sweets = new JRadioButtonMenuItem("Sweets");
        Sweets.setMnemonic(KeyEvent. VK_S);
        Sweets.setToolTipText("Click here to go sweets menu.");
        Sweets.addActionListener(new menuListener());
        
        Coffee = new JRadioButtonMenuItem("Coffee");
        Coffee.setMnemonic(KeyEvent. VK_C);
        Coffee.setToolTipText("Click here to go coffee menu.");
        Coffee.addActionListener(new menuListener());
        
        group = new ButtonGroup();
        
        group.add(Sweets);
        group.add(Coffee);
        
        menuMenu = new JMenu("Menu");
        menuMenu.setMnemonic(KeyEvent. VK_M);
        
        menuMenu.add(Sweets);
        menuMenu.add(Coffee);
    }
    
    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    
    void setCustomerData(String n, String mob, String in_out){
        name = n;
        mobile = mob;
        dineIn_takeAway = in_out;
    }
    
    void setSweetPrice(double sp){
        sweetPrice = sp;
    }
    
    void setCoffeePrice(double cp){
        coffeePrice = cp;
    }
    
    private class menuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(Sweets.isSelected()){
                setVisible(false);
                Frame3 window3 = new Frame3();
                window3.setCustomerData(name, mobile, dineIn_takeAway);
                window3.setCoffeePrice(coffeePrice);
            }    
            else if(Coffee.isSelected()){
                setVisible(false);
                Frame4 window4 = new Frame4();
                window4.setCustomerData(name, mobile, dineIn_takeAway);
                window4.setSweetPrice(sweetPrice);
            }        
        }
    }
}

//Frame3
//----------------------------------------------------------------------------------------------------------------------

class Frame3 extends JFrame{
    private JLabel label;
    private JCheckBox waffle;
    private JCheckBox pancake;
    private JCheckBox crepe;
    private JCheckBox chocolateSouse;
    private JCheckBox honeySouse;
    private JCheckBox cramelSouse;
    private JCheckBox pistatioSouse;
    private JCheckBox noSouse;
    private JCheckBox strawberry;
    private JCheckBox bananas;
    private JCheckBox nuts;
    private JCheckBox noAddings;
    private JButton menuButton;
    private JButton checkOutButton;
    private ImageIcon sweetsImage;
    private JLabel sweetsImageLabel;
    
    private JPanel sweets;
    private JPanel sweetsPanel;
    private JPanel sousePanel;
    private JPanel toppingsPanel;
    private JPanel buttonPanel;
    
    public final int WAFFLE = 25;
    public final int PANCAKE = 20;
    public final int CREPE = 10;
    public final int CHOCOLATESOUSE = 5;
    public final int HONEYSOUSE = 3;
    public final int CRAMELSOUSE = 2;
    public final int PISTATIOSOUSE = 6;
    public final int NOSOUSE = 0;
    public final int STRAWBERRY = 5;
    public final int BANANAS = 5;
    public final int NUTS = 8;
    public final int NOADDINGS = 0;
    
    private String name;
    private String mobile;
    private String dineIn_takeAway;
    
    private double coffeePrice;
    
    public Frame3(){
        setVisible(true);
        setTitle("Soul Café");
        setSize(470, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        sweets = new JPanel();
        sweets.setLayout(new BorderLayout());
        sweets.setBackground(new Color(243, 243, 233));
        
        label = new JLabel("Sweet Menu", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        
        buildSweetsPanel();
        buildSousePanel();
        buildToppingsPanel();
        buildButtonPanel();
        
        sweets.add(label, BorderLayout.NORTH);
        sweets.add(sweetsPanel, BorderLayout.WEST);
        sweets.add(sousePanel, BorderLayout.CENTER);
        sweets.add(toppingsPanel, BorderLayout.EAST);
        sweets.add(buttonPanel, BorderLayout.SOUTH);
        
        add(sweets);
    }
    
    private void buildSweetsPanel(){
        sweetsPanel = new JPanel();
        sweetsPanel.setLayout(new GridLayout(3,1));
        sweetsPanel.setBackground(new Color(243, 243, 233));
        sweetsPanel.setBorder(BorderFactory.createTitledBorder("Sweets"));
        
        waffle = new JCheckBox("Waffle (25 S.R)");
        waffle.setBackground(new Color(243, 243, 233));
        waffle.setForeground(new Color(89, 74, 71));
        
        pancake = new JCheckBox("Pancake(20 S.R)");
        pancake.setBackground(new Color(243, 243, 233));
        pancake.setForeground(new Color(89, 74, 71));
        
        crepe = new JCheckBox("Crepe (10 S.R)");
        crepe.setBackground(new Color(243, 243, 233));
        crepe.setForeground(new Color(89, 74, 71));
        
        sweetsPanel.add(waffle);
        sweetsPanel.add(pancake);
        sweetsPanel.add(crepe);
    }
    
    private void buildSousePanel(){
        sousePanel = new JPanel();
        sousePanel.setLayout(new GridLayout(5,1));
        sousePanel.setBackground(new Color(243, 243, 233));
        sousePanel.setBorder(BorderFactory.createTitledBorder("Souse"));
        
        chocolateSouse = new JCheckBox("Chocolate Souse (5 S.R)");
        chocolateSouse.setBackground(new Color(243, 243, 233));
        chocolateSouse.setForeground(new Color(89, 74, 71));
        
        honeySouse = new JCheckBox("Honey Souse (3 S.R)");
        honeySouse.setBackground(new Color(243, 243, 233));
        honeySouse.setForeground(new Color(89, 74, 71));
        
        cramelSouse = new JCheckBox("Cramel Souse (2 S.R)");
        cramelSouse.setBackground(new Color(243, 243, 233));
        cramelSouse.setForeground(new Color(89, 74, 71));
        
        pistatioSouse = new JCheckBox("Pistatio Souse (6 S.R)");
        pistatioSouse.setBackground(new Color(243, 243, 233));
        pistatioSouse.setForeground(new Color(89, 74, 71));
        
        noSouse = new JCheckBox("No Souse");
        noSouse.setBackground(new Color(243, 243, 233));
        noSouse.setForeground(new Color(89, 74, 71));
        
        sousePanel.add(chocolateSouse);
        sousePanel.add(honeySouse);
        sousePanel.add(cramelSouse);
        sousePanel.add(pistatioSouse);
        sousePanel.add(noSouse);
    }
    
    private void buildToppingsPanel(){
        toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new GridLayout(4,1));
        toppingsPanel.setBackground(new Color(243, 243, 233));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        
        strawberry = new JCheckBox("Strawberry (5 S.R)");
        strawberry.setBackground(new Color(243, 243, 233));
        strawberry.setForeground(new Color(89, 74, 71));
        
        bananas = new JCheckBox("Bananas (5 S.R)");
        bananas.setBackground(new Color(243, 243, 233));
        bananas.setForeground(new Color(89, 74, 71));
        
        nuts = new JCheckBox("Nuts (8 S.R)");
        nuts.setBackground(new Color(243, 243, 233));
        nuts.setForeground(new Color(89, 74, 71));
        
        noAddings = new JCheckBox("No Addings");
        noAddings.setBackground(new Color(243, 243, 233));
        noAddings.setForeground(new Color(89, 74, 71));
        
        toppingsPanel.add(strawberry);
        toppingsPanel.add(bananas);
        toppingsPanel.add(nuts);
        toppingsPanel.add(noAddings);
    }
    
    private void buildButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 9));
        buttonPanel.setBackground(new Color(243, 243, 233));
        
        menuButton = new JButton("MENU");
        menuButton.setForeground(new Color(243, 243, 233));
        menuButton.setBackground(new Color(219, 160, 88));
        menuButton.setFont(new Font("Dialog", Font.BOLD, 14));
        menuButton.setToolTipText("Click here to return to the menu.");
        menuButton.addActionListener(new buttonListener());
        
        checkOutButton = new JButton("CHECK OUT");
        checkOutButton.setForeground(new Color(243, 243, 233));
        checkOutButton.setBackground(new Color(219, 160, 88));
        checkOutButton.setFont(new Font("Dialog", Font.BOLD, 14));
        checkOutButton.setToolTipText("Click here to see the invoice.");
        checkOutButton.addActionListener(new buttonListener());
        
        sweetsImage = new ImageIcon("pancake.jpg");
        sweetsImageLabel = new JLabel(sweetsImage, SwingConstants.CENTER);
        sweetsImageLabel.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 20));
        sweetsImageLabel.setIcon(sweetsImage);
        
        buttonPanel.add(menuButton);
        buttonPanel.add(sweetsImageLabel);
        buttonPanel.add(checkOutButton);
    }
    
    void setCoffeePrice(double cp){
        coffeePrice = cp;
    }
    
    void setCustomerData(String n, String mob, String in_out){
        name = n;
        mobile = mob;
        dineIn_takeAway = in_out;
    }
    
    public double getSweetsCost(){
        double sweetsCost = 0.0;
        
        if (waffle.isSelected())
            sweetsCost += WAFFLE;
        if (pancake.isSelected())
            sweetsCost += PANCAKE;
        if (crepe.isSelected())
            sweetsCost += CREPE;
        
        return sweetsCost;
    }
    
    public double getSouseCost(){
        double souseCost = 0.0;
        
        if (chocolateSouse.isSelected())
            souseCost += CHOCOLATESOUSE;
        if (honeySouse.isSelected())
            souseCost += HONEYSOUSE;
        if (cramelSouse.isSelected())
            souseCost += CRAMELSOUSE;
        if (pistatioSouse.isSelected())
            souseCost += PISTATIOSOUSE;
        if (noSouse.isSelected())
            souseCost += NOSOUSE;
        
        return souseCost;
    } 
    
    public double getToppingsCost(){
        double toppingsCost = 0.0;
        
        if (strawberry.isSelected())
            toppingsCost += STRAWBERRY;
        if (bananas.isSelected())
            toppingsCost += BANANAS;
        if (nuts.isSelected())
            toppingsCost += NUTS;
        if (noAddings.isSelected())
            toppingsCost += NOADDINGS;

        return toppingsCost;
    }
    
    public double getTotalSweetsCost(){
        double totalSweetsCost;
        totalSweetsCost = getSweetsCost() +
                          getSouseCost()  +
                          getToppingsCost();
        return totalSweetsCost;
    }
     
    private class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == menuButton){
                setVisible(false);
                Frame2 window2 = new Frame2();
                window2.setCustomerData(name, mobile, dineIn_takeAway);
                window2.setSweetPrice(getTotalSweetsCost());
            }
            else{
                setVisible(false);
                Frame5 window5 = new Frame5();
                window5.setCustomerData(name, mobile, dineIn_takeAway);
                window5.setOrderData(getTotalSweetsCost(), coffeePrice);
            }       
        }
    }
}

//Frame4
//----------------------------------------------------------------------------------------------------------------------

class Frame4 extends JFrame{
    private JLabel label;
    private JCheckBox espresso;
    private JCheckBox americano;
    private JCheckBox cortado;
    private JCheckBox cappuccino;
    private JCheckBox latte;
    private JCheckBox arabicCoffee;
    private JCheckBox icedAmericanoCoffee;
    private JCheckBox icedLatte;
    private JCheckBox icedCaramelLatte;
    private JCheckBox icedSpanishLatte;
    private JCheckBox icedPistachioLatte;
    private JButton menuButton;
    private JButton checkOutButton;
    private ImageIcon coffeeImage;
    private JLabel coffeeImageLabel;
    
    private JPanel coffee;
    private JPanel hotDrinksPanel;
    private JPanel coldDrinksPanel;
    private JPanel buttonPanel;
    
    public final double ESPRESSO = 8;
    public final double LATTE = 16;
    public final double CAPPUCCINO = 10;
    public final double AMERICANO = 10;
    public final double ARABICCOFFEE = 20;
    public final double CORTADO = 15;
    public final double ICEDLATTE = 23;
    public final double ICEDAMRICANOCOFFEE = 19;
    public final double ICEDCRAMELLATTE = 18;
    public final double ICEDSPANISHLATTE = 17;
    public final double ICEDPISTACHIOLATTE = 25;
    
    private String name;
    private String mobile;
    private String dineIn_takeAway;
    
    private double sweetPrice;
    
    public Frame4(){
        setVisible(true);
        setTitle("Soul Café");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        coffee = new JPanel();
        coffee.setBackground(new Color(243, 243, 233));
        coffee.setLayout(new BorderLayout());
        
        label = new JLabel("Coffee Menu", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        
        buildHotDrinksPanel();
        buildColdDrinksPanel();
        buildButtonPanel();
        
        coffee.add(label, BorderLayout.NORTH);
        coffee.add(hotDrinksPanel, BorderLayout.WEST);
        coffee.add(coldDrinksPanel, BorderLayout.EAST);
        coffee.add(buttonPanel, BorderLayout.SOUTH);
        
        add(coffee);
    }
    
    private void buildHotDrinksPanel(){
        hotDrinksPanel = new JPanel();
        hotDrinksPanel.setLayout(new GridLayout(6,1));
        hotDrinksPanel.setBackground(new Color(243, 243, 233));
        hotDrinksPanel.setBorder(BorderFactory.createTitledBorder("Hot Drinks"));
        
        espresso = new JCheckBox("Espresso (8 S.R)");
        espresso.setBackground(new Color(243, 243, 233));
        espresso.setForeground(new Color(89, 74, 71));
        
        americano = new JCheckBox("Americano (10 S.R)");
        americano.setBackground(new Color(243, 243, 233));
        americano.setForeground(new Color(89, 74, 71));
        
        cortado = new JCheckBox("Cortado (15 S.R)");
        cortado.setBackground(new Color(243, 243, 233));
        cortado.setForeground(new Color(89, 74, 71));
        
        cappuccino = new JCheckBox("Cappuccino (10 S.R)");
        cappuccino.setBackground(new Color(243, 243, 233));
        cappuccino.setForeground(new Color(89, 74, 71));
        
        latte = new JCheckBox("Latte (16 S.R)");
        latte.setBackground(new Color(243, 243, 233));
        latte.setForeground(new Color(89, 74, 71));
        
        arabicCoffee = new JCheckBox("Arabic Coffee (20 S.R)");
        arabicCoffee.setBackground(new Color(243, 243, 233));
        arabicCoffee.setForeground(new Color(89, 74, 71));
        
        hotDrinksPanel.add(espresso);
        hotDrinksPanel.add(americano);
        hotDrinksPanel.add(cortado);
        hotDrinksPanel.add(cappuccino);
        hotDrinksPanel.add(latte);
        hotDrinksPanel.add(arabicCoffee);
    }
    
    private void buildColdDrinksPanel(){
        coldDrinksPanel = new JPanel();
        coldDrinksPanel.setLayout(new GridLayout(5,1));
        coldDrinksPanel.setBackground(new Color(243, 243, 233));
        coldDrinksPanel.setBorder(BorderFactory.createTitledBorder("Cold Drinks"));
        
        icedAmericanoCoffee = new JCheckBox("Iced Americano Coffee (19 S.R)");
        icedAmericanoCoffee.setBackground(new Color(243, 243, 233));
        icedAmericanoCoffee.setForeground(new Color(89, 74, 71));
        
        icedLatte = new JCheckBox("Iced Latte (23 S.R)");
        icedLatte.setBackground(new Color(243, 243, 233));
        icedLatte.setForeground(new Color(89, 74, 71));
        
        icedCaramelLatte = new JCheckBox("Iced Caramel Latte (18 S.R)");
        icedCaramelLatte.setBackground(new Color(243, 243, 233));
        icedCaramelLatte.setForeground(new Color(89, 74, 71));
        
        icedSpanishLatte = new JCheckBox("Iced Spanish Latte (17 S.R)");
        icedSpanishLatte.setBackground(new Color(243, 243, 233));
        icedSpanishLatte.setForeground(new Color(89, 74, 71));
        
        icedPistachioLatte = new JCheckBox("Iced Pistachio Latte (25 S.R)");
        icedPistachioLatte.setBackground(new Color(243, 243, 233));
        icedPistachioLatte.setForeground(new Color(89, 74, 71));
        
        coldDrinksPanel.add(icedAmericanoCoffee);
        coldDrinksPanel.add(icedLatte);
        coldDrinksPanel.add(icedCaramelLatte);
        coldDrinksPanel.add(icedSpanishLatte);
        coldDrinksPanel.add(icedPistachioLatte);
    }
    
    private void buildButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(243, 243, 233));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 9));
        
        menuButton = new JButton("MENU");
        menuButton.setToolTipText("Click here to return to the menu.");
        menuButton.setForeground(new Color(243, 243, 233));
        menuButton.setBackground(new Color(219, 160, 88));
        menuButton.setFont(new Font("Dialog", Font.BOLD, 14));
        menuButton.addActionListener(new buttonListener());
        
        checkOutButton = new JButton("CHECK OUT");
        checkOutButton.setToolTipText("Click here to see the invoice.");
        checkOutButton.setForeground(new Color(243, 243, 233));
        checkOutButton.setBackground(new Color(219, 160, 88));
        checkOutButton.setFont(new Font("Dialog", Font.BOLD, 14));
        checkOutButton.addActionListener(new buttonListener());
        
        coffeeImage = new ImageIcon("coffee2.jpg");
        coffeeImageLabel = new JLabel(coffeeImage, SwingConstants.CENTER);
        coffeeImageLabel.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 20));
        coffeeImageLabel.setIcon(coffeeImage);
        
        buttonPanel.add(menuButton);
        buttonPanel.add(coffeeImageLabel);
        buttonPanel.add(checkOutButton);
    }
    
    void setSweetPrice(double sp){
        sweetPrice = sp;
    }
    
    void setCustomerData(String n, String mob, String in_out){
        name = n;
        mobile = mob;
        dineIn_takeAway = in_out;
    }
    
    public double getCoffeeCost(){
        double coffeeCost = 0.0;
        
        if (espresso.isSelected())
            coffeeCost += ESPRESSO;
        if (americano.isSelected())
            coffeeCost += AMERICANO;
        if (cortado.isSelected())
            coffeeCost += CORTADO;
        if (cappuccino.isSelected())
            coffeeCost += CAPPUCCINO;
        if (latte.isSelected())
            coffeeCost += LATTE;
        if (arabicCoffee.isSelected())
            coffeeCost += ARABICCOFFEE;
        if (icedAmericanoCoffee.isSelected())
            coffeeCost += ICEDAMRICANOCOFFEE;
        if (icedLatte.isSelected())
            coffeeCost += ICEDLATTE;
        if (icedCaramelLatte.isSelected())
            coffeeCost += ICEDCRAMELLATTE;
        if (icedSpanishLatte.isSelected())
            coffeeCost += ICEDSPANISHLATTE;
        if (icedPistachioLatte.isSelected())
            coffeeCost += ICEDPISTACHIOLATTE;

        return coffeeCost;
    }
    
    private class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == menuButton){
                setVisible(false);
                Frame2 window2 = new Frame2();
                window2.setCustomerData(name, mobile, dineIn_takeAway);
                window2.setCoffeePrice(getCoffeeCost());        
            }
            else{
                setVisible(false);
                Frame5 window5 = new Frame5();
                window5.setCustomerData(name, mobile, dineIn_takeAway);
                window5.setOrderData(sweetPrice, getCoffeeCost());
            }        
        }
    }
}

//Frame5
//----------------------------------------------------------------------------------------------------------------------

class Frame5 extends JFrame{
    private ImageIcon BackGroundIcon;
    private JLabel BackGroundLabel;
    private ImageIcon billIcon;
    private JLabel billLabel;
    private JLabel cafeName;
    private JLabel thankLabel;
    private JLabel customerLabel;
    private JLabel phoneNamberLabel;
    private JLabel whereLabel;
    private JLabel subtotalLabel;
    private JLabel VATLabel;
    private JLabel TotalLabel;
    private JLabel statementLabel;
    private ImageIcon doneImage;
    private JButton doneButton;
    
    private Customer_Order cust_ord1;//Global object
    private Customer_Order cust_ord2;//Global object
    
    public Frame5(){
        setVisible(true);
        setTitle("Soul Café");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        BackGroundIcon = new ImageIcon("CheckOut.jpg");
        BackGroundLabel = new JLabel(BackGroundIcon);
        BackGroundLabel.setBounds(0, 0, 400, 610);
        
        billIcon = new ImageIcon("bill.jpg");
        billLabel = new JLabel(billIcon);
        billLabel.setBounds(160, 10, 100, 100);
        
        cafeName = new JLabel("Soul Café", SwingConstants.CENTER);
        cafeName.setFont(new Font("Serif", Font.BOLD + + Font.ITALIC, 22));
        cafeName.setForeground(new Color(89, 74, 71));
        cafeName.setBounds(60, 70,300, 100);
        
        thankLabel = new JLabel("Thank you for choosing us", SwingConstants.CENTER);
        thankLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        thankLabel.setForeground(new Color(89, 74, 71));
        thankLabel.setBounds(65, 90,300, 100);
        
        customerLabel = new JLabel();
        customerLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        customerLabel.setForeground(new Color(89, 74, 71));
        customerLabel.setBounds(35, 120, 300, 100);
        
        phoneNamberLabel = new JLabel();
        phoneNamberLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        phoneNamberLabel.setForeground(new Color(89, 74, 71));
        phoneNamberLabel.setBounds(35, 150, 300, 100);
        
        whereLabel = new JLabel();
        whereLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        whereLabel.setForeground(new Color(89, 74, 71));
        whereLabel.setBounds(35, 180, 300, 100);
        
        subtotalLabel = new JLabel();
        subtotalLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        subtotalLabel.setForeground(new Color(89, 74, 71));
        subtotalLabel.setBounds(35, 210,200, 100);
        
        VATLabel = new JLabel();
        VATLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        VATLabel.setForeground(new Color(89, 74, 71));
        VATLabel.setBounds(35, 240,200, 100);
        
        TotalLabel = new JLabel();
        TotalLabel.setFont(new Font("DialogInput", Font.BOLD, 15));
        TotalLabel.setForeground(new Color(89, 74, 71));
        TotalLabel.setBounds(35, 270,200, 100);
        
        statementLabel = new JLabel("Follow your soul", SwingConstants.CENTER);
        statementLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        statementLabel.setForeground(new Color(89, 74, 71));
        statementLabel.setBounds(130, 350,160, 25);
        
        doneImage = new ImageIcon("Done.jpg");
        doneButton = new JButton(doneImage);
        doneButton.setBackground(Color.WHITE);
        doneButton.setToolTipText("Click here when you are done with the order.");
        doneButton.setBounds(350, 330,65, 65);
        doneButton.addActionListener(new confirmListener());
        
        add(billLabel);
        add(cafeName);
        add(thankLabel);
        add(customerLabel);
        add(phoneNamberLabel);
        add(whereLabel);
        add(subtotalLabel);
        add(VATLabel);
        add(TotalLabel);
        add(statementLabel);
        add(doneButton);
        add(BackGroundLabel);
    }
    
    void setCustomerData(String n, String mob, String in_out){
        customerLabel.setText("Customer Name: " + n);
        phoneNamberLabel.setText("Phone Number: " + mob);
        whereLabel.setText("Dine In / Take Away: " + in_out);
        
        cust_ord1 = new Customer_Order(n, mob, in_out);
    }
    
    void setOrderData(double sp, double cp){
        double SUBTOTAL, TAX, TOTAL;
        final double TAX_RATE = 0.15;
        
        //Calculate the subtotal
        SUBTOTAL = sp + cp;
        
        //Calculate the sales tax
        TAX = SUBTOTAL * TAX_RATE;
        
        //Calculate the total
        TOTAL = SUBTOTAL + TAX;
        
        subtotalLabel.setText("Subtotal: " + String.valueOf(SUBTOTAL) + " S.R");
        VATLabel.setText("VAT(15%): " + String.valueOf(TAX) + " S.R");
        TotalLabel.setText("Total: " + String.valueOf(TOTAL) + " S.R");
        
        cust_ord2 = new Customer_Order(SUBTOTAL, TAX, TOTAL);
    }
    
    public void writeToFile(){
        BufferedWriter Customer_Order;
        try{
            Customer_Order = new BufferedWriter(new FileWriter("Customer_Order.txt", true));
            Customer_Order.write((String) subtotalLabel.getText() + "\n");
            Customer_Order.write((String) VATLabel.getText() + "\n");
            Customer_Order.write((String) TotalLabel.getText() + "\n");
            
            System.out.println("Customer order are added successfully");
            Customer_Order.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Error writing to file");
        }
        catch(IOException ex){
            System.out.println("Error writing to file");
        }
    }
    
    public void readFromFile(){
        BufferedReader Customer_Order;
        String CustomerOrder = null;
        
        try{
            Customer_Order = new BufferedReader(new FileReader("Customer_Order.txt"));

            while((CustomerOrder = Customer_Order.readLine()) != null){
                System.out.println(CustomerOrder);
            }
            System.out.println("Customer information and order are read successfully");
            Customer_Order.close();
        }    
        catch(FileNotFoundException ex){
            System.out.println("Error reading from file");
        }
        catch(IOException ex){
            System.out.println("Error reading from file");
        }
    }
    
    private class confirmListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //Write customer and order information to the file
            writeToFile();
            //Read customer and order information from the file
            readFromFile();
            
            System.exit(0);
        }
    }
    
    //Customer_Order class
    //----------------------------------------------------------------------------------------------------------------------
    
    public class Customer_Order implements Serializable{
        private String name;
        private String phone;
        private String in_out;
        
        private double subtotal;
        private double tax;
        private double total;
        
        public Customer_Order(String name, String phone, String in_out){
            this.name = name;
            this.phone = phone;
            this.in_out = in_out;
        }
        
        public Customer_Order(double subtotal, double tax, double total){
            this.subtotal = subtotal;
            this.tax = tax;
            this.total = total;
        }
        
        public void setName(String name){
            this.name = name;
        }
        
        public void setPhone(String phone){
            this.phone = phone;
        }
        
        public void setIn_out(String in_out){
            this.in_out = in_out;
        }
        
        public void setSubtotal(double subtotal){
            this.subtotal = subtotal;
        }
        
        public void setTax(double tax){
            this.tax = tax;
        }
        
        public void setTotal(double total){
            this.total = total;
        }
        
        public String getName(){
            return name;
        }
        
        public String getPhone(){
            return phone;
        }
        
        public String getIn_out(){
            return in_out;
        }
        
        public double getSubTotal(){
            return subtotal;
        }
        
        public double getTax(){
            return tax;
        }
        
        public double getTotal(){
            return total;
        }
    }
}