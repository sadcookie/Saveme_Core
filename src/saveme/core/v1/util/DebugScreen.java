package saveme.core.v1.util;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextArea;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

/**
 * Saveme debug Screen
 */
public class DebugScreen {
	private static DebugScreen ds;
	private JFrame frame;
	private JButton exitButton;
	private JTextArea textToPrint;
	private Vector<String> warningMessage;

	/**
	 * Warning : class cannot create instance. The instance can be call through {@link getDebugScreen()}.
	 * order to get instance, use {@code getDebugScreen()}
	 */
	private DebugScreen() {
		
		frame = new JFrame();
		exitButton = new JButton("Exit");
		textToPrint = new JTextArea();
		warningMessage = new Vector<>(1,1);
		
		// An Example size of the frame.
		// Window frame has to be adjustable.
		exitButton.setBounds(130, 100, 100, 40);
		
		textToPrint.setEditable(false);
		
		
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
		getDebugScreen().warningMessage.addElement(warningMessage+"\n");
	}
	
	/**
	 * clear the warning messages.
	 */
	private static void resetWarningMessage(){
		getDebugScreen().warningMessage.clear();
		getDebugScreen().warningMessage.add("Warnings: \n");
	}
	
	/**
	 * @return  All warningMessage
	 */
	private static String messageToString(){
		String result=null;
		for (String a: getDebugScreen().warningMessage ){
			result = new StringBuilder().append(a).toString();
		}
		return result;
	}
	
	/**
	 * load a message on debugging screen. If a message displayed, 
	 */
	public static void showMessage() {
		getDebugScreen().textToPrint.append(messageToString());
		getDebugScreen().frame.add(new JScrollPane(getDebugScreen().textToPrint, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		// Clear the screen and reload.
		getDebugScreen().frame.invalidate();
		getDebugScreen().frame.validate();
		getDebugScreen().frame.repaint();
		resetWarningMessage();
	}
}
