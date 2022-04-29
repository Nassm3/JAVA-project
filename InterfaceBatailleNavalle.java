import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



public class InterfaceBatailleNavalle {
	Plateau plat;
	Joueur joueur;
	JButton [][] b1;
	JButton [][] b2;
	JPanel plateau; 
	JPanel p1;
	JFrame f = new JFrame("Bataille Navale !");
	JLabel tour;
	BatailleNavale bn;

		
		
		public InterfaceBatailleNavalle(int size, ActionListener listener,int mode, Plateau plat, Joueur joueur) {			
			this.plat = plat;
			this.joueur = joueur;
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setLayout(new BorderLayout());
			
			tour=new JLabel();
			tour.setText("INFO");
			tour.setPreferredSize(new Dimension(100,100));
			tour.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
			tour.setBackground(Color.LIGHT_GRAY);
			
			plateau =new JPanel(new GridLayout(1,2,50,50)); 
			
			
			p1 = new JPanel(new GridLayout(size,size));
			b1 = new JButton[size][size];
			ImageIcon icon = new ImageIcon (new ImageIcon("va.jpg").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
			for (int i=0;i<b1.length;i++)
				for (int j=0;j<b1[i].length;j++) {
					b1[i][j]=new JButton();
					b1[i][j].addActionListener(listener);
					b1[i][j].setActionCommand(i+"-"+j);
					b1[i][j].setIcon(icon);
					b1[i][j].setPreferredSize(new Dimension(80,80));
					p1.add(b1[i][j]);
				}
			plateau.add(p1);
			
			JPanel p2=new JPanel(new GridLayout(size,size));
			b2=new JButton[size][size];
			
			for (int i=0;i<b2.length;i++)
				for (int j=0;j<b2[i].length;j++) {
					b2[i][j]=new JButton();
					b2[i][j].setPreferredSize(new Dimension(80,80));
					b2[i][j].setBackground(Color.BLUE);
					b2[i][j].setEnabled(false);
					p2.add(b2[i][j]);
					
				}
			plateau.add(p2);
			
			JPanel bateaux;
			if (mode==1) {
				
				bateaux = new JPanel(new GridLayout(1,4));
				
				JPanel ra = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JButton bat1 = new JButton();
				bat1.setIcon(new ImageIcon (new ImageIcon("bat4.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));

				JPanel z1= new JPanel(new GridLayout(2,1));
				z1.add(new JLabel("compteur :"+"c"));
				z1.add(new JLabel("taille du bateau : 2"));
				ra.add(bat1);
				ra.add(z1);

				

				
				JPanel re=new JPanel(new FlowLayout(FlowLayout.LEFT));
				JButton bat2 = new JButton();
				bat2.setIcon(new ImageIcon (new ImageIcon("bat1.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));

				
				JPanel z2= new JPanel(new GridLayout(2,1));
				z2.add(new JLabel("compteur :"+"c"));
				z2.add(new JLabel("taille du bateau :3"));
				re.add(bat2);
				re.add(z2);
				
				JPanel ri=new JPanel(new FlowLayout(FlowLayout.LEFT));
				JButton bat3 = new JButton();
				bat3.setIcon(new ImageIcon (new ImageIcon("bat3.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));

				JPanel z3= new JPanel(new GridLayout(2,1));
				z3.add(new JLabel("compteur :"+"c"));
				z3.add(new JLabel("taille du bateau : 4"));
				ri.add(bat3);
				ri.add(z3);
				
				JPanel ro=new JPanel(new FlowLayout(FlowLayout.LEFT));
				JButton bat4 = new JButton();
				bat4.setIcon(new ImageIcon (new ImageIcon("bat2.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));
				
				JPanel z4= new JPanel(new GridLayout(2,1));
				z4.add(new JLabel("compteur :"+"c"));
				z4.add(new JLabel("taille du bateau : 5"));
				ro.add(bat4);
				ro.add(z4);
				
				bateaux.add(ra);
				bateaux.add(re);
				bateaux.add(ri);
				bateaux.add(ro);
				bateaux.setPreferredSize(new Dimension(100,150));
			}

			else {
			bateaux = new JPanel();
			bateaux.add(new Label("choisir une position"));
			bateaux.setPreferredSize(new Dimension(25,25));
			}
		
		
		
		f.setLayout(new BorderLayout());
		f.add(tour, BorderLayout.PAGE_START);
		f.add(plateau,BorderLayout.CENTER);
		f.add(bateaux,BorderLayout.PAGE_END);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	public void activateButton( Case [][] grille ){
		for (int i=0;i<b2.length;i++)
				for (int j=0;j<b2[i].length;j++) {
					if (grille[i][j] == Case.EMPTY){
						b2[i][j].setEnabled(true);
					} 
					
				}
	}
	public void deactivateButton(Case [][] grille){
		for (int i=0;i<b2.length;i++)
				for (int j=0;j<b2[i].length;j++) {
					b2[i][j].setEnabled(true);
				}
	}
	
	
	public void fillOnSunk(int i, int j){
		Bateau[][] bateau = plat.getBateau();
		Bateau boat = bateau[i][j];
		String[] coordinate = boat.getBoatCoordinate().split(";");
		for(int k=0;k<coordinate.length;k++){
			int y = Integer.parseInt(coordinate[k].split("-")[0]);
			int z = Integer.parseInt(coordinate[k].split("-")[1]);
			b1[y][z].setBackground(new JButton().getBackground());
			b1[y][z].setIcon(null);
			b1[y][z].setIcon(new ImageIcon (new ImageIcon("wreck.jpg").getImage().getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH)));}
	}

	public void remplir(int i, int j){
		String res = plat.jouer(i, j);
		String result = res.split("_")[0];
		String boat = res.split("_")[1];

		if (result.equals("touché-coulé")){
			b1[i][j].setEnabled(false);
			fillOnSunk(i, j);
			tour.setText("Touché-coulé" + boat + "!");
		}
		else if (result.equals("touché")){
			b1[i][j].setEnabled(false);
			b1[i][j].setIcon(null);
			b1[i][j].setBackground(Color.black);
			tour.setText("Touché !");
		}
		else{
			tour.setText("Manqué :(");
			b1[i][j].setEnabled(false);
		}
	
}
		

public void revealBoat(Plateau p) {
	Case [][] grille=p.getGrille();
	for (int i=0;i<grille.length;i++)
		for (int j=0;j<grille[i].length;j++) 
			if (grille[i][j]==Case.BOAT) 
				b2[i][j].setBackground(Color.GREEN);
}
    
	public static void main(String[] args) {
	}

	public void actionPerformed(ActionEvent e) {
		
	}
    
}
