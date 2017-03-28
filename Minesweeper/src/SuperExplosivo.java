import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class SuperExplosivo {

	public static int[][] mines = new int[11][11];
	public static Random generator = new Random();
	public static int MINE = 10;
	Random random = new Random();
	public static String[][] boardgame = new String[11][11]; 
	public static int neighbor;
	

	public static void SetMine(){
		MyMouseAdapterMS.gamestate=true;
		for(int i=1; i<10; i++){
			for(int j=1; j<10; j++){
				mines[i][j] = 0 ;
				boardgame[i][j] = "0";
				MyMouseAdapterMS.revealed[i][j]=false;
			}
		}
		
		
		//10 Mines will be made
		for(int c=1; c <11 ; c++){
			int i;
			int j;
			do{
				i = generator.nextInt(10);
				j = generator.nextInt(10);
			}while(mines[i][j] ==  MINE || i== 0 || j== 0);

			//setting mines value for arrays
			mines[i][j] = MINE;
			boardgame[i][j] = "20";


			System.out.print("Mine  " );
			System.out.println(c);

			System.out.print(" x= ");
			System.out.println(i);

			System.out.print(" y= ");
			System.out.println(j);
		}
		
		for(int i = 1; i<10 ;i++){
			for(int j = 1; j<10 ; j++){
				if(!(boardgame[i][j] == "20")){
					//search for neighbors			
					neighbor = 0;

					//upper left grid corner neighbor check
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
					//Bottom left grid corner neighbor check
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

					
					//Top Right grid corner neighbor check
					if(i == 9 && j == 1){
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
					//Bottom Right grid corner neighbor check
					if(i == 9 && j == 9){
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
					//Left grid Border neighbor check
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
					//Right grid Border neighbor check
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
					//Top grid Border neighbor check
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
					//Bottom grid Border neighbor check
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
					//Inside of grid Non-Border neighbor check
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
						}
							if(mines[i +1][j]== MINE){
								neighbor++;
								boardgame[i][j]= Integer.toString(neighbor); 
							}
					}
				}
			}
		}
		//console grid for easier reading of grid values 
		for(int j = 1; j<10;j++){

			for(int i = 1; i<10; i++){
				System.out.print(boardgame[i][j] + " | ");

			}
			System.out.println(" Row" + j);
			System.out.println("-----------------------------------------");
		}

	}

	//console board show for loss
	public static void showBoard(Boolean gamestate){
		if (gamestate == false){
			System.out.println(" Bomb ");
			for(int j = 1; j<10;j++){

				for(int i = 1; i<10; i++){
					System.out.print(boardgame[i][j] + " | ");


				}
				System.out.println(" Row" + j);
				System.out.println("-----------------------------------------");
			}
		}

	}

	


}
