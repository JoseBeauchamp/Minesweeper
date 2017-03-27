import javax.swing.JFrame;

public class MainMS {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("SuperExplosivo");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(250, 50);
		myFrame.setSize(400, 400);

		
		MyPanelMS myPanel = new MyPanelMS();
		myFrame.add(myPanel);

		MyMouseAdapterMS myMouseAdapter = new MyMouseAdapterMS();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		
		SuperExplosivo.SetMine();
	}
}