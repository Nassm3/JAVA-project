public class Plateau {
    private Case [][] grille;
	public Bateau[][] bateau;
    
    public Plateau(int i){
        grille = new Case [i][i];
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
	
	public boolean remplir(int joueur,int li,int col) {
		if(grille[li][col] != Case.EMPTY) 
			return false;
		grille[li][col]=Case.values()[joueur+1];
		return true;
	}
	
	public void putBoat(int i,int j,int a, int b ,int taille) { 
		if (Math.abs(a-i)+1!=taille ) //|| Math.abs(j-b)+1!=taille)
			System.out.println("saisir taille cohérente");
		else {
			if (grille[i][j]==Case.EMPTY && grille[a][b]==Case.EMPTY) {
				grille[i][j]=Case.BOAT;
				grille[a][b]=Case.BOAT;
				Bateau bt = new Bateau(i, j, a, b, taille, 1);
				bateau[i][j] = bt;
				bateau[a][b] = bt;
				if (taille>3) {
					int c=1;
					if (j==b) {
						for (int x=0;x<Math.abs(a-i);x++) {
							grille[i+c][j]=Case.BOAT;
							bateau[i+c][j] = bt;
							c+=1;
						}
					}
					if (i==a) {
						for (int x=0;x<Math.abs(j-b);x++) {
							grille[i][j+c]=Case.BOAT;
							bateau[i][j+c] = bt;
							c+=1;
						}
					}
				}
			}		
		}
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
			
	
	
	public static void main(String[] args) {
		Plateau p=new Plateau(8);
		p.putBoat(1, 2, 4, 2, 4);
		p.afficher();
	}
}

