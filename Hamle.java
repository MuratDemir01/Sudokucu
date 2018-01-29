package yazlab2;

public class Hamle {
	
	public int sat;
	public int sut;
	public int say;
	
	
	public Hamle() {
		
	}
	public Hamle(int sat,int sut, int say) {
		this.sat = sat;
		this.sut = sut;
		this.say=say;
	}	
	
	public String hamleGetir(){
		return "sat: " + sat + " sut: " + sut + " say: " + say;
	}
	
}
