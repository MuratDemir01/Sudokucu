package yazlab2;

public class Threadci extends Thread{
    
	public static String bitirenThread;
	public String ad;
	public int say;
	public int[][] sudoku;
	public Sudokucu sudokucu;
	
	public static boolean Kesme = false;
	
	public long ilkZaman = 0; 
	public long sonZaman = 0; 
	public long zaman = 0; 
	
	
	public Threadci(String isim, int deger) {
		ilkZaman =  System.nanoTime();
		ad = isim;
		say = deger;
		sudokuAta();
		//System.out.println("\n " + deger+ "   "  + say);
		sudokucu = new Sudokucu(sudoku,this);
		
	}
	
	@Override
    public void run() {
		sudokucu.sudokuOynat();
		
		sonZaman =  System.nanoTime();
		zaman = sonZaman - ilkZaman;
		
		/*for (int i = 0; i < 500; i++) {
			System.out.println(ad + ": " + i + ". kere yazýyorum.");
		}*/	
    }
		
	public void sudokuAta(){
		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = OkuYaz.sudoku[i][j];
			}
		}
		
	}
	
	
}
