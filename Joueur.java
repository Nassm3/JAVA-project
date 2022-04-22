import javax.swing.JOptionPane;

public class Joueur {
    String pseudo;
    
    public Joueur() {
		pseudo =JOptionPane.showInputDialog(null,"Quel est le nom du joueur ? ","Pseudo du joueur",JOptionPane.QUESTION_MESSAGE);
    }
}
