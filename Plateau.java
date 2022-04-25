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
	
	public String jouer(int joueur, int li, int col) {

		if(grille[li][col] != Case.EMPTY){
			grille[li][col] = Case.HIT;

			if(bateau[li][col].isSunk(li, col)){
			return "touché-coulé" +"-"+bateau[li][col].getName();
			}
			else{
				return "touché" +"-"+ bateau[li][col].getName();
			}
		} 
		grille[li][col] = Case.MISSED;
		return "manqué"+"-"+null;

		
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


				if (taille>2) {
					int c=1;

					if (j==b) {
						for (int x=0;x<Math.abs(a-i);x++) {
							grille[i+c][j]=Case.BOAT;
							bateau[i+c][j] = bt;
							c+=1;
						}
					}

					if (i==a) {
						for (int x=0;x<Math.abs(a-i);x++) {
							grille[i+c][j]=Case.BOAT;
							bateau[i+c][j] = bt;
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
	
	
    public boolean isSunk(Bateau b){
    	 
    	if (b.iLS==b.iLE) {
    		for (int i=b.iCS;i<=b.l;i++) {
    			if (grille[b.iLS][i]==Case.HIT)
    				return true;}
    	}
       	if (b.iCS==b.iCE) {
    		for (int i=b.iLS; i<= b.l ;i++) {
    			if (grille[b.iCS][i]==Case.HIT)
    				return true;}
       	}
    	return false;
       }
			
	
	
	public static void main(String[] args) {
		Plateau p=new Plateau(8);
		p.putBoat(1, 2, 4, 2, 4);
		p.afficher();
	}
}

