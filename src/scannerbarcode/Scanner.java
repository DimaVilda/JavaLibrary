package scannerbarcode;

import javax.swing.JDialog;

/**
 *
 * @author alexej
 */
public class Scanner {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ScanPanel sp = new ScanPanel();
		JDialog dialog = new JDialog();
		dialog.setSize(400, 300);
		dialog.add(sp);
		dialog.setVisible(true);
	}
	
}
