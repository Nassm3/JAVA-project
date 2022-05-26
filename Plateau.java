//import java.util.ArrayList;
import java.util.Random;

public class Plateau {
    private Case [][] grille;
	public Bateau[][] bateau;
	Bateau bt;
	int gameSize;
	boolean rejouer;

    public Plateau(int i){
    	this.gameSize = i;
        grille = new Case [i][i];
		bateau = new Bateau[i][i];
        init();
        rejouer = false;
    } 

    public void init(){
        for(int indiceLigne=0; indiceLigne<grille.length; indiceLigne++)
            for(int indiceCol=0; indiceCol<grille[indiceLigne].length; indiceCol++)
                grille[indiceLigne][indiceCol] = Case.EMPTY;
    }
    
	public boolean gagnant() {
		
        for(int indiceLigne=0; indiceLigne<grille.length; indiceLigne++)
            for(int indiceCol=0; indiceCol<grille[indiceLigne].length; indiceCol++)
            	if (grille[indiceLigne][indiceCol]==Case.BOAT)
            		return false;
		return true;
	}
	
	public String jouer(int li, int col) {

		if (grille[li][col] != Case.EMPTY){
			if(grille[li][col].affiche().equals("bateau")){
				Bateau boat = bateau[li][col];
				String boatName = bateau[li][col].getName();
				grille[li][col] = Case.HIT;
				rejouer = true;
				if(boat.isSunk(grille)){
					String[] coordinate = boat.getBoatCoordinate().split(";");
					for(int k=0;k<coordinate.length;k++){
						int y = Integer.parseInt(coordinate[k].split("-")[0]);
						int z = Integer.parseInt(coordinate[k].split("-")[1]);
						grille[y][z] = Case.SUNK;}
					return "touché-coulé" + "_" + boatName;
					
				}
				else{
					return "touché_" + boatName;
				}
			}
			return "manqué"+"_"+null;
		}
		else{
			rejouer = false;
			grille[li][col] = Case.MISSED;
			return "manqué" + "_" + null;}
	}
	
	

	public Boolean manualPutBoat(int i,int j,int a, int b ,int taille, BatailleNavale bn){
		bt = new Bateau(i, j, a, b, taille, 1);
			if (grille[i][j]==Case.EMPTY && grille[a][b]==Case.EMPTY) {
				String coordinate = bt.getBoatCoordinate();
				String[] position = coordinate.split(";");
				if (i<a || j<b){
					int c=0;
					while(c<position.length){
						int indiceL = Integer.parseInt(position[c].split("-")[0]);
						int indiceCol = Integer.parseInt(position[c].split("-")[1]);
						grille[indiceL][indiceCol] = Case.BOAT;
						bateau[indiceL][indiceCol] = bt;
						c++;
				}
			}
				else{
					int c=taille-1;
					while(c>=0){

						int indiceL = Integer.parseInt(position[c].split("-")[0]);
						int indiceCol = Integer.parseInt(position[c].split("-")[1]);
						grille[indiceL][indiceCol] = Case.BOAT;
						bateau[indiceL][indiceCol] = bt;
						c--;}
				}

				
			}
			else{
				return false;
			}

		return true;

	}


	public Bateau[][] putBoat(int i,int j,int a, int b ,int taille, BatailleNavale bn){ 

		if (Math.abs(a-i)+1 != taille && Math.abs(b-j)+1 != taille) {//|| Math.abs(j-b)+1!=taille)

			throw new java.lang.Error("ImproperLength");
			
		}
		else if(!bn.checkIndexExistence(bateau, i, j, a, b)){
			/*System.err.println("non" +bn.checkIndexExistence(bateau, i, j, a, b));
			throw new java.lang.Error("Improperplacement " + i +", "+j+", "+a+", "+b);*/
			int ii = new Random().nextInt(gameSize-taille+1);
			int jj = new Random().nextInt(gameSize-taille+1);
			putBoat(ii, jj, ii+taille-1, jj, taille, bn);	
		}

		else {
			bt = new Bateau(i, j, a, b, taille, 1);
			if (grille[i][j]==Case.EMPTY && grille[a][b]==Case.EMPTY) {
				
				String coordinate = bt.getBoatCoordinate();
				String[] position = coordinate.split(";");
				int c=0;
				while(c<position.length){
					int indiceL = Integer.parseInt(position[c].split("-")[0]);
					int indiceCol = Integer.parseInt(position[c].split("-")[1]);
					grille[indiceL][indiceCol] = Case.BOAT;
					bateau[indiceL][indiceCol] = bt;
					c++;
				}

				
			}	
			else {
				int ii = new Random().nextInt(gameSize-taille+1);
				int jj = new Random().nextInt(gameSize-taille+1);
				putBoat(ii, jj, ii+taille-1, jj, taille, bn);}	
		}
		return bateau;
	}
	public void afficher() {
		String marge=" ";
    	afficherligne(marge.length());
	    for (int i = 0; i < grille.length; i++) {
	    	for (int j=0;j<grille[i].length;j++){
	    		System.out.print("|"+marge+grille[i][j].affiche()+marge);
	    	}
	    System.out.println();
	    }
	}
	public void afficherligne(int marge) {
		for (int i=1;i<8*marge+7;i++) 
			System.out.print("-");
		System.out.println();
	}
			
	public Case[] [] getGrille(){
		return grille;
	}

	public Bateau[][] getBateau(){
		return bateau;
	}
	public static void main(String[] args) {
		

	
	}
}

