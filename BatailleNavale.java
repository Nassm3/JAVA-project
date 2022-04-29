import java.awt.event.*;
import java.util.Random;

import javax.swing.JLabel;



public class BatailleNavale implements ActionListener{
    Plateau pg;
    Plateau pd;
    int gameSize;
    Joueur joueur;
    int tour;
    String gamemode;
    InterfaceBatailleNavalle ib;
    Bateau[][] bateau;
    String name;
    public BatailleNavale(){
        ChoosePanel cp = new ChoosePanel();
        cp.choosePanel();
        
        name = cp.getName();
        int difficulty = cp.getDifficulty();

        if (difficulty == 0){
            gamemode = new String("Easy");
            joueur = new Joueur(name);}
        else if (difficulty == 1){
            gamemode = new String("Normal");
            joueur = new Joueur(name);
        }
        else{
            gamemode = new String("JcJ");
            joueur = new Joueur(name);
        }
        
        gameSize = cp.getGameSize();
        pg = new Plateau(gameSize);
        this.bateau = pg.getBateau();

        pd = new Plateau(gameSize);
        int placementOption = cp.getPlacementOption();
        if (placementOption == 0){
            putBoatOnGame(pg, difficulty, gameSize);
            putBoatOnGame(pd, difficulty, gameSize);
        }
        else {
            
        }                
        ib = new InterfaceBatailleNavalle(gameSize, this, placementOption, pg,pd, joueur);
        ib.revealBoat(pd);
    }

    public boolean checkIndexExistence(Bateau[][] bateau, int i, int j, int ii, int jj){
        try {
        	if (bateau[i][j] == null || bateau[ii][jj] == null) {
                bateau[i][j] = new Bateau();      
                bateau[ii][jj] = new Bateau();
                bateau[i][j] = null;      
                bateau[ii][jj] = null;
                return true;
        	}
        	else {
        		return false;
        	}
            
        } 
        catch (Exception e) {
            return false;
        }
        }

    public void putBoatOnGame(Plateau p, int difficulty, int gameSize){
        if (difficulty != 2){
            int size = 2;
            while (size<6) {
                if ((new Random().nextInt(2)) == 0){
                    int ii;
                    int jj;
                    ii = new Random().nextInt(gameSize-size+1);
                    jj = new Random().nextInt(gameSize-size+1);
                    p.putBoat(ii, jj, ii+size-1, jj, size, this);
                }
                else{
                    int ii;
                    int jj;
                    ii = new Random().nextInt(gameSize-size+1);
                    jj = new Random().nextInt(gameSize-size+1);               
                    p.putBoat(ii, jj, ii, jj+size-1, size, this);
                }
                size++;
            }
        }
                
    }     	
    
    public boolean rejouer(JLabel c) {
        if (ib.getinfo(c)!="Manqué !") {
        	return true;
        }
        return false;
        
        
    }
    
    public void actionPerformed(ActionEvent e) { 
        String[] coordinates = e.getActionCommand().split("-");
        if (coordinates[0].equals("a")){
           int i = Integer.parseInt(coordinates[1]);
           int j = Integer.parseInt(coordinates[2]);
           System.out.println(coordinates[0] +"" + i+" "+j);
        }
        else{
            int i = Integer.parseInt(coordinates[0]);
            int j = Integer.parseInt(coordinates[1]);
            int ii=new Random().nextInt(gameSize-1);
            int jj=new Random().nextInt(gameSize-1);
            tour=0;
        	ib.remplir(i, j,tour);
        	if(!rejouer(ib.tourj))
            	tour++;
            	tour=tour%2;
            	ib.remplir(ii,jj,1);

        		

    
            
            if(pg.gagnant())
            	if (ib.affichegagnant(name)==1) 
            		ib.endGame();
            	else {
            		ib.dispose();
            		new BatailleNavale();
            	}	
    	}
    }
    

    public static void main(String[] args) {
        BatailleNavale bn;
        bn = new BatailleNavale();
    }
}
