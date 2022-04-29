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
    public BatailleNavale(){
        
        ChoosePanel cp = new ChoosePanel();
        cp.choosePanel();
        
        String name = cp.getName();
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
            putBoatOnGame(p, difficulty, gameSize);
            putBoatOnGame(pJoueur, difficulty, gameSize);
        }
        else {
            
        }


                    
                
            
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
            if (coordinates[0].equals("a")){
                int i = Integer.parseInt(coordinates[1]);
                int j = Integer.parseInt(coordinates[2]);
                System.out.println(coordinates[0] +"" + i+" "+j);
            }
        else{
            

            int i = Integer.parseInt(coordinates[0]);
            int j = Integer.parseInt(coordinates[1]);
            ib.remplir(i, j);
            if(p.gagnant()){
                System.out.println("no cap");
            }


        }
    }
    

    public static void main(String[] args) {
        BatailleNavale bn;
        bn = new BatailleNavale();
    }
}
