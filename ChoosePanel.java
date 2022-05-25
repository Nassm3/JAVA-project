import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChoosePanel implements ActionListener{
    public JDialog f;
    String name;
    JComboBox<String> combobox;
    JComboBox<String> ratio;
    JComboBox<String> par;
    JTextField pseudotext;
	int difficulty = -1;
	int gameSize = -1;
	int placementOption = -1;
    ChoosePanel c ;
    public ChoosePanel(){
        
    }

    public static JPanel encapsuleComp(JComponent c) {
    	JPanel pan = new JPanel();
    	pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
    	pan.add(Box.createVerticalGlue());
    	pan.add(c);
    	pan.add(Box.createVerticalGlue());
    	return pan;
    }
		

	public void choosePanel(){ 
		
		
		f=new JDialog((Frame)null,"Séléction des paramètres",true);    
        String L[]={"Facile","Moyen"};        
        combobox=new JComboBox<String>(L);
        
        String P[]={"Aléatoire","Joueur"};        
        par =new JComboBox<String>(P);
        
        String C[]={"16","32","64"};        
        ratio = new JComboBox<String>(C);
        
        
        JLabel txt1=new JLabel("Difficulté",JLabel.CENTER);
        JLabel txt2=new JLabel("Taille du plateau",JLabel.CENTER);
        JLabel txt3=new JLabel("Mise en place des bateaux",JLabel.CENTER);
        
        JPanel centre=new JPanel(new GridLayout(3,2));
        centre.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        centre.add(encapsuleComp(combobox));
        centre.add(txt1);
        centre.add(encapsuleComp(ratio));
        centre.add(txt2);
        centre.add(encapsuleComp(par));
        centre.add(txt3);
        
        
        JPanel pseudo= new JPanel(new FlowLayout(FlowLayout.LEFT));
        pseudotext = new JTextField(20);
        JLabel p=new JLabel("votre pseudo :");
        pseudo.add(p);
        pseudo.add(pseudotext);
        
        JButton jouer=new JButton("JOUER");
        jouer.setPreferredSize(new Dimension(50,50));
		jouer.addActionListener(this);
        jouer.setActionCommand(pseudotext.getText()+"-"+ combobox.getSelectedItem().toString()+"-"+ ratio.getSelectedItem().toString()+"-"+par.getSelectedItem().toString());
        
        f.setLayout(new BorderLayout());
		f.add(pseudo,BorderLayout.PAGE_START);
        f.add(centre,BorderLayout.CENTER);
        f.add(jouer,BorderLayout.PAGE_END);
        f.pack();
        f.setSize(new Dimension(400,500));
        f.setLocationRelativeTo(null);
        f.setVisible(true); 
    }

    public String getName(){return name;}
    public int getDifficulty(){return difficulty;}
    public int getGameSize(){return gameSize;}
    public int getPlacementOption(){return placementOption;}

    public void actionPerformed(ActionEvent e) {
       
        name = new String(pseudotext.getText());

        if(combobox.getSelectedItem().equals("Facile")) {
            difficulty = 0;}
	    
	    else if(combobox.getSelectedItem().equals("Moyen")) {
            difficulty = 1;}
            
        else{
            difficulty = 2;}
                                
    
        gameSize = Integer.parseInt((String)ratio.getSelectedItem().toString());
        
        
        
                                
        if(par.getSelectedItem().equals("Aléatoire")){
            placementOption = 0;}
                                
        else{
            placementOption = 1;}
        
        f.dispose();
    }
     

}
