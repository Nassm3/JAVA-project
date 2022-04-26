public class Plateau {
    private Case [][] grille;
	public Bateau[][] bateau;
	Bateau bt;

    
    public Plateau(int i){
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

		if (grille[li][col] != Case.EMPTY){
			//System.out.println(grille[li][col].affiche());
			if(grille[li][col].affiche().equals("bateau")){
				System.out.println("fuck me");
				
				//System.out.println(bateau[li][col].getName());
				grille[li][col] = Case.HIT;
				if(bateau[li][col].isSunk(grille)){
					return "touché-coulé" + "_" + bateau[li][col].getName();
				}
				else{
					return "touché" +"_"+ bateau[li][col].getName();
				}
				
			}
			
		}
		else{
			System.out.println("fuck me, the sequel");
			grille[li][col] = Case.MISSED;
			return "manqué"+"_"+null;}
		return " "+"_"+" ";
		
		 		
	}
	
	public boolean isTouched(int i,int j) {
		if(grille[i][j] == Case.HIT){
			return true;
		}
		return false;
	}
	
	public void putBoat(int i,int j,int a, int b ,int taille) { 
		
		bt = new Bateau(i, j, a, b, taille, 1);


		if (Math.abs(a-i)+1!=taille && Math.abs(b-j)+1!=taille) {//|| Math.abs(j-b)+1!=taille)
			System.out.println("saisir taille cohérente");}

		else {
			if (grille[i][j]==Case.EMPTY && grille[a][b]==Case.EMPTY) {

				grille[i][j]=Case.BOAT;
				grille[a][b]=Case.BOAT;

				bateau[a][b] = bt;
				bateau[i][j] = bt;

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
			
	public Case[] [] getGrille(){
		return grille;
	}
		
	public static void main(String[] args) {
		Plateau p=new Plateau(8);
		p.putBoat(2, 3, 4, 3, 3);
		Bateau bo=new Bateau(2,3,4,3,3,5);
		p.jouer(0,0);
		p.jouer(1,3);
		p.jouer(2,3);
		p.jouer(4,3);



		System.out.println(bo.isSunk(p.grille));
		p.afficher();


	}
}

