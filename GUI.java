package yazlab2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI {
	
	public JFrame pencere;
	public JPanel ana;
	public JPanel sudokuPanel;
	public JPanel aracPanel;
	public JButton[][] alanlar;
	
	public int gorunenHamle;
	
	
	public Threadci[] threadciler;
	public int gecerliThread; 
	
	public GUI() {
		pencereYap();
		sudokuyaAlanYap();
		buttonlaraAlanYap();
		
		ana.add(sudokuPanel);
		ana.add(aracPanel);
		pencere.setVisible(true);
		
		sudokuAta();
		sudokuAta(OkuYaz.sudoku);
		
	}
	
	public void pencereYap(){
		pencere = new JFrame("Atakan Ateþ Murat Demir");
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setSize(800, 530);
		pencere.setResizable(false);
		ana = new JPanel();
		ana.setLayout(new BoxLayout(ana, BoxLayout.X_AXIS));
		pencere.add(ana);
	}
	
	public void sudokuyaAlanYap(){
		alanlar = new JButton[9][9];
		sudokuPanel = new JPanel();
		sudokuPanel.setBackground(new Color(150, 150, 150));
		
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				alanlar[i][j] = new JButton(i + "" + j); 
				alanlar[i][j].setPreferredSize(new Dimension(50, 50));
				alanlar[i][j].setName(i + "," + j);
				
				if((i/3)==0 && (j/3)==0){
					alanlar[i][j].setBackground(new Color(245, 245, 245));
				}else if((i/3)==0 && (j/3)==1){
					alanlar[i][j].setBackground(new Color(80, 80, 80));
					alanlar[i][j].setForeground(new Color(245, 245, 245));
				}else if((i/3)==0 && (j/3)==2){
					alanlar[i][j].setBackground(new Color(245, 245, 245));
				}else if((i/3)==1 && (j/3)==0){
					alanlar[i][j].setBackground(new Color(80, 80, 80));
					alanlar[i][j].setForeground(new Color(245, 245, 245));
				}else if((i/3)==1 && (j/3)==1){
					alanlar[i][j].setBackground(new Color(245, 245, 245));
				}else if((i/3)==1 && (j/3)==2){
					alanlar[i][j].setBackground(new Color(80, 80, 80));
					alanlar[i][j].setForeground(new Color(245, 245, 245));
				}else if((i/3)==2 && (j/3)==0){
					alanlar[i][j].setBackground(new Color(245, 245, 245));
				}else if((i/3)==2 && (j/3)==1){					
					alanlar[i][j].setBackground(new Color(80, 80, 80));
					alanlar[i][j].setForeground(new Color(245, 245, 245));
				}else if((i/3)==2 && (j/3)==2){
					alanlar[i][j].setBackground(new Color(245, 245, 245));
				}
				
				
				/*JLabel alan = new JLabel(i + "," + j,JLabel.CENTER);
				alan.setPreferredSize(new Dimension(50, 50));
				alan.setForeground(new Color(0, 0, 0));
				alan.setBackground(new Color(255, 255, 255));
				alan.setOpaque(true);*/
				
				sudokuPanel.add(alanlar[i][j]);
			}
			
		}		
		
		sudokuPanel.setMaximumSize(new Dimension(500, 530));
		sudokuPanel.setPreferredSize(new Dimension(500, 530));
	}
	
	public void buttonlaraAlanYap(){
		aracPanel = new JPanel();
		aracPanel.setBackground(new Color(240,248,255));
		
		JLabel sayThread = new JLabel("Kaç Thread ile iþlem gerçekleþeceðini seçiniz!");
		aracPanel.add(sayThread);
		sayThread.setBorder(new EmptyBorder(10, 2, 10, 2));
		
		JSlider threadKay = new JSlider(JSlider.HORIZONTAL, 3, 10, 3);
		aracPanel.add(threadKay);		
		//Turn on labels at major tick marks.
		threadKay.setMajorTickSpacing(1);
		threadKay.setMinorTickSpacing(1);
		threadKay.setPaintTicks(true);
		threadKay.setPaintLabels(true);
		threadKay.setBorder(new EmptyBorder(2, 2, 10, 2));
		threadKay.setBackground(new Color(240,248,255));
		
		JButton baslat = new JButton("Ýþlemi Baþlat");
		aracPanel.add(baslat);
		
		
		JLabel hamleBas = new JLabel("Geçerli Hamle");
		aracPanel.add(hamleBas);
		hamleBas.setBorder(new EmptyBorder(10, 2, 10, 2));

		
		JButton geri = new JButton("Geri");
		aracPanel.add(geri);
		geri.setPreferredSize(new Dimension(75, 25));
		geri.setMaximumSize(new Dimension(75, 25));
		geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(threadciler == null)
					return;
				
				if(gorunenHamle < 0)
					return;
				
				int sat = threadciler[gecerliThread].sudokucu.hamleler.get(gorunenHamle).sat;
				int sut = threadciler[gecerliThread].sudokucu.hamleler.get(gorunenHamle).sut;
				int say = threadciler[gecerliThread].sudokucu.hamleler.get(gorunenHamle).say;
				alanlar[sat][sut].setText("");
				gorunenHamle--;
				hamleBas.setText("Görüntülenen Hamle: " + (gorunenHamle+1));
			}
		});
		JLabel bosluk = new JLabel("");
		aracPanel.add(bosluk);
		bosluk.setBorder(new EmptyBorder(4, 2, 4, 2));
		
		JButton ileri = new JButton("Ýleri");
		aracPanel.add(ileri);
		ileri.setPreferredSize(new Dimension(75, 25));
		ileri.setMaximumSize(new Dimension(75, 25));
		ileri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(threadciler == null)
					return;
				
				if(gorunenHamle > (threadciler[gecerliThread].sudokucu.hamleler.size() - 2))
					return;
				
				gorunenHamle++;
				
				int sat = threadciler[gecerliThread].sudokucu.hamleler.get(gorunenHamle).sat;
				int sut = threadciler[gecerliThread].sudokucu.hamleler.get(gorunenHamle).sut;
				int say = threadciler[gecerliThread].sudokucu.hamleler.get(gorunenHamle).say;
				alanlar[sat][sut].setText("" + say);
				hamleBas.setText("Görüntülenen Hamle: " + (gorunenHamle+1));
			}
		});
		
		JLabel th = new JLabel("Thread Seç");
		aracPanel.add(th);
		th.setBorder(new EmptyBorder(10, 2, 2, 2));
		
		JLabel ilkHamle = new JLabel("Baþlangýç Sat,Sut");
		aracPanel.add(ilkHamle);
		ilkHamle.setBorder(new EmptyBorder(2, 2, 2, 2));
		
		JLabel zaman = new JLabel("Zaman");
		aracPanel.add(zaman);
		zaman.setBorder(new EmptyBorder(2, 2, 10, 2));
		
		JButton beriThread = new JButton("Önceki Thread");
		aracPanel.add(beriThread);
		beriThread.setPreferredSize(new Dimension(140, 30));
		beriThread.setMaximumSize(new Dimension(140, 30));
		
		JLabel bosluk2 = new JLabel("");
		aracPanel.add(bosluk2);
		bosluk2.setBorder(new EmptyBorder(4, 2, 4, 2));
		
		JButton sonrakiThread = new JButton("Sonraki Thread");
		aracPanel.add(sonrakiThread);
		sonrakiThread.setPreferredSize(new Dimension(140, 30));
		sonrakiThread.setMaximumSize(new Dimension(140, 30));
		
		JLabel bitiren = new JLabel("bitiren");
		aracPanel.add(bitiren);
		bitiren.setBorder(new EmptyBorder(10, 2, 10, 2));
		
		baslat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadleriYarat(threadKay.getValue()); 	
				
				if(threadciler[0].sudokucu != null){
					sudokuAta();
					sudokuAta(threadciler[0].sudokucu.sudoku);
					GecerliThread(0);
					beriThread.doClick();
				}
			}
		});
		
		beriThread.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(threadciler == null)
					return;
				
				sudokuAta();
				gecerliThread = (gecerliThread-1+threadciler.length)%threadciler.length;
				GecerliThread(gecerliThread);
				
				sudokuAta(threadciler[gecerliThread].sudokucu.sudoku);
				th.setText(threadciler[gecerliThread].ad);
				zaman.setText("Bitirme Süresi: " + threadciler[gecerliThread].zaman + " nanosaniye");
				bitiren.setText("Ýlk bitiren thread: " + Threadci.bitirenThread);
				hamleBas.setText("Görüntülenen Hamle: " + (gorunenHamle+1));
				int sat = threadciler[gecerliThread].sudokucu.hamleler.get(0).sat;
				int sut = threadciler[gecerliThread].sudokucu.hamleler.get(0).sut;
				ilkHamle.setText("Baþlangýç satýrý ve sütunu: " + sat + "," + sut);
			}
		});
		sonrakiThread.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(threadciler == null)
					return;
				
				sudokuAta();
				gecerliThread = (gecerliThread+1)%threadciler.length;
				GecerliThread(gecerliThread);
				
				sudokuAta(threadciler[gecerliThread].sudokucu.sudoku);
				th.setText(threadciler[gecerliThread].ad);
				zaman.setText("Bitirme Süresi: " + threadciler[gecerliThread].zaman + " nanosaniye");
				bitiren.setText("Ýlk bitiren thread: " + Threadci.bitirenThread);
				hamleBas.setText("Görüntülenen Hamle: " + (gorunenHamle+1));
				int sat = threadciler[gecerliThread].sudokucu.hamleler.get(0).sat;
				int sut = threadciler[gecerliThread].sudokucu.hamleler.get(0).sut;
				ilkHamle.setText("Baþlangýç satýrý ve sütunu: " + sat + "," + sut);
			}
		});

		aracPanel.setLayout(new BoxLayout(aracPanel, BoxLayout.Y_AXIS));
		
		aracPanel.setMaximumSize(new Dimension(294, 530));
		aracPanel.setPreferredSize(new Dimension(294, 530));
	}
	
	public void sudokuAta(int[][] sudoku){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//System.out.println(sudoku[i][j]);
				if(sudoku[i][j] != 0)
					alanlar[i][j].setText("" + sudoku[i][j]);		
			}
		}
		
	}
	
	public void sudokuAtaUzun(int[][] sudoku){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//System.out.println(sudoku[i][j]);
				alanlar[i][j].setText("" + sudoku[i][j]);		
			}
		}
		
	}
	public void sudokuAta(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//System.out.println(sudoku[i][j]);
				alanlar[i][j].setText("");		
			}
		}
		
	}
	
	public void threadcileriAta(Threadci[] threadler){
		threadciler = threadler;
	}
	
	public void GecerliThread(int threadAta){
		gecerliThread = threadAta;
		gorunenHamle = (threadciler[gecerliThread].sudokucu.hamleler.size() - 1);
		//System.out.println(gorunenHamle);
	}
	
	
	public void threadleriYarat(int adet){
		Threadci.Kesme = false;
		Threadci.bitirenThread = null;
		
		threadciler = new Threadci[adet];
		
		for (int i = 0; i < adet; i++) {
			Threadci yeniThread = new Threadci(("Thread" + i),i);
			threadciler[i] = yeniThread;
		}
		
		for (int i = 0; i < adet; i++) {
			threadciler[i].start();
		}
		

		
		/*if(threadciler[0].sudokucu != null){
			sudokuAta();
			sudokuAta(threadciler[0].sudokucu.sudoku);
			GecerliThread(0);
		}*/
		
		
		/*gui.threadciler();
		gui.GecerliThread(thread0);*/
		
		/*System.out.println("Thread0");
		Sudokucu.sudokuUzunYaz(thread0.sudokucu.sudoku);*/

		
		
		/*System.out.println("Thread0");
		Sudokucu.sudokuUzunYaz(thread0.sudoku);*/
		/*System.out.println("Thread1");
		Sudokucu.sudokuUzunYaz(thread1.sudoku);
		System.out.println("Thread2");
		Sudokucu.sudokuUzunYaz(thread2.sudoku);
		System.out.println("Thread3");
		Sudokucu.sudokuUzunYaz(thread3.sudoku);*/
	}
	
	
	
	
	/*public void GUIYap(){
		
	}*/
	
}