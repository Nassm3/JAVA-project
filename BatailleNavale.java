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

        putBoatOnGame(p, difficulty, gameSize);

                    
                
            
        //System.out.println("Hi " + name + ", you're playing in " + gamemode + " on a " + gameSize + "*" + gameSize + " plateau");
        
        ib = new InterfaceBatailleNavalle(gameSize, this, 1, p, joueur);
        ib.revealBoat(pJoueur);

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
                System.out.println(bateau[i][j] + " " + bateau[ii][jj] + "");
        		return false;
        	}
            
        } 
        catch (Exception e) {
            System.err.println(e + ",  " + i+ ",  " + j+ ",  " + ii+ ",  " + jj);
            return false;
        }
        }

    public void putBoatOnGame(Plateau p, int difficulty, int gameSize){
        if (difficulty != 2){
            int size = 2;
            while (size<6) {
                if ((new Random().nextInt(2)) == 0){

                    

                    //System.out.println("oui" + checkIndexExistence(bateau, ii, jj, ii+size-1, jj));
                    if (new Random().nextInt(2) == 0){
                        int ii = new Random().nextInt(gameSize-size+1);
                        int jj = new Random().nextInt(gameSize-size+1);
                        p.putBoat(ii, jj, ii+size-1, jj, size, this);
                    }
                    
                    else{
                        int ii = new Random().nextInt(gameSize - size) + size-1;	
                        int jj = new Random().nextInt(gameSize - size) + size-1;
                        p.putBoat(ii, jj, ii-size+1, jj, size, this);
                    }
                    
                }
                else{
                    
                    if (new Random().nextInt(2) == 0){     
                        int ii = new Random().nextInt(gameSize-size+1);
                        int jj = new Random().nextInt(gameSize-size+1);               
                        p.putBoat(ii, jj, ii, jj+size-1, size, this);
                    }
                    else{
                        int ii = new Random().nextInt(gameSize - size) + size-1;
                        int jj = new Random().nextInt(gameSize - size) + size-1;
                        p.putBoat(ii, jj, ii, jj-size+1, size, this);
                    }

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
		int i = Integer.parseInt(coordinates[0]);
		int j = Integer.parseInt(coordinates[1]);
        ib.remplir(i, j);
    }
    
public void m(int l, int c){

}

    public static void main(String[] args) {
        BatailleNavale bn;
        bn = new BatailleNavale();
    }
}
