import java.awt.event.*;


public class BatailleNavale implements ActionListener{
    Plateau p;
    Joueur[] joueurs;
    int tour;
    InterfaceBatailleNavalle ib ;
    public BatailleNavale(){
        p = new Plateau();
        joueurs = new Joueur[2];
        ib = new InterfaceBatailleNavalle(this);

        for (int i = 0; i < joueurs.length; i++){
            System.out.println("---joueur" + String.valueOf(i+1));
            joueurs[i] = new Joueur(false, i+1, im);
        }
        Random r = new Random();
        tour = r.nextInt(2);

    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}
