import java.awt.event.*;
import java.util.Random;

import javax.swing.JLabel;



public class BatailleNavale implements ActionListener{
    Plateau pg;
    Plateau pd;
    Case[][] grillepg;
    Case[][] grillepd;
    int gameSize;
    Joueur joueur;
    int tour;
    String gamemode;
    InterfaceBatailleNavalle ib;
    Bateau[][] bateau;
    String name;
    int cpt;
    int placeLigneStart, placeLigneEnd, placeColStart, placeColEnd, placeSize;
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
        pd = new Plateau(gameSize);
        this.grillepg = pg.getGrille();
        this.grillepd = pd.getGrille();

        this.bateau = pg.getBateau();

        
        int placementOption = cp.getPlacementOption();
        ib = new InterfaceBatailleNavalle(gameSize, this, placementOption, pg, pd, joueur);
        if (placementOption == 0){
            putBoatOnGame(pd, 0, gameSize);
            ib.activateButton(grillepg, 1);
        }
        else {

        }
        putBoatOnGame(pg, difficulty, gameSize);
            
        //System.out.println("Hi " + name + ", you're playing in " + gamemode + " on a " + gameSize + "*" + gameSize + " plateau");
        
        
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

            if (coordinates[1].equals("b")){
                cpt = 0;
                ib.activateButton(pd.getGrille(), 2);
                placeSize = Integer.parseInt(coordinates[2]);
            }
            else if (coordinates[0].equals("a")){
                if (cpt == 0){
                    placeLigneStart = Integer.parseInt(coordinates[1]);
                    placeColStart = Integer.parseInt(coordinates[2]);
                    cpt++;
                }
                else if (cpt == 1){
                    placeLigneEnd = Integer.parseInt(coordinates[1]);
                    placeColEnd = Integer.parseInt(coordinates[2]);
                    if ((placeColStart<16 || placeLigneEnd<16 || placeColEnd<16 || placeSize<16) 
                    && (Math.abs((placeLigneStart-placeLigneEnd)+(placeColStart-placeColEnd))+1 == placeSize) 
                    && (placeLigneStart == placeLigneEnd || placeColStart==placeColEnd)){
                        pd.manualPutBoat(placeLigneStart, placeColStart, placeLigneEnd, placeColEnd, placeSize, this);
                        ib.deactivateButton(pd.getGrille(), 2);
                        ib.revealBoat(pd);
                        cpt++;
                        ib.decreaseButton(placeSize);
                    }
                    else{
                        ib.tourj.setText("Mauvaise taille");
                        ib.deactivateButton(pd.getGrille(), 2);

                        cpt = 0;

                    }
                }
            }
        
        else{
            int i = Integer.parseInt(coordinates[0]);
            int j = Integer.parseInt(coordinates[1]);
            int ii=new Random().nextInt(gameSize-1);
            int jj=new Random().nextInt(gameSize-1);
            tour=0;
        	ib.remplir(i, j,tour);
        	ib.remplir(ii, jj,1);


        	
            if(pg.gagnant()){
                System.out.println("cringe un peu non");
            	if (ib.affichegagnant(name)==1) 
            		ib.endGame();
            	else {
                    System.out.println("cringe un peu non2");

            		ib.dispose();
            		new BatailleNavale();
            	}	
            }



        }

    
    
    }
    public static void main(String[] args) {
        BatailleNavale bn;
        bn = new BatailleNavale();
    }
}



