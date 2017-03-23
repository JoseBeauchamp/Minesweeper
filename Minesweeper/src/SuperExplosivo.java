import java.util.Random;

public class SuperExplosivo {

	public static int[][] mines = new int[10][10];
	public static Random generator = new Random();
	public static int MINE = 10;
	Random random = new Random();
	
	//public static void NeighboringMines(){
		
		//int neighbor = 0;
		
			
		//}
	public static void SetMine(){
		for(int i=1; i<9; i++){
			for(int j=1; j<9; j++){
				mines[i][j] = 0 ;
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


			System.out.print("Mine  " );
			System.out.println(c);

			System.out.print(" x= ");
			System.out.println(i);

			System.out.print(" y= ");
			System.out.println(j);
	
		}

	}

}
