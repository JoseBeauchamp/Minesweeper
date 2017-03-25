import java.awt.Color;
import java.awt.Component;
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
//need to find what makes the null appear
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
						neighbor = 0;
						
						if((gridX ==1) && (gridY ==1)){
							if(SuperExplosivo.mines[gridX+1][gridY] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX][gridY] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX +1][gridY+1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
						}
						//Bottom left
						if(gridX == 1 && gridY==1){
							
							if(SuperExplosivo.mines[gridX +1][gridY]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX][gridY-1]==MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX + 1][gridY - 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
						}
						
						//Top Right
						if(gridX == 1 && gridY == 1){
							if(SuperExplosivo.mines[gridX - 1][gridY] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX][gridY + 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX - 1][gridY + 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
						}
						//Bottom Right
						if(gridX == 1 && gridY == 1){
							if(SuperExplosivo.mines[gridX - 1][gridY] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX][gridY - 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX - 1][gridY - 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
						}
						//Left Border
						if (gridX == 1 && gridY > 1 && gridY < 9){
							if(SuperExplosivo.mines[gridX][gridY + 1] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX][gridY - 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							for(int i=0; i <3 ; i++){
								if(SuperExplosivo.mines[gridX + 1][gridY - 1 + i]== MINE){
									neighbor++;
									SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
								}
							}
						}
						//Right Border
						if (gridX == 9 && gridY > 1 && gridY < 9){
							if(SuperExplosivo.mines[gridX][gridY + 1] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX][gridY - 1]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							for(int i=0; i <3 ; i++){
								if(SuperExplosivo.mines[gridX - 1][gridY - 1 + i]== MINE){
									neighbor++;
									SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
								}
							}
						}
						//Top Border
						if (gridY == 1 && gridX > 1 && gridX < 9){
							if(SuperExplosivo.mines[gridX - 1][gridY] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX + 1][gridY]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							for(int i=0; i <3 ; i++){
								if(SuperExplosivo.mines[gridX - 1 + i][gridY + 1 ]== MINE){
									neighbor++;
									SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
								}
							}
						}
						//Top Border
						if (gridY == 9 && gridX > 1 && gridX < 9){
							if(SuperExplosivo.mines[gridX - 1][gridY] == MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX + 1][gridY]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							for(int i=0; i <3 ; i++){
								if(SuperExplosivo.mines[gridX - 1 + i][gridY - 1 ]== MINE){
									neighbor++;
									SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
								}
							}
						}
						//Inside of grid Non-Border
						if((gridX != 1 && gridX != 9) && (gridY != 1 && gridY != 9)){

							for(int i=0; i <3 ; i++){
								if(SuperExplosivo.mines[gridX -1 + i][gridY - 1]== MINE){
									neighbor++;
									SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
								}
							}
							for(int i=0; i <3 ; i++){
								if(SuperExplosivo.mines[gridX -1 + i][gridY + 1]== MINE){
									neighbor++;
									SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
								}
							}
							if(SuperExplosivo.mines[gridX -1][gridY]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
							if(SuperExplosivo.mines[gridX +1][gridY]== MINE){
								neighbor++;
								SuperExplosivo.boardgame[gridX][gridY] = Integer.toString(neighbor);
							}
						}
						System.out.println(neighbor);



						for(int i = 1; i < 10; i++){
							for(int j = 1; j < 10; j++){
								if(SuperExplosivo.mines[i][j] == MINE){
									if((gridX == i) && (gridY == j)){
										myPanel.colorArray[gridX-1][gridY-1] = Color.black;
										gamestate = false;
										SuperExplosivo.showBoard(gamestate);
									}
								}

							}
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

						for(int i = 1; i < 10; i++){
							for(int j = 1; j < 10; j++){
								if((gridXR == i) && (gridYR == j)){
									if(myPanelR.colorArray[gridXR-1][gridYR-1].equals(Color.RED)){
										myPanelR.colorArray[gridXR-1][gridYR-1] = Color.WHITE;
									}
									else{
										myPanelR.colorArray[gridXR-1][gridYR-1] = Color.RED;
									}
								}
							}
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