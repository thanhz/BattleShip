import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Write a description of class GameGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameGUI
{
    private WAM fred = new Admiral("Fred");

    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();

    private JPanel northPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel southPantel = new JPanel();

    //private JLabel shipJL = new JLabel("Enter a Ship name");
    private JTextField designJT = new JTextField(10);
    private JTextArea listing = new JTextArea();

    private JButton fightButton = new JButton("Fight Encounter");
    private JButton viewButton = new JButton("View Game State");
    private JButton quitButton = new JButton("Quit");
    private JButton clearButton = new JButton("Clear");

    public GameGUI()
    {
        makeMenuBar(myFrame); //set up the menu bar & items
        makeFrame();          //set up the content pane
    }

    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        myFrame.setPreferredSize(new Dimension(800,600));

        // Create the Ship menu
        JMenu shipMenu = new JMenu ("Ships");
        menubar.add(shipMenu);
        
        //demo
        JMenu encounterMenu = new JMenu ("Encounters");
        menubar.add(encounterMenu);
        
        //add Encounter menu
        JMenuItem fightEncounter = new JMenuItem("Fights an encounter");
        fightEncounter.addActionListener(new fightHandler());
        encounterMenu.add(fightEncounter);
        
        //add Ship menu
        JMenuItem listReserve = new JMenuItem("List ships in reserves");
        listReserve.addActionListener(new ListReserveHandler());
        shipMenu.add(listReserve);

        JMenuItem listFleet = new JMenuItem("List ships in fleet");
        listFleet.addActionListener(new ListFleetHandler());
        shipMenu.add(listFleet);

        JMenuItem commission = new JMenuItem("Commission Ship");
        commission.addActionListener(new CommissionHandler());
        shipMenu.add(commission);
    }

    private void makeFrame()
    {
        //set layout of content pane
        contentPane.setLayout(new BorderLayout());

        //add some components directly to content pane
        contentPane.add(listing,BorderLayout.CENTER); 
        listing.setVisible(false);

        contentPane.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(fightButton);
        eastPanel.add(viewButton);
        eastPanel.add(quitButton);
        eastPanel.add(clearButton); 
        
        fightButton.setVisible(true);
        viewButton.setVisible(true);
        quitButton.setVisible(true);
        clearButton.setVisible(false);

        // add action listener for the buttons
        // no need for listeners for radio buttons or textfields
        fightButton.addActionListener(new fightButtonHandler());
        viewButton.addActionListener(new viewButtonHandler());  
        quitButton.addActionListener(new quitButtonHandler());
        clearButton.addActionListener (new clearButtonHandler()); 

        // see below for method to make north & west areas
        makeInputAreas();

        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }

    private void makeInputAreas()
    {
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(westPanel, BorderLayout.WEST);

        northPanel.setVisible(false);
        westPanel.setVisible(true); 

        northPanel.setLayout(new GridLayout(6,1));  
    }

    private class  fightHandler implements ActionListener
    {
       public void actionPerformed(ActionEvent e) 
        {
            String encounterNumber = JOptionPane.showInputDialog("Encounter Number: ");
            int num = Integer.parseInt(encounterNumber);
            JOptionPane.showMessageDialog(myFrame,fred.fightEncounter(num));
        }
    }
    
    private class ListReserveHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            clearButton.setVisible(true);
            String s = fred.getReserves();  
            listing.setText(s);
            listing.setVisible(true); 
            myFrame.pack();
        }
    }

    private class ListFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            clearButton.setVisible(true);
            String s = fred.getFleet();  
            listing.setText(s);
            listing.setVisible(true); 
            myFrame.pack();
        }
    }

    private class  CommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String result = "";
            String name = JOptionPane.showInputDialog("Ship Name: ");
            listing.setVisible(false);
            //             hireButton.setVisible(false); 
            result = fred.commissionShip(name);
            JOptionPane.showMessageDialog(myFrame, result);
        }        
    }

    private class  fightButtonHandler implements ActionListener
    {
       public void actionPerformed(ActionEvent e) 
        {
            String encounterNumber = JOptionPane.showInputDialog("Encounter Number: ");
            int num = Integer.parseInt(encounterNumber);
            JOptionPane.showMessageDialog(myFrame,fred.fightEncounter(num));
        }
    }

    private class  viewButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(false);
            westPanel.setVisible(false);
            northPanel.setVisible(false); 
            eastPanel.setVisible(true);
            clearButton.setVisible(true);
            listing.setText(fred.toString());
            listing.setVisible(true);  
            myFrame.pack();
        }
    }

    private class  clearButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(false);
            clearButton.setVisible(false);
        }
    }

    private class  quitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int answer = JOptionPane.showConfirmDialog(myFrame,
                    "Are you sure you want to quit?","Finish",
                    JOptionPane.YES_NO_OPTION);

            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0); 
            }              
        }
    }
}
