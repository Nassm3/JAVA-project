import java.awt.event.*;
import java.util.Random;




public class BatailleNavale implements ActionListener{

    int placeLigneStart, placeLigneEnd, placeColStart, placeColEnd, placeSize, ii, jj, gameSize, tour, cpt, difficulty, botWay;
    Case[][] grillepg, grillepd;
    InterfaceBatailleNavalle ib;
    String gamemode, name;
    Bateau[][] bateau; 
    Plateau pg, pd;
    Joueur joueur;

    public BatailleNavale(){
        ii=0;
        jj=0;
        ChoosePanel cp = new ChoosePanel();
        cp.choosePanel();
        
        name = cp.getName();
        difficulty = cp.getDifficulty();

        if (difficulty == 0){
            gamemode = new String("Easy");
            joueur = new Joueur(name);
        }

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
    public boolean checkIfBotPlayable(int i, int j){
        if (grillepd[i][j].affiche().equals("vide") | grillepd[i][j].affiche().equals("bateau")){
            return true;
        }
        return false;
    }	
    public void botJouer(int gameSize, int difficulty){
        int[] ij = new int[2];
        if (difficulty == 0){

            ij[0]=new Random().nextInt(gameSize);
            ij[1]=new Random().nextInt(gameSize);

            if (grillepd[ij[0]][ij[1]].affiche().equals("vide") | grillepd[ij[0]][ij[1]].affiche().equals("bateau")){
                ib.remplir(ij[0], ij[1], 1);
            }
            else{
                botJouer(gameSize, difficulty);
            }
        }
        else {
            if (ib.botMemory > 0){
                if(ib.botHitCpt<2){

                    if (new Random().nextInt(2)==0){
                        

                        if (new Random().nextInt(2) == 0 & ib.lastBotHit[0] != gameSize){
                            ib.remplir(ib.lastBotHit[0] + 1, ib.lastBotHit[1], 1);
                        }
                        else if (ib.lastBotHit[1] != gameSize) {
                        ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + 1, 1);
                        }
                        else{
                            ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] - 1, 1);
                        }
                    }
                    else{
                        if (new Random().nextInt(1)==0 & ib.lastBotHit[0] != 0){
                            ib.remplir(ib.lastBotHit[0] - 1, ib.lastBotHit[1], 1);
                        }
                        else if (ib.lastBotHit[1] != 0) {
                        ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] - 1, 1);
                        }
                        else{
                            ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + 1, 1);
                        }
                    }
                }
                else if(ib.botHitCpt == 2){   
                    System.out.println("== 2");
                    System.out.println("on essaye"+ ib.lastBotHit[0] + " " + ib.lastBotHit[1] + " " + ib.botHit[0] + " " + ib.botHit[1]);

                        if (ib.botHit[0] - ib.lastBotHit[0] != 0){
                            if (ib.botHit[0] - ib.lastBotHit[0] < 0 ){
                                botWay = 1;
                                if(checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                    ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                                }
                                else{
                                    System.out.println(1 + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                                }
                            }
                            else{
                                botWay = -1;
                                if(checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                    ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                                }
                                else{
                                    System.out.println(2+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                                }
                            }
                        }   
                        else{
                            if (ib.botHit[1] - ib.lastBotHit[1] < 0 ){
                                botWay = 1;
                                if(checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] + botWay)){
                                    ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                                }
                                else{
                                    System.out.println(3 +"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                                }
                            }
                            else{
                                botWay = -1;
                                if(checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] + botWay)){
                                    ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                                    System.out.println(ib.lastBotHit[0] + " " + ib.lastBotHit[1] + botWay);

                                }
                                else{
                                    System.out.println(4 + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                                }
                            }
                        }
  
                    } 
                else{
                    if (ib.botHit[0] - ib.lastBotHit[0] != 0){
                        if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                            ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                        }
                        else{
                            botJouer(gameSize, 0);
                            System.out.println("cringe moment");
                        }
                    }
                    else{
                        if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                            ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                        }
                        else{
                            botJouer(gameSize, 0);
                            System.out.println("cringe moment");

                        }
                    }
            }
        }
            else{
                botJouer(gameSize, 0);
            }

        }
        if (pd.rejouer){
            System.out.println("cringe moment de fin");
            botJouer(gameSize, difficulty);
        }
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
                    && (placeLigneStart == placeLigneEnd || placeColStart==placeColEnd)) {

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
        	
            ib.remplir(i, j,0);
            if (!pg.rejouer) {
                botJouer(gameSize, difficulty);
                if (pd.rejouer) {
                	botJouer(gameSize, difficulty);
                }
            }
        	
            if(pg.gagnant()){
            	if (ib.affichegagnant(name)==1) 
            		ib.endGame();

            	else {
                    System.out.println("crnge un peu non2");
                }
            }
        }
    
    
    }
    public static void main(String[] args) {
        new BatailleNavale();
    }
}