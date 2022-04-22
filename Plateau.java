public class Plateau {
    private Case [][] grille;
    
    public Plateau(int i){
        grille = new Case [i][i];
        init();
    } 

    public void init(){
        for(int indiceLigne=0; indiceLigne<grille.length; indiceLigne++)
            for(int indiceCol=0; indiceCol<grille[indiceLigne].length; indiceCol++)
                grille[indiceLigne][indiceCol] = Case.EMPTY;
    }
    
}
