import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ScrollPaneTest {
	private final static int DIMENSIONS_X = 960; 
	private final static int DIMENSIONS_Y = 540;
	
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(DIMENSIONS_X, DIMENSIONS_Y);	
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
        JPanel panel = new JPanel();
        
		GridLayout gridLayout = new GridLayout(0, 4);
		panel.setLayout(gridLayout);
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(DIMENSIONS_X - 10, DIMENSIONS_Y);
        
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(DIMENSIONS_X, DIMENSIONS_Y));
        contentPane.add(scrollPane);
        
        frame.setContentPane(contentPane);
        
		for (int i = 0; i < 5000; i++) { 
			JTextField test = new JTextField(); 
			test.setHorizontalAlignment(JTextField.CENTER);
			panel.add(test);
		}
		
        frame.setVisible(true);
    }
}