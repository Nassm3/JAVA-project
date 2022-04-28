import java.util.Random;

public class Plateau {
    private Case [][] grille;
	public Bateau[][] bateau;
	Bateau bt;
	int gameSize;

    
    public Plateau(int i){
    	this.gameSize = i;
        grille = new Case [i][i];
		bateau = new Bateau[i][i];
        init();
    } 

    public void init(){
        for(int indiceLigne=0; indiceLigne<grille.length; indiceLigne++)
            for(int indiceCol=0; indiceCol<grille[indiceLigne].length; indiceCol++)
                grille[indiceLigne][indiceCol] = Case.EMPTY;
    }
    
	public boolean gagnant(int i,int j) {
		
        for(int indiceLigne=0; indiceLigne<grille.length; indiceLigne++)
            for(int indiceCol=0; indiceCol<grille[indiceLigne].length; indiceCol++)
            	if (grille[indiceLigne][indiceCol]==Case.SUNK || grille[indiceLigne][indiceCol]==Case.EMPTY)
            		return true;
		return false;
	}
	
	public String jouer(int li, int col) {
		System.out.println("valeur grille :" + grille[li][col].affiche());

		if (grille[li][col] != Case.EMPTY){

			if(grille[li][col].affiche().equals("bateau")){	
				Bateau boat = bateau[li][col];

				String boatName = bateau[li][col].getName();
				System.out.println(boat.getBoatCoordinate());
				grille[li][col] = Case.HIT;
				if(boat.isSunk(grille)){
					return "touché-coulé" + "_" + boatName;
					
				}
				else{
					return "touché_" + boatName;
				}
			}
			return "manqué"+"_"+null;
		}
		else{
			grille[li][col] = Case.MISSED;
			return "manqué"+"_"+null;}
	}
	

	
	public Bateau[][] putBoat(int i,int j,int a, int b ,int taille, BatailleNavale bn){ 
		
		bt = new Bateau(i, j, a, b, taille, 1);


		if (Math.abs(a-i)+1 != taille && Math.abs(b-j)+1 != taille) {//|| Math.abs(j-b)+1!=taille)

			throw new java.lang.Error("ImproperLength");
			
		}
		else if(!bn.checkIndexExistence(bateau, i, j, a, b)){
			System.err.println("non" +bn.checkIndexExistence(bateau, i, j, a, b));
			throw new java.lang.Error("Improperplacement " + i +", "+j+", "+a+", "+b);
		}

		else {
			if (grille[i][j]==Case.EMPTY && grille[a][b]==Case.EMPTY) {

				grille[i][j]=Case.BOAT;
				grille[a][b]=Case.BOAT;

				bateau[a][b] = bt;
				bateau[i][j] = bt;

				if (taille>2) {
					int c=1;
					if (j==b) {
						while(c<Math.abs(a-i)) {
							grille[i+c][j]=Case.BOAT;
							bateau[i+c][j] = bt;
							c+=1;
						}
					}

					else if (i==a) {
						while(c<Math.abs(b-j)) {
							grille[i][j+c] = Case.BOAT;
							bateau[i][j+c] = bt;
							c+=1;
						}
					}
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
	    	System.out.print(marge+String.valueOf(i+1)+marge);
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
	
	public int getSize(){
		return size;
	}
		
	public static void main(String[] args) {
		/*Plateau p=new Plateau(8);
		p.putBoat(2, 3, 4, 3, 3);
		Bateau bo=new Bateau(2,3,4,3,3,5);
		p.jouer(0,0);
		//p.jouer(1,3);
		p.jouer(2,3);
		//p.jouer(4,3);



		System.out.println(bo.isSunk(p.grille));
		p.afficher();*/


	}
}

