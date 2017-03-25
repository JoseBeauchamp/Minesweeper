import javax.swing.JFrame;

public class MainMS {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("SuperExplosivoMegaBrutalPatentadoPorLaUNAM");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(460, 460);

		
		MyPanelMS myPanel = new MyPanelMS();
		myFrame.add(myPanel);

		MyMouseAdapterMS myMouseAdapter = new MyMouseAdapterMS();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		
		SuperExplosivo.SetMine();
	}
}