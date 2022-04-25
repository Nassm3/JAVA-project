import java.awt.event.*;
import java.util.Random;


public class BatailleNavale implements ActionListener{
    Plateau p;
    Joueur[] joueurs;
    int tour;
    String gamemode;
    public BatailleNavale(){
        
        joueurs = new Joueur[2];
        ChoosePanel cp = new ChoosePanel();
        cp.choosePanel();
        String name = cp.getName();

        int difficulty = cp.getDifficulty();
        if (difficulty == 0){
            gamemode = new String("Easy");}
        else{
            gamemode = new String("Normal");
        }
        int gameSize = cp.getGameSize();
        int placementOption = cp.getPlacementOption();
        p = new Plateau(gameSize);
        //System.out.println("Hi " + name + ", you're playing in " + gamemode + " on a " + gameSize + "*" + gameSize + " plateau");
        
        InterfaceBatailleNavalle ib = new InterfaceBatailleNavalle(gameSize, this);

        for (int i = 0; i < joueurs.length; i++){
            //joueurs[i] = new Joueur(false, i+1, im);
        }
        Random r = new Random();
        tour = r.nextInt(2);

    }
    
    public void actionPerformed(ActionEvent e) { 
    }
    public static void main(String[] args) {
        BatailleNavale bn = new BatailleNavale();
    }
}
