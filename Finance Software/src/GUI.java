import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder; 

// Date 
// Bank Account 
// Liquid Assets
// Total Calculation 

public class GUI {
	private final int BORDER_SIZE = 1; 
	private final Color BORDER_COLOR = Color.BLACK; 
	private final int DIMENSIONS_X = 960; 
	private final int DIMENSIONS_Y = 540; 
	private final int ROWS = 0; 
	private final int COLUMNS = 4; 
	private int lines = 0; 
    private FileWriter myWriter = null;
	private BufferedReader reader = null;

	public GUI() {
		try {
			reader = new BufferedReader(new FileReader("data"));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		try {
			while (reader.readLine() != null) this.lines++;
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			reader.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		System.out.println("File line count: " + this.lines);
	} 
	
	private void setupTextField(JTextField field, Font newFont, boolean editable, boolean writeData) { 		
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setBorder(new LineBorder(BORDER_COLOR, BORDER_SIZE));
	    field.setEditable(editable);
	    field.setFont(newFont);	
	    
	    if (writeData) { 
		    field.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					
				}
	
				public void focusLost(FocusEvent e) {		
			    	lines++; 
			    	System.out.println("Final Line Count:" + lines); 
			    	
			    	String finalLine = field.getText();
			    	
			    	for (int i = 0; i < lines; i++) { 
			    		finalLine += "\n";
			    	}
			    	
					System.out.println(finalLine);
					
				    try {				    	
						myWriter = new FileWriter("data", true);
				        myWriter.write(finalLine);
				        myWriter.close();
				      } catch (IOException e1) {
				        e1.printStackTrace();
				      }
				} 
		    });
	    }
	}
	
	public void init() { 
		Font headerFont = new Font("Times New Roman", Font.BOLD, 20); 
		Font regularFont = new Font("Times New Roman", Font.PLAIN, 20); 
		
		JFrame frame = new JFrame("Finance Software by Edrin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(DIMENSIONS_X, DIMENSIONS_Y);	
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
        JPanel panel = new JPanel();
        
		GridLayout gridLayout = new GridLayout(ROWS, COLUMNS);
		panel.setLayout(gridLayout);
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(DIMENSIONS_X - 10, DIMENSIONS_Y);
        
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(DIMENSIONS_X, DIMENSIONS_Y));
        contentPane.add(scrollPane);
                
        JTextField date = new JTextField("Date:"); 
        setupTextField(date, headerFont, false, false);
		panel.add(date);
		
        JTextField bankAccount = new JTextField("Bank Account:"); 
        setupTextField(bankAccount, headerFont, false, false);
		panel.add(bankAccount);
		
		JTextField liquidAssets = new JTextField("Liquid Assets:"); 
        setupTextField(liquidAssets, headerFont, false, false);
		panel.add(liquidAssets);
			
		JTextField total = new JTextField("Total:"); 
        setupTextField(total, headerFont, false, false);
		panel.add(total);
		
		for (int i = 0; i < 100; i++) { 
			JTextField newFrame = new JTextField(); 
	        setupTextField(newFrame, regularFont, true, true);
			panel.add(newFrame);
		}
		
        frame.setContentPane(contentPane);
        frame.setVisible(true);
	}
}
