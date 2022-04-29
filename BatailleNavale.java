import java.awt.event.*;
import java.util.Random;



public class BatailleNavale implements ActionListener{
    Plateau p;
    Plateau pJoueur;
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
        p = new Plateau(gameSize);
        this.bateau = p.getBateau();

        pJoueur = new Plateau(gameSize);
        int placementOption = cp.getPlacementOption();
        if (placementOption == 0){
            putBoatOnGame(pJoueur, 0, gameSize);

        }
        else {

        }
        putBoatOnGame(p, difficulty, gameSize);

                    
                
            
        //System.out.println("Hi " + name + ", you're playing in " + gamemode + " on a " + gameSize + "*" + gameSize + " plateau");
        
        ib = new InterfaceBatailleNavalle(gameSize, this, placementOption, p, joueur);
        ib.revealBoat(pJoueur);
        if (p.gagnant()) {
        	System.out.println("GAGNANT AZEFAZEPZKNEFPOJAZNRG");
        	ib.affichegagnant(name);
        }
        	

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

    public void fillButtonOnSunk(int i, int j){
        ib.fillOnSunk(i, j);
    }
    
    public void actionPerformed(ActionEvent e) { 
        String[] coordinates = e.getActionCommand().split("-");
            if (coordinates[1].equals("b")){
                cpt = 0;
                ib.activateButton(pJoueur.getGrille());
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
                        pJoueur.manualPutBoat(placeLigneStart, placeColStart, placeLigneEnd, placeColEnd, placeSize, this);
                        ib.deactivateButton(pJoueur.getGrille());
                        ib.revealBoat(pJoueur);
                        cpt++;
                        ib.decreaseButton(placeSize);
                    }
                    else{
                        ib.tour.setText("Taille pas bonne kek, cringe même, c'est Squid Game nul a chier si je puis me permettre");
                        ib.deactivateButton(pJoueur.getGrille());

                        cpt = 0;

                    }
                }

                
            
                

            }
            
        else{
            int i = Integer.parseInt(coordinates[0]);
            int j = Integer.parseInt(coordinates[1]);
            ib.remplir(i, j);
            if(p.gagnant()){
                ib.affichegagnant(name);

            }


        }
    }
    

    public static void main(String[] args) {
        BatailleNavale bn;
        bn = new BatailleNavale();
    }
}
