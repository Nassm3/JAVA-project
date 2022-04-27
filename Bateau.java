

public class Bateau{
    String name;
    int l;
    int iLS;
    int iCS;
    int iLE;
    int iCE;
    public Bateau(int indiceLigneStart, int indiceColStart,int indiceLigneEnd, int indiceColEnd, int length, int nb) {
        this.l = length;
        if (length == 5){
            name = "Porte avion " + String.valueOf(nb);
		}
        else if (length == 4){ 
            name = "Croiseur " + String.valueOf(nb);
        }
        else if (length == 3){ 
            name = "Contre-torpilleur " + String.valueOf(nb);
        }
        else{ 
            name = "Torpilleur" + String.valueOf(nb);
        }
        this.iLS = indiceLigneStart;
        this.iCS = indiceColStart;
        this.iLE = indiceLigneEnd;
        this.iCE = indiceColEnd;
    }
    public String getName() {return name;}
    
    public boolean isSunk(Case [][] grille){   	
   	
    	String[] coordinate=getBoatCoordinate().split(";");
		int taille = 0;
		System.out.println("coo :" + getBoatCoordinate());
		for (int i=0;i<coordinate.length;i++){
			if (coordinate[i].contains("-")){
				taille+=1;
			}
		}
		System.out.println("taille : " + taille);
		for (int i=0; i<taille;i++){
			int li=Integer.parseInt(coordinate[i].split("-")[0]);
			int col=Integer.parseInt(coordinate[i].split("-")[1]);
			if (grille[li][col] != Case.HIT){
				return false;
			}
		}
		return true;
    }
    
    
	public boolean isHit(Case [][] grille) {
    	String [] coordinate=getBoatCoordinate().split(";");
		int taille=0;
		for (int i=0;i<coordinate.length;i++){
			System.out.print("coordinate" + coordinate[i]);
			if (coordinate[i].contains("-")){
				taille+=1;
			}
		}
		for (int i=0; i<taille;i++){
			int li=Integer.parseInt(coordinate[i].split("-")[0]);
			int col=Integer.parseInt(coordinate[i].split("-")[1]);
			if (grille[li][col]==Case.HIT){
				return true;
			}
		}
		return true;
	}
    
    public String getBoatCoordinate() {
		String liste = new String("");
		System.out.println("taille in getBoatCoordinate : " + l);
   		if (l==2){
			liste=String.valueOf(iLS)+"-"+String.valueOf(iCS)+";"+String.valueOf(iLE)+"-"+String.valueOf(iCE);
   		}
   		else {
			System.out.println("iLS : " + iLS + " iLE : " + iLE + " iCS : " + iCS + " iCE : " + iCE);
   			if (iLS==iLE) {
   	       		for (int i=iCS;i<=l+iCS-1;i++) {
   	       			liste += String.valueOf(iLS)+"-"+String.valueOf(i)+";";
					System.out.println("entered iLS==iLE :" + liste);
   	       	    }
   			}
   	       	if (iCS==iCE) {
   	       		for (int i=iLS;i<=l+iLS-1;i++) {
   	       			liste+= String.valueOf(i)+"-"+String.valueOf(iCS)+";";
					System.out.println("entered iCS==iCE :" + liste);
   	       		}
   	       	}

   		}
		System.out.println("liste : "+ liste);
   		return liste;
   	}
}

