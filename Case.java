 
public enum Case {
	
	SUNK("coulé"),
	EMPTY("vide"),
	MISSED("manqué"),
	BOAT("bateau"),
	HIT("touché");
	
	private String affichage;
	private Case(String aff) {
		affichage=aff;
	}

	public String affiche() {return affichage;}

}