import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



public class InterfaceBatailleNavalle {
	Plateau plat;
	Plateau bot;
	Joueur joueur;
	JButton [][] b1;
	JButton [][] b2;
	JPanel plateau; 
	JPanel p1;
	JFrame f = new JFrame("Bataille Navale !");
	JLabel tourj;
	JLabel tourB;
	BatailleNavale bn;

	JButton bat1, bat2, bat3, bat4;
	int c1, c2, c3, c4;
	JPanel z1, z2, z3, z4;
	JLabel compteur1, compteur2, compteur3, compteur4;


		
		
		public InterfaceBatailleNavalle(int size, ActionListener listener,int mode, Plateau plat,Plateau bot, Joueur joueur) {	
			this.bot=bot;
			this.plat = plat;
			this.joueur = joueur;
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setLayout(new BorderLayout());
			
			tourj=new JLabel();
			tourj.setText("INFO joueur");
			tourj.setPreferredSize(new Dimension(100,100));
			tourj.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
			tourj.setBackground(Color.LIGHT_GRAY);
			
			tourB=new JLabel();
			tourB.setText("INFO adversaire");
			tourB.setPreferredSize(new Dimension(100,100));
			tourB.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
			tourB.setBackground(Color.LIGHT_GRAY);
			 JPanel tour =new JPanel(new GridLayout(1,2));
			tour.add(tourj);
			tour.add(tourB);
			
			
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
					b1[i][j].setEnabled(false);
					b1[i][j].setPreferredSize(new Dimension(80,80));
					p1.add(b1[i][j]);
				}
			plateau.add(p1);
			
			JPanel p2=new JPanel(new GridLayout(size,size));
			b2=new JButton[size][size];
			
			for (int i=0;i<b2.length;i++)
				for (int j=0;j<b2[i].length;j++) {
					b2[i][j]=new JButton();
					b2[i][j].addActionListener(listener);
					b2[i][j].setActionCommand("a-"+i+"-"+j);
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
				bat1 = new JButton();
				bat1.addActionListener(listener);
				
				bat1.setActionCommand("a-b-2");
				bat1.setIcon(new ImageIcon (new ImageIcon("bat4.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));

				z1 = new JPanel(new GridLayout(2,1));
				c1 = 1;
				compteur1 = new JLabel("compteur : "+ c1 );
				z1.add(compteur1);
				z1.add(new JLabel("taille du bateau : 2"));
				ra.add(bat1);
				ra.add(z1);

				

				
				JPanel re=new JPanel(new FlowLayout(FlowLayout.LEFT));
				bat2 = new JButton();
				bat2.addActionListener(listener);
				
				bat2.setIcon(new ImageIcon (new ImageIcon("bat1.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));
				bat2.setActionCommand("a-b-3");
				c2 = 1;
				JPanel z2= new JPanel(new GridLayout(2,1));
				compteur2 = new JLabel("compteur : "+ c2 );

				z2.add(compteur2);
				z2.add(new JLabel("taille du bateau : 3"));
				re.add(bat2);
				re.add(z2);
				
				JPanel ri=new JPanel(new FlowLayout(FlowLayout.LEFT));
				bat3 = new JButton();
				bat3.addActionListener(listener);
				
				bat3.setActionCommand("a-b-4");
				bat3.setIcon(new ImageIcon (new ImageIcon("bat3.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));

				z3= new JPanel(new GridLayout(2,1));
				c3 = 1;
				compteur3 = new JLabel("compteur : "+ c3 );

				z3.add(compteur3);
				z3.add(new JLabel("taille du bateau : 4"));
				ri.add(bat3);
				ri.add(z3);
				
				JPanel ro=new JPanel(new FlowLayout(FlowLayout.LEFT));
				bat4 = new JButton();
				bat4.addActionListener(listener);
				

				bat4.setIcon(new ImageIcon (new ImageIcon("bat2.jpg").getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));
				bat4.setActionCommand("a-b-5");
				z4= new JPanel(new GridLayout(2,1));
				c4 = 1;
				compteur4 = new JLabel("compteur : "+ c4 );

				z4.add(compteur4);
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

	public void decreaseButton(int b){
		switch(b){
   
			case 2: 
				c1--;
				compteur1.setText("compteur : "+ c1);
				if (c1 <= 0) bat1.setEnabled(false);
				break;
		
			case 3:
				c2--;
				compteur2.setText("compteur : "+ c2);
				if (c2 <= 0) bat2.setEnabled(false);
				break;

			case 4:
				c3--;
				compteur3.setText("compteur : "+ c3);
				if (c3 <= 0) bat3.setEnabled(false);
			break;

			case 5:
				c4--;
				compteur4.setText("compteur : "+ c4);
				if (c4 <= 0) bat4.setEnabled(false);
				break;

			default:
				System.out.println("wtf, how did we get here");
				break;
		}
		if (c1 ==0 && c2 ==0 && c3 ==0 && c4 ==0)	{
			for (int i=0;i<b1.length;i++){
				for (int j=0;j<b1[i].length;j++) {
					b1[i][j].setEnabled(true);

				}
			}
		}
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
					b2[i][j].setEnabled(false);
				}
	}
	
	
	public void fillOnSunk(JButton[][] plateau){
		Case[][] grille=plat.getGrille();
        for(int y=0; y<grille.length; y++)
            for(int z=0; z<grille[y].length; z++)
				if(grille[y][z]==Case.SUNK) {
					plateau[y][z].setBackground(new JButton().getBackground());
					plateau[y][z].setIcon(null);
					plateau[y][z].setIcon(new ImageIcon (new ImageIcon("wreck.jpg").getImage().getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH)));}
	}

	public void remplir(int i, int j,int t){
			if (t==0) {
				String res = plat.jouer(i, j);
				String result = res.split("_")[0];
				String boat = res.split("_")[1];
				if (result.equals("touché-coulé")){
					b1[i][j].setEnabled(false);
					fillOnSunk(b1);
					tourj.setText("Touché-coulé " + boat + "!");
				}
				else if (result.equals("touché")){
					b1[i][j].setEnabled(false);
					b1[i][j].setIcon(null);
					b1[i][j].setBackground(Color.black);
					tourj.setText("Touché !");
				}
				else{
					tourj.setText("Manqué :(");
					b1[i][j].setBackground(Color.LIGHT_GRAY);
					b1[i][j].setEnabled(false);
				}
			}
			
		else {
			String res = bot.jouer(i, j);
			String result = res.split("_")[0];
			String boat = res.split("_")[1];
			if (result.equals("touché-coulé")){
				fillOnSunk(b2);
				tourB.setText("Touché-coulé " + boat + "!");
			}
			else if (result.equals("touché")){
				b2[i][j].setBackground(Color.BLACK);
				tourB.setText("Touché !");
			}
			else{
				b2[i][j].setBackground(Color.RED);
				tourB.setText("Manqué :(");
			}
		}
	}
	




		
	public String getinfo(JLabel c) {
		return c.getText();
	}

	public void revealBoat(Plateau p) {
	Case [][] grille=p.getGrille();
	for (int i=0;i<grille.length;i++)
		for (int j=0;j<grille[i].length;j++) 
			if (grille[i][j]==Case.BOAT) 
				b2[i][j].setBackground(Color.GREEN);
	}
	
	public int affichegagnant(String joueur) {
		return JOptionPane.showConfirmDialog(f,joueur+" a gagné !! Voulez-vous rejouer ??????","CRINGE ALERT",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
	}
    
	public void endGame() {
		JOptionPane.showMessageDialog(f,"Au revoir");
		dispose();
		System.exit(0);
	}
	public void dispose() {
		f.dispose();
	}
    
}
