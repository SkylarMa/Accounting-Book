import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.io.*;

import javafx.scene.control.DatePicker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

// *************************************

public class AccountingBook extends JFrame
{
	JMenuItem lookUp = new JMenuItem("Look up record");
	JMenuItem export = new JMenuItem("Export to .txt");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem about = new JMenuItem("About Author / Version");
	JMenuItem thank = new JMenuItem("Thanks for...");
	JLabel jLabel1 = new JLabel("Date");
	JLabel jLabel2 = new JLabel("Category");
	JLabel jLabel3 = new JLabel("Detail");
	JLabel jLabel4 = new JLabel("Amount");
	JTextField jTextField1 = new JTextField();
	JButton jButton1 = new JButton("Submit");
	JButton jButton2 = new JButton("Clean");
	DatePicker datePicker;
	String[] categories =
		{ "Breakfast", "Lunch", "Dinner", "Dessert", "Stationery", "Transportation Cost", "clothes" };
	JComboBox<String> box1 = new JComboBox<>(categories);
	String output = "";
	
	AccountingBook()
	{
		JFrame frame = new JFrame("Accounting Book");
		JFXPanel fxPanel = new JFXPanel(); // create FXPanel in order to set DatePicker
		frame.getContentPane().setLayout(new GridBagLayout()); // set layout species
		frame.add(fxPanel); // put faPanel into JFrame
		StackPane root = new StackPane(); // StackPane allows many components stack together, and combines into a new component
      Scene scene = new Scene(root); // Scene is the class and the container for all content in a scene graph. The background of the scene is filled as specified by the fill property
      
      // --- setDatePicker ---
      datePicker = new DatePicker();
      root.getChildren().add(datePicker);
      // After the StackPane is set, the object is added to the StackPane with the getChildren().Add() method of the Pane class
      fxPanel.setScene(scene);
      
		// --- setAmount ---
		MaskFormatter formatter = null;
		try
		{
			formatter = new MaskFormatter("$ " + "###,###");
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		JFormattedTextField jFormattedTextField = new JFormattedTextField(formatter);
		
		// -- setButtonListener ---
		jButton1.setActionCommand("subnit");
		jButton2.setActionCommand("clear");
		ActionListener actionListener = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (e.getActionCommand().equals("clear"))
				{
					int result_clear;
					result_clear = JOptionPane.showConfirmDialog(null, "This information will be cleared.", "Confirm Again", JOptionPane.YES_NO_OPTION);
					switch (result_clear)
					{
						case JOptionPane.YES_OPTION:
							jTextField1.setText("");
							jFormattedTextField.setText("");
							datePicker.setValue(null);
							break;
						case JOptionPane.NO_OPTION:
							break;
						case JOptionPane.CANCEL_OPTION:
							break;
						case JOptionPane.CLOSED_OPTION:
							break;
					}
					
					
				}
				else
				{
					int result_submit;
					result_submit = JOptionPane.showConfirmDialog(null, "This information will be submitted.", "Confirm Again", JOptionPane.YES_NO_OPTION);
					switch (result_submit)
					{
						case JOptionPane.YES_OPTION:
							// --- DIY ---
							break;
						case JOptionPane.NO_OPTION:
							break;
						case JOptionPane.CANCEL_OPTION:
							break;
						case JOptionPane.CLOSED_OPTION:
							break;
					}
				}
			}
		};
		jButton1.addActionListener(actionListener);
		jButton2.addActionListener(actionListener);
		
		// --- MenuBar ---
		JMenu menu1 = new JMenu("File");
		menu1.add(lookUp);
		menu1.add(export);
		menu1.add(exit);
		menu1.setMnemonic(KeyEvent.VK_F);
		lookUp.setMnemonic(KeyEvent.VK_L);
		export.setMnemonic(KeyEvent.VK_X);
		exit.setMnemonic(KeyEvent.VK_E);
		JMenu menu2 = new JMenu("About");
		menu2.add(about);
		menu2.add(thank);
		menu2.setMnemonic(KeyEvent.VK_A);
		about.setMnemonic(KeyEvent.VK_B);
		thank.setMnemonic(KeyEvent.VK_T);
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(menu1);
		bar.add(menu2);
		lookUp.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog
				(null,
				"Here are your records :\n" + output,
				"Look up Record",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		export.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
					out.write(output + "\n");  //Replace with the string out.close();
					out.close();
				}
				catch (IOException err)
				{
					System.out.println("Exception ");
				}
			}
		});
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		about.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				
				JOptionPane.showMessageDialog
				(null,
				"Author: Skylar Ma\nVersion: 1.0.3\nMy WebSite: www.isuper.thisistap.com",
				"About",
				JOptionPane.INFORMATION_MESSAGE);
			}
		});
		thank.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				
				JOptionPane.showMessageDialog
				(null,
				"I'd like to thank my SD team members.  So far, I learned a lot from you.\n" +
				"Well, I'd also like to thank all the teachers who have taught me.\n" +
				"Writed by Skylar Ma.",
				"About",
				JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// --- setGridBagConstraints ---
		GridBagConstraints  c1 = new GridBagConstraints();
		GridBagConstraints  c2 = new GridBagConstraints();
		GridBagConstraints  c3 = new GridBagConstraints();
		GridBagConstraints  c4 = new GridBagConstraints();
		GridBagConstraints  c5 = new GridBagConstraints();
		GridBagConstraints  c6 = new GridBagConstraints();
		GridBagConstraints  c7 = new GridBagConstraints();
		GridBagConstraints  c8 = new GridBagConstraints();
		GridBagConstraints  c9 = new GridBagConstraints();
		GridBagConstraints c10 = new GridBagConstraints();
		
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridwidth = 1;
		c1.gridheight = 1;
		c1.insets = new Insets(20, -80, 20, 0);
		c1.fill = GridBagConstraints.BOTH;
		c1.anchor = GridBagConstraints.CENTER;
		
		c2.gridx = 1;
		c2.gridy = 0;
		c2.gridwidth = 1;
		c2.gridheight = 1;
		c2.insets = new Insets(20, -50, 20, 20);
		c2.fill = GridBagConstraints.BOTH;
		c2.anchor = GridBagConstraints.CENTER;
		c3.gridx = 0;
		c3.gridy = 1;
		c3.gridwidth = 1;
		c3.gridheight = 1;
		c3.insets = new Insets(20, -80, 20, 0);
		c3.fill = GridBagConstraints.BOTH;
		c3.anchor = GridBagConstraints.CENTER;
		
		c4.gridx = 1;
		c4.gridy = 1;
		c4.gridwidth = 1;
		c4.gridheight = 1;
		c4.insets = new Insets(20, -50, 20, 20);
		c4.fill = GridBagConstraints.BOTH;
		c4.anchor = GridBagConstraints.CENTER;
		
		c5.gridx = 0;
		c5.gridy = 2;
		c5.gridwidth = 1;
		c5.gridheight = 1;
		c5.insets = new Insets(20, -80, 20, 0);
		c5.fill = GridBagConstraints.BOTH;
		c5.anchor = GridBagConstraints.CENTER;
		
		c6.gridx = 1;
		c6.gridy = 2;
		c6.gridwidth = 1;
		c6.gridheight = 1;
		c6.insets = new Insets(20, -50, 20, 20);
		c6.fill = GridBagConstraints.BOTH;
		c6.anchor = GridBagConstraints.CENTER;
		
		c7.gridx = 0;
		c7.gridy = 3;
		c7.gridwidth = 1;
		c7.gridheight = 1;
		c7.insets = new Insets(20, -80, 20, 0);
		c7.fill = GridBagConstraints.BOTH;
		c7.anchor = GridBagConstraints.CENTER;
		
		c8.gridx = 1;
		c8.gridy = 3;
		c8.gridwidth = 1;
		c8.gridheight = 1;
		c8.insets = new Insets(20, -50, 20, 20);
		c8.fill = GridBagConstraints.BOTH;
		c8.anchor = GridBagConstraints.CENTER;
		
		c9.gridx = 0;
		c9.gridy = 4;
		c9.gridwidth = 1;
		c9.gridheight = 1;
		c9.insets = new Insets(20, -20, 20, 0);
		c9.fill = GridBagConstraints.BOTH;
		c9.anchor = GridBagConstraints.CENTER;
		
		c10.gridx = 1;
		c10.gridy = 4;
		c10.gridwidth = 1;
		c10.gridheight = 1;
		c10.insets = new Insets(20, 30, 20, 20);
		c10.fill = GridBagConstraints.BOTH;
		c10.anchor = GridBagConstraints.CENTER;
        
		// --- putComponentIntoFrame ---
		frame.setJMenuBar(bar);
		frame.add(jLabel1, c1);
		frame.add(fxPanel, c2);
		frame.add(jLabel2, c3);
		frame.add(box1, c4);
		frame.add(jLabel3, c5);
		frame.add(jTextField1, c6);
		frame.add(jLabel4, c7);
		frame.add(jFormattedTextField, c8);
		frame.add(jButton1, c9);
		frame.add(jButton2, c10);
		
		// --- setFrameInformation ---
		frame.setBounds(700, 300, 500, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String dummy[])
	{
		// ---- DIY ----
	}
}
