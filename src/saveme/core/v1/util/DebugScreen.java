package saveme.core.v1.util;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

/**
 * Saveme debug Screen
 * @author KwangEun An
 * 
 */
public class DebugScreen {
	private boolean debugEnable; //set debuger enable
	private static DebugScreen ds;
	private static JFrame frame;
	private static JButton exitButton;
	private static JTextArea textToPrint;
	private String warningMessage;
	private int numberOfStockedWarning;

	/**
	 * Warning : class cannot instantiate. It has only one single instance. In
	 * order to get instance, use {@code getDebugScreen()}
	 */
	private DebugScreen() {
		DebugScreen.frame = new JFrame();
		DebugScreen.exitButton = new JButton("Exit");
		DebugScreen.textToPrint = new JTextArea();
		exitButton.setBounds(130, 100, 100, 40);

		frame.add(exitButton);
		frame.setSize(400, 600);
		frame.setLayout(null);
		frame.setResizable(true);
		frame.setVisible(true);
	}
	
	/**
	 * Lazy loading
	 * @return Stocked instance. if the instance is empty, it will create new instance otherwise it will return that already created. 
	 */
	public static DebugScreen getDebugScreen() {
		if (ds == null) {
			ds = new DebugScreen();
			return ds;
		} else
			return ds;
	}
	/**
	 * 
	 * @param warningMessage  
	 */
	public static void setWarningMessage(String warningMessage) {
		DebugScreen.getDebugScreen().warningMessage = new StringBuffer()
				.append(DebugScreen.getDebugScreen().warningMessage).append(DebugScreen.getDebugScreen().numberOfStockedWarning+": ")
				.append(warningMessage).toString();
		DebugScreen.getDebugScreen().numberOfStockedWarning ++; // 
	}
	
	private static void resetWarningMessage(){
		DebugScreen.getDebugScreen().warningMessage = "Warnings: \n";
		DebugScreen.getDebugScreen().numberOfStockedWarning = 0;
	}
	/**
	 * load a message on debugging screen. If a message displayed, 
	 */
	public static void showMessage() {
		frame.add(new JScrollPane(textToPrint, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		frame.invalidate();
		frame.validate();
		frame.repaint();
		resetWarningMessage();
	}
}
