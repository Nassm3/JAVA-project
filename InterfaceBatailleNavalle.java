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

	public String[] choosePanel(){ 
		String name = new String();
		int difficulty = -1;
		int gameSize = -1;
		int placementOption = -1;

		JFrame frame = new JFrame("Paramètre de jeu");

		JPanel panelPseudo = new JPanel(new GridLayout(1, 2));
			JLabel pseudo = new JLabel("Saisir votre pseudo :");
			JTextField pseudoField = new JTextField(20);
			panelPseudo.add(pseudo);
			panelPseudo.add(pseudoField);

		JPanel panelChoixDifficulty = new JPanel(new GridLayout(2, 1));
			JPanel parameterDifficulty = new JPanel(new FlowLayout(FlowLayout.LEFT));
			String E[] = { "Facile", "Normale"};
			JComboBox<String> combobox = new JComboBox<String>(E);
			JLabel l1 = new JLabel("choisissez la difficulté de l'ordinateur");
			parameterDifficulty.add(l1);
			parameterDifficulty.add(combobox);
			panelChoixDifficulty.add(parameterDifficulty);

		JPanel panelChoixSize = new JPanel(new GridLayout(2, 1));
			JPanel parameterSize = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			String E1[] = { "10", "20", "30", "40", "50", "60"};
			JComboBox<String> comboboxSize = new JComboBox<String>(E1);
			JLabel l2 = new JLabel("choisissez la taille du plateau");
			parameterSize.add(l2);
			parameterSize.add(comboboxSize);
			panelChoixSize.add(parameterSize);

		JButton jouer = new JButton("Jouer");
			jouer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
						name = new String(pseudoField.getText());
						if(combobox.getSelectedItem().equals("Facile")){
							difficulty = 0;
						}
						else{
							difficulty = 1
						}

						
						
				}
			});
		frame.add(jouer, BorderLayout.PAGE_END);
		frame.add(panelPseudo, BorderLayout.LINE_START);
		frame.add(panelChoixDifficulty, BorderLayout.WEST);
		frame.add(panelChoixSize, BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		String[] tab = {String.valueOf(name), String.valueOf(difficulty), String.valueOf(gameSize), String.valueOf(placementOption)}; 

		return tab;



		



	}
	
	public static void main(String[] args) {
		new InterfaceBatailleNavalle(10).choosePanel();;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
		
	}
    
}
