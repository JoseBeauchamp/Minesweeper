import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class SuperExplosivo {

	public static int[][] mines = new int[10][10];
	public static Random generator = new Random();
	public static int MINE = 10;
	Random random = new Random();
	public static String[][] boardgame = new String[10][10]; 
	public static int neighbor;
	

	public static void SetMine(){
		MyMouseAdapterMS.gamestate=true;
		for(int i=1; i<10; i++){
			for(int j=1; j<10; j++){
				mines[i][j] = 0 ;
				boardgame[i][j] = "0";
			}
		}
		for(int c=1; c <11 ; c++){
			int i;
			int j;
			do{
				i = generator.nextInt(10);
				j = generator.nextInt(10);
			}while(mines[i][j] ==  MINE || i== 0 || j== 0);

			mines[i][j] = MINE;
			boardgame[i][j] = "*";


			System.out.print("Mine  " );
			System.out.println(c);

			System.out.print(" x= ");
			System.out.println(i);

			System.out.print(" y= ");
			System.out.println(j);
		}
		for(int i = 1; i<10 ;i++){
			for(int j = 1; j<10 ; j++){
				if(!(boardgame[i][j] == "*")){
					//search for neighbors			
					neighbor = 0;

					if((i ==1) && (j ==1)){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i+1][j] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i][j] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i +1][j+1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
					}
					//Bottom left
					if(i == 1 && j==1){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i +1][j]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i][j-1]==MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i + 1][j - 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
					}

					//Top Right
					if(i == 1 && j == 1){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i - 1][j] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i][j + 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i - 1][j + 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
					}
					//Bottom Right
					if(i == 1 && j == 1){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i - 1][j] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i][j - 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i - 1][j - 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
					}
					//Left Border
					if (i == 1 && j > 1 && j < 9){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i][j + 1] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i][j - 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						for(int c1=0; c1 <3 ; c1++){
							if(mines[i + 1][j - 1 + c1]== MINE){
								neighbor++;
								boardgame[i][j] = Integer.toString(neighbor);
							}
						}
					}
					//Right Border
					if (i == 9 && j > 1 && j < 9){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i][j + 1] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i][j - 1]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						for(int c1=0; c1 <3 ; c1++){
							if(mines[i - 1][j - 1 + c1]== MINE){
								neighbor++;
								boardgame[i][j] = Integer.toString(neighbor);
							}
						}
					}
					//Top Border
					if (j == 1 && i > 1 && i < 9){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i - 1][j] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i + 1][j]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						for(int c1=0; c1 <3 ; c1++){
							if(mines[i - 1 + c1][j + 1 ]== MINE){
								neighbor++;
								boardgame[i][j] = Integer.toString(neighbor);
							}
						}
					}
					//Bottom Border
					if (j == 9 && i > 1 && i < 9){
						boardgame[i][j] = Integer.toString(neighbor);
						if(mines[i - 1][j] == MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						if(mines[i + 1][j]== MINE){
							neighbor++;
							boardgame[i][j] = Integer.toString(neighbor);
						}
						for(int c1=0; c1 <3 ; c1++){
							if(mines[i - 1 + c1][j - 1 ]== MINE){
								neighbor++;
								boardgame[i][j] = Integer.toString(neighbor);
							}
						}
					}
					//Inside of grid Non-Border
					if((i != 1 && i != 9) && (j != 1 && j != 9)){
						boardgame[i][j] = Integer.toString(neighbor);
						for(int c2=0; c2 <3 ; c2++){
							if(mines[i -1 + c2][j - 1]== MINE){
								neighbor++;
								boardgame[i][j]= Integer.toString(neighbor); 
							}
						}
						for(int c1=0; c1 <3 ; c1++){
							if(mines[i -1 + c1][j + 1]== MINE){
								neighbor++;
								boardgame[i][j]= Integer.toString(neighbor); 
							}
						}
						if(mines[i -1][j]== MINE){
							neighbor++;
							boardgame[i][j]= Integer.toString(neighbor); 
							if(mines[i +1][j]== MINE){
								neighbor++;
								boardgame[i][j]= Integer.toString(neighbor); 
							}
						}
						System.out.println(neighbor);
					}
				}
			}
		}
		for(int j = 1; j<10;j++){

			for(int i = 1; i<10; i++){
				System.out.print(boardgame[i][j] + " | ");

				//Find a way to paint the board
			}
			System.out.println(" Row" + j);
			System.out.println("-----------------------------------------");
		}

	}

	public static void showBoard(Boolean gamestate){
		if (gamestate == false){
			System.out.println(" Bomb ");
			for(int j = 1; j<10;j++){

				for(int i = 1; i<10; i++){
					System.out.print(boardgame[i][j] + " | ");

					//Find a way to paint the board
				}
				System.out.println(" Row" + j);
				System.out.println("-----------------------------------------");
			}
		}

	}

	public static Graphics draw(Graphics g1, int gridX, int gridY){

		int x1 =40;
		int y1 = 40;




		switch(Integer.parseInt(boardgame[gridX][gridY])){
		case 1:
			Font font1 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font1);
			g1.setColor(Color.black);
			g1.drawString("1", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 2:
			Font font2 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font2);
			g1.setColor(Color.black);
			g1.drawString("2", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 3:
			Font font3 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font3);
			g1.setColor(Color.black);
			g1.drawString("3", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 4:
			Font font4 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font4);
			g1.setColor(Color.black);
			g1.drawString("4", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 5:
			Font font5 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font5);
			g1.setColor(Color.black);
			g1.drawString("5", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 6:
			Font font6 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font6);
			g1.setColor(Color.black);
			g1.drawString("6", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 7:
			Font font7 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font7);
			g1.setColor(Color.black);
			g1.drawString("7", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 8:
			Font font8 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font8);
			g1.setColor(Color.black);
			g1.drawString("8", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 9:
			Font font9 = new Font("arial", Font.BOLD, 15);
			g1.setFont(font9);
			g1.setColor(Color.black);
			g1.drawString("9", x1 + (gridX*30), y1 + (gridY*30));
			break;
		case 10:
			Font font10 = new Font("arial", Font.BOLD, 20);
			g1.setFont(font10);
			g1.setColor(Color.black);
			g1.drawString("10", x1 + (gridX*30), y1 + (gridY*30));
			break;

		}


		return g1;


	}


}
