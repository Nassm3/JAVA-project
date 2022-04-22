import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.util.StringConverter;

public class InterfaceBatailleNavalle implements ActionListener{
	JFrame f = new JFrame();
	public InterfaceBatailleNavalle(int size, ActionListener listener) {
		
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(new BorderLayout());
		
		JLabel tour = new JLabel("INFO",JLabel.CENTER);
		tour.setPreferredSize(new Dimension(50,50));
		tour.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		
		JPanel plateau =new JPanel(new GridLayout(1,2,50,50)); 
		
		
		JPanel p1=new JPanel(new GridLayout(size,size));
		JButton [][] b1=new JButton[size][size];
		
		for (int i=0;i<b1.length;i++)
			for (int j=0;j<b1[i].length;j++) {
				b1[i][j]=new JButton();
				b1[i][j].setBackground(Color.RED);
				b1[i][j].setPreferredSize(new Dimension(80,80));
				p1.add(b1[i][j]);
			}
		plateau.add(p1);
		
		JPanel p2=new JPanel(new GridLayout(size,size));
		JButton [][] b2=new JButton[size][size];
		
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
<<<<<<< HEAD
	
    public static JPanel encapsuleComp(JComponent c) {
    	JPanel pan = new JPanel();
    	pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
    	pan.add(Box.createVerticalGlue());
    	pan.add(c);
    	pan.add(Box.createVerticalGlue());
    	return pan;
    }
		

	public String[] choosePanel(){ 
		String name = new String();
		int difficulty = -1;
		int gameSize = -1;
		int placementOption = -1;
		
		JFrame f=new JFrame("Séléction des paramètres");    
        String L[]={"Facile","Moyen"};        
        JComboBox combobox=new JComboBox(L);
        
        String P[]={"Aléatoire","Joueur"};        
        JComboBox par=new JComboBox(P);
        
        String C[]={"16","32","64"};        
        JComboBox ratio=new JComboBox(C);
        
        
        JLabel txt1=new JLabel("taille",JLabel.CENTER);
        JLabel txt2=new JLabel("par2",JLabel.CENTER);
        JLabel txt3=new JLabel("par3",JLabel.CENTER);
        
        JPanel centre=new JPanel(new GridLayout(3,2));
        centre.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        centre.add(encapsuleComp(combobox));
        centre.add(txt1);
        centre.add(encapsuleComp(ratio));
        centre.add(txt2);
        centre.add(encapsuleComp(par));
        centre.add(txt3);
        
        
        JPanel pseudo= new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField pseudotext=new JTextField(20);
        JLabel p=new JLabel("votre pseudo :");
        pseudo.add(p);
        pseudo.add(pseudotext);
        
        JButton jouer=new JButton("JOUER");
        jouer.setPreferredSize(new Dimension(50,50));
		jouer.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				name = new String(pseudotext.getText());
				if(combobox.getSelectedItem().equals("Facile"))
					difficulty = 0;
					
				else
					difficulty = 1;
										
				if(ratio.getSelectedItem().equals("16"))
					gameSize=0;
										
				else if (ratio.getSelectedItem().equals("32"))
					gameSize=1;
										
				else 
					gameSize=2;
										
				if(par.getSelectedItem().equals("Aléatoire"))
					placementOption=0;
										
				else
					placementOption=1;
			}
		});
		
        
        
		f.setLayout(new BorderLayout());
		f.add(pseudo,BorderLayout.PAGE_START);
        f.add(centre,BorderLayout.CENTER);
        f.add(jouer,BorderLayout.PAGE_END);
        f.pack();
        f.setSize(new Dimension(400,500));
        f.setLocationRelativeTo(null);
        f.setVisible(true);   
		
        String[] tab = {name, String.valueOf(difficulty), String.valueOf(gameSize), String.valueOf(placementOption)}; 

		return tab;

	}
	

	
    
	public static void main(String[] args) {
		new InterfaceBatailleNavalle(10).choosePanel();
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
		
	}
    
}
