import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class InterfaceBatailleNavalle {
	
	public InterfaceBatailleNavalle() {
		
		JFrame f= new JFrame();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(new BorderLayout());
		
		JLabel tour = new JLabel("INFO",JLabel.CENTER);
		tour.setPreferredSize(new Dimension(50,50));
		tour.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		
		JPanel plateau =new JPanel(new GridLayout(1,2,50,50)); 
		
		
		JPanel p1=new JPanel(new GridLayout(10,10));
		JButton [][] b1=new JButton[10][10];
		
		for (int i=0;i<b1.length;i++)
			for (int j=0;j<b1[i].length;j++) {
				b1[i][j]=new JButton();
				b1[i][j].setBackground(Color.RED);
				b1[i][j].setPreferredSize(new Dimension(80,80));
				p1.add(b1[i][j]);
			}
		plateau.add(p1);
		
		JPanel p2=new JPanel(new GridLayout(10,10));
		JButton [][] b2=new JButton[10][10];
		
		for (int i=0;i<b2.length;i++)
			for (int j=0;j<b2[i].length;j++) {
				b2[i][j]=new JButton();
				b2[i][j].setPreferredSize(new Dimension(80,80));
				b2[i][j].setBackground(Color.BLUE);
				p2.add(b2[i][j]);
			}
		plateau.add(p2);
		
		JLabel res = new JLabel("choisir une position");
		res.setPreferredSize(new Dimension(25,25));
		
		f.setLayout(new BorderLayout());
		f.add(tour, BorderLayout.PAGE_START);
		f.add(plateau,BorderLayout.CENTER);
		f.add(res,BorderLayout.PAGE_END);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new InterfaceBatailleNavalle();
	}
    
}
