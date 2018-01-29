package yazlab2;

import java.io.FileReader;

import javax.swing.JFileChooser;

public class OkuYaz {
	
	public static String dosyaKonumu;
	public static int[][] sudoku;
	
	public OkuYaz() {
		JFileChooser dosyaOku = new JFileChooser(); 
		dosyaOku.showOpenDialog(null);
		try {
			dosyaKonumu = dosyaOku.getSelectedFile().toString(); 
			FileReader okuyucu = new FileReader(dosyaOku.getSelectedFile());
			char[] okunanlar = new char[(int) dosyaOku.getSelectedFile().length()];
			okuyucu.read(okunanlar);
			//System.out.println(okunanlar);
			sudoku = sudokuYap(okunanlar);
		} catch (Exception e) {
			//System.out.println(e);
			System.out.println("Formata uygun olmayan bir dosya verildi yada iþlem iptal edildi!");
			System.exit(0);
		}

	}
	
	public int[][] sudokuYap(char[] okunanlar){
		int[][] matris = new int[9][9];
		
		int sat = 0 , sut = 0;//satýr, sutun
	    //System.out.println("okunan length: " + okunanlar.length);
	    for(int i = 0;  i < okunanlar.length; i++){
	    	//System.out.println("i: " + i + " sat: " + sat + " sut: "+ sut + "   Okunan: " + "." + okunanlar[i] + ".");
	    	if(okunanlar[i] == '\r'){
	    		//Windows da \n \r þeklinde alt satýra geçiliyor
	    		//bize bir tanesi yetiyor
		    }else if(okunanlar[i] == '\n'){
		      	sat++;
		      	sut=0;
		    } else if(okunanlar[i] == '*'){ 
		    	//ya 0 olcak ya da 
		    	matris[sat][sut] = 0;
			    sut++;
		    } else{
		      	matris[sat][sut] = Character.getNumericValue(okunanlar[i]);
		      	sut++;
		    }
		}
	    /*System.out.println("Sonrasý" + "\n----------------------------");
	    for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(matris[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print(matris[i][j]);
			}
			System.out.println("|");
		}
	    */
		
		return matris;
	}
	
	public void sudokuYaz(){
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
