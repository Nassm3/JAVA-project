import java.awt.event.*;
import java.util.Random;



public class BatailleNavale implements ActionListener{
    Plateau p;
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
        int gameSize = cp.getGameSize();
        int placementOption = cp.getPlacementOption();
        
        p = new Plateau(gameSize);
        if (difficulty != 2){
            int size = 2;
            while (size<6) {
                int ii = new Random().nextInt(14)+1;
                int jj = new Random().nextInt(14)+1;
                p.putBoat(ii, jj, ii+size-1, jj, size);
                System.out.println(size);
                size++;
                
                
            }
        }
        //System.out.println("Hi " + name + ", you're playing in " + gamemode + " on a " + gameSize + "*" + gameSize + " plateau");
        
        ib = new InterfaceBatailleNavalle(gameSize, this, 1, p, joueur);
        

    }
    
    public void actionPerformed(ActionEvent e) { 
        String[] coordinates = e.getActionCommand().split("-");
		int i = Integer.parseInt(coordinates[0]);
		int j = Integer.parseInt(coordinates[1]);
        ib.remplir(i, j);
    }
    public static void main(String[] args) {
        BatailleNavale bn = new BatailleNavale();
    }
}
