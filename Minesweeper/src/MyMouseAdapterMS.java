import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class MyMouseAdapterMS extends MouseAdapter {

	Random random = new Random();
	Scanner input = new Scanner(System.in);



	public void Flag(){
		//place flag on tile


	}


	//	if(MineExploded){
	//		public void Lose(){
	//			//Show Board
				//Game state is false
	//		}
	//	}

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
			if(gridX == -2 || gridY == -2){
				SuperExplosivo.SetMine();
			}

			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {}
					//Released the mouse button on a different cell where it was pressed 
					else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {}
						//Bottom Left
						if(gridX == 0 && gridY == 0){}
						//Left Collumn
						if(gridX == 0 && gridY!= 0){}
						//Top Row
						if(gridY == 0 && gridX != 0){}


						else {
							//On the grid other than on the left column and on the top row:

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

			if(gridXR >0 && gridXR <11 && gridYR >0 && gridYR>11){
				Flag();
			}
			if ((gridXR == -1) || (gridYR == -1)) { // If you click outside the grid

			}



			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}