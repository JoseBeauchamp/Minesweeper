import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class MyMouseAdapterMS extends MouseAdapter {

	Random random = new Random();
	Scanner input = new Scanner(System.in);
	public int MINE = 10;




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
				if ((gridX == -1) || (gridY == -1)) {

				}
				//Is releasing outside
				//Do nothing
				else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {}
					//Released the mouse button on a different cell where it was pressed 
					else {
						//Released the mouse button on the same cell where it was pressed
						int neighbor = 0;

						//Top Left
						if(gridX == 1 && gridY == 1){
							if(SuperExplosivo.mines[gridX+1][gridY] == MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX][gridY+ 1]== MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX+1][gridY+ 1]== MINE){
								neighbor++;
							}
						}

						//bottom left
						if(gridX == 1 && gridY == 1){
							if(SuperExplosivo.mines[gridX+1][gridY] == MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX][gridY - 1]== MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX + 1][gridY - 1]== MINE){
								neighbor++;
							}
						}

						//Top Right
						if(gridX == 1 && gridY == 1){
							if(SuperExplosivo.mines[gridX - 1][gridY] == MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX][gridY + 1]== MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX - 1][gridY + 1]== MINE){
								neighbor++;
							}
						}

						//Bottom Right
						if(gridX == 1 && gridY == 1){
							if(SuperExplosivo.mines[gridX - 1][gridY] == MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX][gridY - 1]== MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX - 1][gridY - 1]== MINE){
								neighbor++;
							}
						}

						//inside of grid nonborder
						if((gridX != 1 && gridX != 9) && (gridY != 1 && gridY != 9)){


							for(int i=0; i < 3 ; i++){
								if(SuperExplosivo.mines[gridX -1 + i][gridY - 1]== MINE){
									neighbor++;
								}
							}
							for(int i=0; i < 3 ; i++){
								if(SuperExplosivo.mines[gridX -1 + i][gridY + 1]== MINE){
									neighbor++;
								}
							}

							if(SuperExplosivo.mines[gridX -1][gridY]== MINE){
								neighbor++;
							}
							if(SuperExplosivo.mines[gridX +1][gridY]== MINE){
								neighbor++;
							}
						}
						System.out.println(neighbor);
						// print on tile

						switch (neighbor) {
						case 0:

							break;
						case 1:

							break;
						case 2:

							break;
						case 3:

							break;
						case 4:
							break;
						case 5:
							break;
						case 6:

							break;
						case 7:

							break;
						case 8:

							break;
						}

						for(int i = 1; i < 10; i++){
							for(int j = 1; j < 10; j++){
								if(SuperExplosivo.mines[i][j] == MINE){
									if((gridX == i) && (gridY == j)){
										myPanel.colorArray[gridX-1][gridY-1] = Color.black;
									}
								}

							}
							//SuperExplosivo.NeighboringMines();

						}

						//
						//						if ((gridX == 0) || (gridY == 0)) {
						//						}
						//						//Bottom Left
						//						if(gridX == 0 && gridY == 0){}
						//						//Left Collumn
						//						if(gridX == 0 && gridY!= 0){}
						//						//Top Row
						//						if(gridY == 0 && gridX != 0){}


						//						else {
						//							//On the grid other than on the left column and on the top row:
						//
						//						}
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
									else if(SuperExplosivo.mines[i][j] == MINE){
										myPanelR.colorArray[gridXR-1][gridYR-1] = Color.black;
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