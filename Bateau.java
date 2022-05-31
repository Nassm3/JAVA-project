

public class Bateau{
    String name;
    int iLS, iCS, iLE, iCE, l;
	public Bateau(){
		
	}

    public Bateau(int indiceLigneStart, int indiceColStart,int indiceLigneEnd, int indiceColEnd, int length, int nb) {
        l = length;
		
        if (length == 5){
            name = "Porte avion ";
		}
        else if (length == 4){ 
            name = "Croiseur ";
        }
        else if (length == 3){ 
            name = "Contre-torpilleur ";
        }
        else{ 
            name = "Torpilleur";
        }
        this.iLS = indiceLigneStart;
        this.iCS = indiceColStart;
        this.iLE = indiceLigneEnd;
        this.iCE = indiceColEnd;
    }
	
    public String getName() {return name;}
    
    public boolean isSunk(Case [][] grille){   	
   	
    	String[] coordinate = getBoatCoordinate().split(";");
		int taille = 0;
		for (int i=0;i<coordinate.length;i++){
			if (coordinate[i].contains("-")){
				taille+=1;
			}
		}
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
   		if (l==2){
			liste=String.valueOf(iLS)+"-"+String.valueOf(iCS)+";"+String.valueOf(iLE)+"-"+String.valueOf(iCE);
   		}
   		else {

   			if (iLS==iLE) {
				liste += String.valueOf(iLS)+"-"+String.valueOf(iCS);
				if (iCE > iCS){
					for (int i=iCS+1;i<=l+iCS-1;i++) {
						liste += ";" + String.valueOf(iLS)+"-"+String.valueOf(i);
					}
				}
				else{
					for (int i=iCS-1;i>=iCE;i--) {
						liste += ";" + String.valueOf(iLS)+"-"+String.valueOf(i);
					}
				}
				
   			}
   	       	else{

				liste += String.valueOf(iLS)+"-"+String.valueOf(iCS);
				if (iLE > iLS){
					for (int i=iLS+1;i<=l+iLS-1;i++) {

						liste+= ";" + String.valueOf(i)+"-"+String.valueOf(iCS);
					}
				}
				else{
					for (int i=iLS-1;i>=iLE;i--) {

						liste += ";" + String.valueOf(i)+"-"+String.valueOf(iCS);
					}
				}
   	       		
   	       	}

   		}
   		return liste;
   	}
	   public Bateau getBateau(int i, int j) {
		//String [] coordinate = getBoatCoordinate().split(";");

		return this;
	   }
}

