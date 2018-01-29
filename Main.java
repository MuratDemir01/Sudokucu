package yazlab2;

//import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		//Scanner girdi = new Scanner(System.in);
		OkuYaz okuyaz = new OkuYaz();
		//System.out.println(okuyaz.dosyaKonumu);
		//okuyaz.sudokuYaz();		
		Sudokucu.sudokuUzunYaz(OkuYaz.sudoku);
		GUI gui = new GUI();
				
	}

}
