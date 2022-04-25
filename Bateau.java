import java.util.*;

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
            name = "Porte avion" + String.valueOf(nb);
        }
        else if (length == 4){ 
            name = "Croiseur" + String.valueOf(nb);
        }
        else if (length == 3){ 
            name = "Contre-torpilleur" + String.valueOf(nb);
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
    
    public boolean isSunk(){
    	getBoatCoordinate();
    	String indice1=
    	
   	 	
    	
    	
    	
    	if (iLS==iLE) {
    		int i=iCS;
    		while(i<=l) {
      			if (grille[iLS][i]!=Case.HIT || grille[iLS][i]!=Case.SUNK) {
      				return false;
      			}
    		}
    	}
    		
       	if (iCS==iCE) {
       		int i=iLS;
       	    while(i<=l) {
       	    	if (grille[iLS][i]!=Case.HIT || grille[iLS][i]!=Case.SUNK) {
       	    		return false;
       	      	}
       	    }
       }
       	return true;
    }
    
    public String getBoatCoordinate() {
		String liste=new String("");
   		if (l==2){
			liste=String.valueOf(iLS)+"-"+String.valueOf(iCS)+";"+String.valueOf(iLE)+"-"+String.valueOf(iCE);
   		}
   		else {
   			if (iLS==iLE) {
   	       		for (int i=iCS;i<=l;i++) {
   	       			liste=liste.concat(String.valueOf(iLS)+"-"+String.valueOf(i)+";");    	
   	       	    }
   			}
   	       	if (iCS==iCE) {
   	       		for (int i=iLS;i<=l;i++) {
   	       			liste=liste.concat(String.valueOf(i)+"-"+String.valueOf(iCS)+";");
   	       		}
   	       	}

   		}
   		return liste;
   	}
}

