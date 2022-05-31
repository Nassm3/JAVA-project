import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;




public class BatailleNavale implements ActionListener{

    int placeLigneStart, placeLigneEnd, placeColStart, placeColEnd, placeSize, ii, jj, gameSize, tour, cpt, difficulty, botWay;
    Case[][] grillepg, grillepd;
    InterfaceBatailleNavalle ib;
    String gamemode, name;
    Bateau[][] bateau;
    Plateau pg, pd;
    Joueur joueur;
    int debugCpt = 0;

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

        
        gameSize = cp.getGameSize();
        pg = new Plateau(gameSize);
        pd = new Plateau(gameSize);
        this.grillepg = pg.getGrille();
        this.grillepd = pd.getGrille();

        this.bateau = pg.getBateau();

        
        int placementOption = cp.getPlacementOption();
        ib = new InterfaceBatailleNavalle(gameSize, this, placementOption, pg, pd, joueur);
        if (placementOption == 0){
            //choix de placement automatique
            putBoatOnGame(pd, 0, gameSize, 2, true);
            ib.activateButton(grillepg, 1);
        }

        putBoatOnGame(pg, difficulty, gameSize, 2, true);
                  
        ib.revealBoat(pd);
    }

    public boolean checkIndexExistence(Bateau[][] bateau, int i, int j, int ii, int jj){
        /*teste l'existence de l'index pour le placement d'un bateau, prend les indices des extremitées du bateau
        et le tableau de bateau*/
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



    public void putBoatOnGame(Plateau p, int difficulty, int gameSize, int size, boolean scan){
        /*ajoute les bateau sur le plateau a une position aléatoire de manière à ne pas être superposé ni adjacent*/
        if (!scan){
            int ii = new Random().nextInt(gameSize-size+1);
            int jj = new Random().nextInt(gameSize-size+1);
            if ((new Random().nextInt(2)) == 0){
                
                if(checkIfBoatCanBePlaced(ii, jj, ii+size-1, jj, p, size)){
                    p.putBoat(ii, jj, ii+size-1, jj, size, this);
                }
                else{
                    putBoatOnGame(p, difficulty, gameSize, size, false);
                }
            }
            else{
                if(checkIfBoatCanBePlaced(ii, jj, ii, jj+size-1, p, size)){
                    p.putBoat(ii, jj, ii, jj+size-1, size, this);
                }
                else{
                    putBoatOnGame(p, difficulty, gameSize, size, false);
                }            
            }

        }
        else{
            while (size<6) {
                int ii = new Random().nextInt(gameSize-size+1);
                int jj = new Random().nextInt(gameSize-size+1);
                if ((new Random().nextInt(2)) == 0){
                    
                    if(checkIfBoatCanBePlaced(ii, jj, ii+size-1, jj, p, size)){
                        p.putBoat(ii, jj, ii+size-1, jj, size, this);
                    }
                    else{
                        putBoatOnGame(p, difficulty, gameSize, size, false);
                    }
                }
                else{
                    if(checkIfBoatCanBePlaced(ii, jj, ii, jj+size-1, p, size)){
                        p.putBoat(ii, jj, ii, jj+size-1, size, this);
                    }
                    else{
                        putBoatOnGame(p, difficulty, gameSize, size, false);
                    }            
                }
                    size++;

            }    
        }
    }
                
         

    public boolean checkOrientation(ArrayList<int[]> allBotHits){
        /*retourne vrai si le bateau est horizontal*/
        if(allBotHits.get(allBotHits.size()-2)[0] - allBotHits.get(allBotHits.size()-1)[0] == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void jouerInverser(){
        /*prends le premier hit du bot et inverse le sens de frappe*/
        if (!checkOrientation(ib.allBotHits)){
            if(checkIfBotPlayable(ib.allBotHits.get(0)[0] + botWay * -1, ib.allBotHits.get(0)[1])){
                ib.remplir(ib.allBotHits.get(0)[0] + botWay * -1, ib.allBotHits.get(0)[1], 1);
            }

        }
        else{
            if(checkIfBotPlayable(ib.allBotHits.get(0)[0] , ib.allBotHits.get(0)[1] + (botWay * -1))){
                ib.remplir(ib.allBotHits.get(0)[0] , ib.allBotHits.get(0)[1]+ botWay * -1, 1);
            }

        }
    }
    
    public boolean checkIfBotPlayable(int i, int j){
        /*return true si la case peut être selectionnée*/
        if(i < gameSize & i >= 0 & j < gameSize & j >= 0){
            if (grillepd[i][j].affiche().equals("vide") | grillepd[i][j].affiche().equals("bateau")){
                return true;
            }
        }
        return false;

    }	

    public void botJouer(int gameSize, int difficulty){

        /*action de jeu du bot, varie en fonction de la difficulté, le bot facile joue aléatoirement, le bot moyen
        joue aléatoirement jusqu'à trouver un bateau, auquel cas il étend jusqu'à couler le bateau*/
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

            if (ib.botMemory == 1){
                if(ib.botHitCpt == 1){
                    if (new Random().nextInt(2)==0){
                        

                        if (new Random().nextInt(2) == 0 & checkIfBotPlayable(ib.lastBotHit[0] + 1, ib.lastBotHit[1])){
                            ib.remplir(ib.lastBotHit[0] + 1, ib.lastBotHit[1], 1);
                        }
                        else if (checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] + 1)) {
                        ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + 1, 1);
                        }
                        else{
                            botJouer(gameSize, difficulty);
                        }
                    }
                    else{
                        if (new Random().nextInt(1)==0 & checkIfBotPlayable(ib.lastBotHit[0] - 1, ib.lastBotHit[1])){
                            ib.remplir(ib.lastBotHit[0] - 1, ib.lastBotHit[1], 1);
                        }
                        else if (checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] - 1)) {
                        ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] - 1, 1);
                        }
                        else{
                            botJouer(gameSize, difficulty);
                        }
                    }
                }
                else if(ib.botHitCpt == 2){   

                        if (!checkOrientation(ib.allBotHits)){
                            if (ib.allBotHits.get(ib.allBotHits.size()-2)[0] - ib.lastBotHit[0] < 0 ){
                                botWay = 1;
                            }
                            else{
                                botWay = -1; 
                            }
                            if(checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                            }
                            else{
                                jouerInverser();
                                //ib.botMemory = 2;


                            }
                        }   
                        else{
                            if (ib.allBotHits.get(ib.allBotHits.size()-2)[1] - ib.lastBotHit[1] < 0 ){
                                botWay = 1;
                            }
                            else{
                                botWay = -1;
                                
                            }
                            if(checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] + botWay)){
                                ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                            }
                            else{
                                jouerInverser();
                                //ib.botMemory = 2;
                            }
                        }
  
                    }

                else{

                    if (!checkOrientation(ib.allBotHits)){
                        if(checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                            ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                        }
                        else{
                            jouerInverser();
                            //botJouer(gameSize, 0);
                            //ib.botMemory = 2;
                        }
                    }
                    else{
                        if(checkIfBotPlayable(ib.lastBotHit[0] , ib.lastBotHit[1]+ botWay)){
                            ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                        }
                        else{
                            jouerInverser();
                            //botJouer(gameSize, 0);

                            }
                        }
                    }
            }
            else if(ib.botMemory == 2){
                jouerInverser();
                botWay = botWay * -1;
            }
            else{
                botJouer(gameSize, 0);
            }

        }
        if (pd.rejouer & debugCpt < 10){
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            debugCpt++;
            botJouer(gameSize, difficulty);
        }
        
    }

    public boolean checkIfBoatCanBePlaced(int i, int j, int ii, int jj, Plateau p, int taille){
        /*verifie si le bateau peut être placé au coordonnée fournies, 
        en verifiant si il rentre au contact d'autres bateau*/
        Case[][] grille = p.getGrille();
        Bateau bt = new Bateau(i, j, ii, jj, taille, 1);
        String coordinate = bt.getBoatCoordinate();
		String[] position = coordinate.split(";");
        int c = 0;

        while(c<position.length){
            int indiceL = Integer.parseInt(position[c].split("-")[0]);
            int indiceCol = Integer.parseInt(position[c].split("-")[1]);
            if(grille[indiceL][indiceCol] == Case.BOAT){
                return false;
            }
            if((indiceL-1)>-1){
                if(grille[indiceL-1][indiceCol] == Case.BOAT){
                    return false;
                }
            }
            if((indiceL+1)< gameSize){
                if(grille[indiceL+1][indiceCol] == Case.BOAT){
                    return false;
                }
            }
            if((indiceCol-1)>-1){
                if(grille[indiceL][indiceCol-1] == Case.BOAT){
                    return false;
                }
            }
            if((indiceCol + 1)< gameSize){
                if(grille[indiceL][indiceCol + 1] == Case.BOAT){
                    return false;
                }
            }
            c++;
        }
		return true;
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

                if ((placeColStart<gameSize || placeLigneEnd<gameSize || placeColEnd<gameSize || placeSize<gameSize) 
                && (Math.abs((placeLigneStart-placeLigneEnd)+(placeColStart-placeColEnd))+1 == placeSize) 
                && (placeLigneStart == placeLigneEnd || placeColStart==placeColEnd) && checkIfBoatCanBePlaced(placeLigneStart, placeColStart, placeLigneEnd, placeColEnd, pd, placeSize)){

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
            debugCpt = 0;
            if (!pg.rejouer) {
                botJouer(gameSize, difficulty);
                if(pd.rejouer) {
                	botJouer(gameSize, difficulty);
                }
            }

            if(pg.gagnant()){

            	if (ib.affichegagnant(name)==1) 
            		ib.endGame();

            	else {
                    ib.dispose();
                    new BatailleNavale();
                }
            }
            else if(pd.gagnant()){
            	if (ib.affichegagnant("Le bot ") == 1) 
            		ib.endGame();
            	else {
                    ib.dispose();
                    new BatailleNavale();
                }
            }
        }
    }

    public static void main(String[] args){
        new BatailleNavale();
    }
}