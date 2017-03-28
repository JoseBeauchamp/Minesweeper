import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class MyMouseAdapterMS extends MouseAdapter {

	Random random = new Random();
	Scanner input = new Scanner(System.in);
	public int MINE = 10;
	public int neighbor = 0;
	public static boolean gamestate;
	public int temp;
	
	public void colorPicker(){
	Color numColor = null;

		switch (temp) {
		case 0:
			numColor = Color.CYAN;
			break;
		case 1:
			numColor = Color.BLUE;
			break;
		case 2:
			numColor =new Color(0x9900ff);
			break;
		case 3:
			numColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
			break;
		case 4:
			numColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
			break;
		}
	}


	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanelMS myPanel = (MyPanelMS) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			//determines position of mouse click
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanelMS myPanelR = (MyPanelMS) myFrameR.getContentPane().getComponent(0);
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			myPanelR.mouseDownGridX = myPanelR.getGridX(xR, yR);
			myPanelR.mouseDownGridY = myPanelR.getGridY(xR, yR);
			myPanelR.repaint();


			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanelMS myPanel = (MyPanelMS) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);

			System.out.print(" x =");
			System.out.println(gridX);

			System.out.print(" y =");
			System.out.println(gridY);
			if(gridX == -2 && gridY == -2){
				for(int i = 0; i < 9; i++){
					for(int j = 0; j < 9; j++){
						myPanel.colorArray[i][j] = Color.WHITE;
					}
				}
				SuperExplosivo.SetMine();
			}
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {}
			//Had pressed outside
			//Do nothing
			else {
				if ((gridX == -1) || (gridY == -1)) {}
				//Is releasing outside
				//Do nothing
				else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {}
					//Released the mouse button on a different cell where it was pressed 
					else {
						//Released the mouse button on the same cell where it was pressed
						//if no win or lose and clicked
						if(gamestate){
							
							
							//inside of grid
							if((gridX >0 && gridX <10) && (gridY >0 && gridY <10)){
								System.out.println(SuperExplosivo.boardgame[gridX][gridY]);
								//tile clicked is not bomb
								if(SuperExplosivo.mines[gridX][gridY] != MINE){
									
									myPanel.colorArray[gridX-1][gridY-1] = Color.BLUE;
									myPanel.repaint();
									int temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);

									if(temp == 0 || temp == 1 || temp ==2 || temp==3 || temp == 4 || temp == 5 || temp == 6 || temp == 7){
										int n;
										//right
										if(gridX != 9 || SuperExplosivo.mines[gridX+1][gridY] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridX+n != 10 && SuperExplosivo.mines[gridX+n][gridY] != MINE){
												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX + n][gridY]);

												if(SuperExplosivo.mines[gridX+n][gridY] != MINE && myPanel.colorArray[gridX-1+n][gridY-1] == Color.WHITE){
													System.out.println("painted");
													myPanel.colorArray[gridX-1+n][gridY-1] = Color.CYAN;
													
												}
												n++;
											}
										}

										//down
										if(gridY != 9 || SuperExplosivo.mines[gridX][gridY + 1] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridY+n != 10 && SuperExplosivo.mines[gridX][gridY+n] != MINE){
												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX][gridY+n]);
												if(SuperExplosivo.mines[gridX][gridY+n] != MINE && myPanel.colorArray[gridX-1][gridY-1+n] == Color.WHITE){
													myPanel.colorArray[gridX-1][gridY-1+n] = Color.CYAN;
													
												}
												n++;
											}
										}

										//up
										if(gridY != 1 || SuperExplosivo.mines[gridX][gridY -1] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											
											while( temp == 0 && gridY-n != 0 && SuperExplosivo.mines[gridX][gridY-n] != MINE){
												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX][gridY-n]);
												if(SuperExplosivo.mines[gridX][gridY-n] != MINE && myPanel.colorArray[gridX-1][gridY-1-n] == Color.WHITE){
													myPanel.colorArray[gridX-1][gridY-1-n] = Color.CYAN;
													
												}
												n++;
											}
										}
										//left
										if(gridX != 1 || SuperExplosivo.mines[gridX-1][gridY] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridX-n != 0 && SuperExplosivo.mines[gridX-n][gridY] != MINE){
												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX-n][gridY]);
												if(SuperExplosivo.mines[gridX-n][gridY] != MINE && myPanel.colorArray[gridX-1-n][gridY-1] == Color.WHITE){
													myPanel.colorArray[gridX-1-n][gridY-1] = Color.CYAN;
													
												}
												n++;
											}
										}

										//right down diagonal
										if( gridX != 9 || gridY != 9 || SuperExplosivo.mines[gridX+1][gridY+1] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridX+n != 10  && gridY+n != 10 && SuperExplosivo.mines[gridX+n][gridY+n] != MINE ){

												myPanel.colorArray[gridX-1 + n][gridY-1 + n] = Color.CYAN;
												

												//right expansion
												if(gridX + n != 9){
													int tempR;
													int nR =0;
													tempR = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempR == 0 && gridX + n +nR != 10 && gridY + n != 10 && SuperExplosivo.mines[gridX+n+nR][gridY+n] != MINE){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX + n+nR][gridY + n]);
														if(SuperExplosivo.mines[gridX+n+nR][gridY+n] != MINE && myPanel.colorArray[gridX-1 +n+nR][gridY-1 +n] == Color.WHITE){
															myPanel.colorArray[gridX-1 +n+nR][gridY-1 +n] = Color.CYAN;
															
														}
														nR++;
													}
												}
												//Downwards expansion
												if(gridY + n != 9 ){
													int tempD;
													int nD =0;
													tempD = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempD == 0 && gridX + n != 10 && gridY + n +nD!= 10 && SuperExplosivo.mines[gridX+n][gridY+n+nD] != MINE ){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX + n][gridY + n + nD]);
														if(SuperExplosivo.mines[gridX+n][gridY+n+nD] != MINE && myPanel.colorArray[gridX-1 +n][gridY-1 +n + nD] == Color.WHITE){	
															myPanel.colorArray[gridX-1 +n][gridY-1 +n + nD] = Color.CYAN;
															
														}
														nD++;
													}
												}
												n++;
											}
										}

										//left down diagonal
										if(gridX != 1 || gridY != 9 || SuperExplosivo.mines[gridX-1][gridY+1] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridX-n != 0 && gridY+n != 10 && SuperExplosivo.mines[gridX-n][gridY+n] != MINE){
												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX - n][gridY + n]);
												if(SuperExplosivo.mines[gridX-n][gridY+n] != MINE && myPanel.colorArray[gridX-1-n][gridY-1+n] == Color.WHITE){
													myPanel.colorArray[gridX-1-n][gridY-1+n] = Color.CYAN;
													
												}
												//left expansion
												if(gridX - n != 1){
													int tempL; 
													int nL = 0; 
													tempL = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempL == 0 && gridX - n -nL != 0 && gridY + n != 10 && SuperExplosivo.mines[gridX-n-nL][gridY+n] != MINE){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX - n-nL][gridY - n]);
														if(SuperExplosivo.mines[gridX-n-nL][gridY+n] != MINE && myPanel.colorArray[gridX-1 -n-nL][gridY-1 +n] == Color.WHITE){
															myPanel.colorArray[gridX-1 -n-nL][gridY-1 +n] = Color.CYAN;
															
														}
														nL++;
													}
												}
												//downwards expansion
												if(gridY + n != 9){
													int tempD; 
													int nD = 0; 
													tempD = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempD == 0 && gridX - n != 0 && gridY + n +nD!= 10 && SuperExplosivo.mines[gridX-n][gridY+n+nD] != MINE ){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX - n][gridY + n+nD]);
														if(SuperExplosivo.mines[gridX-n][gridY+n+nD] != MINE && myPanel.colorArray[gridX-1 -n][gridY-1 +n +nD] == Color.WHITE){
															myPanel.colorArray[gridX-1 -n][gridY-1 +n +nD] = Color.CYAN;
															
														}
														nD++;
													}
												}
												n++;
											}
										}

										//right up diagonal

										if(gridX != 9 || gridY  != 1 || SuperExplosivo.mines[gridX +1][gridY-1] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridX+n != 10 && gridY-n != 0 && SuperExplosivo.mines[gridX+n][gridY-n] != MINE){
												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX + n][gridY - n]);
												if(SuperExplosivo.mines[gridX+n][gridY-n] != MINE && myPanel.colorArray[gridX-1 +n][gridY-1 -n] == Color.WHITE){
													myPanel.colorArray[gridX-1 +n][gridY-1 -n] = Color.CYAN;
													
												}
												//right expansion
												int tempR; 
												int nR = 0; 
												tempR = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
												while(tempR == 0 && gridX + n +nR != 10 && gridY - n != 0 && SuperExplosivo.mines[gridX+n+nR][gridY-n] != MINE){
													temp =Integer.parseInt( SuperExplosivo.boardgame[gridX + n+nR][gridY - n]);
													if(SuperExplosivo.mines[gridX+n+nR][gridY-n] != MINE && myPanel.colorArray[gridX-1 +n+nR][gridY-1 -n] == Color.WHITE){
														myPanel.colorArray[gridX-1 +n+nR][gridY-1 -n] = Color.CYAN;
														
													}
													nR++;
												}
												//upwards expansion
												if(gridY - n != 1){
													int tempU; 
													int nU = 0; 
													tempU = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempU == 0 && gridX + n != 10 && gridY - n -nU!= 0 && SuperExplosivo.mines[gridX+n][gridY-n-nU] != MINE){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX + n][gridY - n-nU]);
														if(SuperExplosivo.mines[gridX+n][gridY-n-nU] != MINE && myPanel.colorArray[gridX-1 +n][gridY-1 -n -nU] == Color.WHITE){
															myPanel.colorArray[gridX-1 +n][gridY-1 -n -nU] = Color.CYAN;
															
														}
														nU++;
													}
												}
												n++;

											}
										}

										//left up diagonal
										if(gridX != 1 || gridY != 1 || SuperExplosivo.mines[gridX-1][gridY-1] != MINE){
											n = 0;
											temp = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
											while( temp == 0 && gridX-n != 0 && gridY-n != 0 && SuperExplosivo.mines[gridX-n][gridY-n] != MINE){

												//left expansion
												if(gridX - n != 1 || SuperExplosivo.mines[gridX-n][gridY] != MINE){
													int tempL;
													int nL =0;
													tempL = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempL == 0 && gridX - n -nL != 0 && gridY - n != 0){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX - n-nL][gridY - n]);
														if(SuperExplosivo.mines[gridX-n-nL][gridY-n] != MINE && myPanel.colorArray[gridX-1 -n-nL][gridY-1 -n] == Color.WHITE){
															myPanel.colorArray[gridX-1 -n-nL][gridY-1 -n] = Color.CYAN;
															
														}
														nL++;
													}
												}
												//Upwards expansion
												if(gridY - n != 1 || SuperExplosivo.mines[gridX][gridY-n] != MINE){
													int tempU;
													int nU =0;
													tempU = Integer.parseInt(SuperExplosivo.boardgame[gridX][gridY]);
													while(tempU == 0 && gridX - n != 0 && gridY - n -nU!= 0){
														temp =Integer.parseInt( SuperExplosivo.boardgame[gridX - n][gridY - n - nU]);
														if(SuperExplosivo.mines[gridX-n][gridY-n-nU] != MINE && myPanel.colorArray[gridX-1 -n][gridY-1 -n - nU] == Color.WHITE){	
															myPanel.colorArray[gridX-1 -n][gridY-1 -n - nU] = Color.CYAN;
															
														}
														nU++;
													}
												}

												temp =Integer.parseInt( SuperExplosivo.boardgame[gridX - n][gridY - n]);
												if(SuperExplosivo.mines[gridX-n][gridY-n] != MINE && myPanel.colorArray[gridX-1 -n][gridY-1 -n] == Color.WHITE){
													myPanel.colorArray[gridX-1 -n][gridY-1 -n] = Color.CYAN;
													
												}
												n++;
											}
										}
									}
								}

							}
							myPanel.repaint();
						}


						if(SuperExplosivo.mines[gridX][gridY] == MINE){
							for(int i = 1; i < 10; i++){
								for(int j = 1; j < 10; j++){
									if(SuperExplosivo.mines[i][j] == MINE){
										myPanel.colorArray[i-1][j-1] = Color.black;
										gamestate = false;

									}
								}

							}
							SuperExplosivo.showBoard(gamestate);
						//} else if(!SuperExplosivo.boardgame[gridX][gridY].equals("0")||!SuperExplosivo.boardgame[gridX][gridY].equals("20")){
							//myPanel.
						}
					}

				}	
			}
			myPanel.repaint();
			break;


		case 3:		//Right mouse button
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanelMS myPanelR = (MyPanelMS) myFrameR.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			int gridXR = myPanelR.getGridX(xR, yR);
			int gridYR = myPanelR.getGridY(xR, yR);

			if ((myPanelR.mouseDownGridX == -1) || (myPanelR.mouseDownGridY == -1)) {}
			//Had pressed outside
			//Do nothing
			else {
				if ((gridXR == -1) || (gridYR == -1)) {}
				//Is releasing outside
				//Do nothing
				else {
					if ((myPanelR.mouseDownGridX != gridXR) || (myPanelR.mouseDownGridY != gridYR)) {}
					//Released the mouse button on a different cell where it was pressed 
					else {
						//Released the mouse button on the same cell where it was pressed
						if(myPanelR.colorArray[gridXR-1][gridYR-1].equals(Color.RED)){
							myPanelR.colorArray[gridXR-1][gridYR-1] = Color.WHITE;
						}
						else{
							myPanelR.colorArray[gridXR-1][gridYR-1] = Color.RED;
						}
					}
				}
			}

			myPanelR.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}	
}