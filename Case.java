 
public enum Case {
	SUNK("X"),
	EMPTY(" "),
	BOAT("B"),
	HIT("H");
	
	private String affichage;
	private Case(String aff) {
		affichage=aff;
	}
	public String affiche() {return affichage;}

}