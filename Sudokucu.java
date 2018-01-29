package yazlab2;

import java.util.LinkedList;
import java.util.Random;

public class Sudokucu {
	
	public static int[][] ilkSudoku;
	
	public int[][] sudoku = new int[9][9];
	
	public int sat=0,sut=0;
	
	public LinkedList<Hamle> hamleler;
	
	public Threadci efendi;
	
	public Sudokucu() {
		
	}
	public Sudokucu(int[][] sudoku) {
		Random random = new Random();
		hamleler = new LinkedList<Hamle>();	
		sudokuAta(sudoku);
		ilkSudoku = sudoku;
		sat = random.nextInt(9);
		sut = random.nextInt(9);
		System.out.println("sat: " + sat + " sut: " + sut);
		/*if(sudokuOyna()){
			System.out.println("Sudoku çözüldü!");
			sudokuYaz(this.sudoku);
		}else
			System.out.println("Sudokunun çözümü bulunmamaktadýr");
		
		tumHamleGor();*/
	}
	
	public Sudokucu(int[][] sudoku,Threadci sahip) {
		Random random = new Random();
		hamleler = new LinkedList<Hamle>();	
		sudokuAta(sudoku);
		ilkSudoku = sudoku;
		sat = random.nextInt(9);
		sut = random.nextInt(9);
		System.out.println("sat: " + sat + " sut: " + sut);
		this.efendi = sahip;
	}
	
	public void sudokuOynat(){
		if(sudokuOyna()){
			System.out.println("Sudoku çözüldü!");
			sudokuYaz(this.sudoku);
		}else
			System.out.println("Sudokunun çözümü bulunmamaktadýr");
		
		tumHamleGor();
	}
	
	public void sudokuAta(int[][] sudoku){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.sudoku[i][j] = sudoku[i][j];
			}
		}
		/*System.out.println("Sudoku ata bas");
		sudokuUzunYaz(this.sudoku);
		System.out.println("Sudoku ata son");*/
		
	}
	public boolean sudokuOyna(){
		
		if(Threadci.Kesme){
			return true;
		}
		
		//System.out.println("Sudoku Oyna!"+ " sat: " + sat +" sut: " +sut);
		
		if(hepsiDolu()){
			Threadci.Kesme = true;
			if(efendi != null)
				Threadci.bitirenThread = efendi.ad;
		  	return true;
		}
		for(int say = 1; say<=9;say++){
			//System.out.println("say: " + say+ " sat: " + sat +" sut: " +sut);
			// sudokuUzunYaz(sudoku);
			if(konulabilir(say)){
				sudoku[sat][sut] = say;
				hamleler.addLast(new Hamle(sat, sut, say));
				
				do {
					sutArttir();
				} while (sudoku[sat][sut] == ilkSudoku[sat][sut] && ilkSudoku[sat][sut] != 0);
				
				
				if(sudokuOyna()){		
					return true;
				}
				
				do {
					sutAzalt();
				} while (sudoku[sat][sut] == ilkSudoku[sat][sut] && ilkSudoku[sat][sut] != 0);
				
				hamleler.removeLast();
				sudoku[sat][sut] = 0;
			}
		  hepsiDolu();
		}	
		
		return false;
	}
	
	public boolean satDolu(int say){// satirda o sayi var mi?
		for(int i=0;i<9;i++){  //for(i 0->9)
	  	if(sudoku[i][sut]==say)
	    	return true;
	  }
		return false;
	}
	public boolean sutDolu(int say){// sutunda o sayi var mi?
		for(int i=0;i<9;i++){  //for(i 0->9)
	  	if(sudoku[sat][i]==say)
	    	return true;
	  }
		return false;
	}
	public boolean kutuDolu(int say){// kutuda o sayi var mi?
		int satAlan= sat - sat%3;
		int sutAlan= sut - sut%3;
	  for(int i=0;i<3;i++){  //for(i 0->3)
	  	for(int j= 0;j<3; j++){
	    	if(sudoku[satAlan+i][sutAlan+j]==say)
	    		return true;
	    }
	  }
		return false;
	}
	public boolean konulabilir(int say){//orada o sayi var mi?
		return !satDolu(say) && !sutDolu(say) && !kutuDolu(say);
	}
	public boolean hepsiDolu(){
	//sudoku[(sat+i)%9][(sut+j)%9] == 0
		for(int i=0; i<9; i++){
		  	for(int j=0; j<9;j++){
		    	if(sudoku[(sat+i)%9][(sut+j)%9] == 0){
		    		sat = (sat+i)%9;
		    		sut = (sut+j)%9;
		    		//sudokuUzunYaz(sudoku);
		    		//System.out.println("Sudoku bitmedi!");
		    		//printef("Sudoku bitmedi")
		    		return false;
		      }
		  	}
		}
		return true;
	  //printef(sudoku tamamalandý);
	 // kesme at;
	}
	public void satArttir(){
		sat++;
		if(sat == 9)
			sat = 0;
	}
	public void sutArttir(){
		sut++;
		if(sut == 9){
		  	satArttir();
		  	sut = 0;
		}
	}
	public void satAzalt(){
		sat--;
		if(sat == -1){
		  	sat = 8;
		}
	}
	public void sutAzalt(){
		sut--;
		if(sut == -1){
			satAzalt();
		  	sut = 8;
		}
	}
	
	public void tumHamleGor(){
		for (int i = 0; i < hamleler.size(); i++) {
			System.out.println(hamleler.get(i).hamleGetir());
		}
	}

	/*public static int[][]sudokuOyna(int[][] sudoku,int thread){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//System.out.println("Thread:" + thread + "i,j"+i+","+j+ ": " + sudoku[i][j]);
				if(sudoku[i][j] == 0){
					sudoku[i][j] = thread;	
				}
			}
		}		
		Random rand = new Random();
		sudoku[rand.nextInt(9)][rand.nextInt(9)] = thread;	
		System.out.println("Thread1Sudokucu");
		sudokuYaz(sudoku);
		return sudoku;
	}*/
	
	public static void sudokuUzunYaz(int[][] sudoku){
		System.out.println("--Sudoku--");
	    for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
			}
			System.out.println("|");
		}
		System.out.println("----------");
	}
	public static void sudokuYaz(int[][] sudoku){
		System.out.println("--Sudoku--");
	    for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(sudoku[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print(sudoku[i][j]);
			}
			System.out.println("|");
		}
		System.out.println("----------");
	}
}
