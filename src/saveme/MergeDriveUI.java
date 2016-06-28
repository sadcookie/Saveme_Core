/*
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.event.*;
import java.awt.*;

public class MergeDriveUI extends JFrame  {
	protected static String title = "File Explorer";
	protected JFileChooser fileChooser;
	protected JTree jtree;
	
	MergeDriveUI(){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		setSize(600,600);
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException{
		MergeDriveUI mdui = new MergeDriveUI();
	}
	
	

}
*/
/*
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MergeDriveUI();
            }
        });
    }       
    */

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.dropbox.core.v2.files.Metadata;
import com.google.api.services.drive.model.File;

public class MergeDriveUI extends JFrame {

	private String g = "GoogleDrive";
	private String d = "Dropbox";
	private String b = "Box";
	private DefaultMutableTreeNode GoogleDrive = new DefaultMutableTreeNode(g);
	private DefaultMutableTreeNode Dropbox = new DefaultMutableTreeNode(d);
	private DefaultMutableTreeNode Box = new DefaultMutableTreeNode(b);

	private JTree tree1;
	private JTree tree2;
	private JTree tree3;

	public MergeDriveUI() {

	}

	public void setTreeNode() {
		tree1 = new JTree(GoogleDrive);
		tree2 = new JTree(Dropbox);
		tree3 = new JTree(Box);
		this.add(tree1);
		this.add(tree2);
		this.add(tree3);
	}

	public void getGoogleDriveFile(List<File> files) {
			for (File file : files) {
				GoogleDrive.add(new DefaultMutableTreeNode(file.getTitle()));
			}
	}
	public void getDropboxFile(List<Metadata> files) {
			for (Metadata file : files) {
				Dropbox.add(new DefaultMutableTreeNode(file.getName()));
			}
	}
	private static final int MAX_DEPTH = 1;
	
	public void getBoxFile(BoxFolder folder, int depth) {
	        for (BoxItem.Info itemInfo : folder) {
	            for (int i = 0; i < depth; i++) {
	            }

	            Box.add(new DefaultMutableTreeNode(itemInfo.getName()));
	            if (itemInfo instanceof BoxFolder.Info) {
	                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
	                if (depth < MAX_DEPTH) {
	                    getBoxFile(childFolder, depth + 1);
	                }
	            }
	        
			}
	}

	public void setLayout() {
		this.setLayout(new VerticalLayout(2, 2));
	}

	public DefaultMutableTreeNode addtreenode(String filename) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(filename);
		return node;
	}

	public void setView() {
		setLayout();
		setTreeNode();
		this.setContentPane(getContentPane());
		this.setTitle("Drive File Explorer");
		this.setSize(300, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}

/*
 * import java.awt.FlowLayout;
 * 
 * import javax.swing.BoxLayout; import javax.swing.JCheckBox; import
 * javax.swing.JFrame; import javax.swing.JPanel; import javax.swing.JTree;
 * import javax.swing.SwingUtilities; import javax.swing.UIManager; import
 * javax.swing.WindowConstants; import javax.swing.tree.DefaultMutableTreeNode;
 * 
 * public class MergeDriveUI extends JFrame {
 * 
 * private JTree tree1; private JTree tree2; private JTree tree3; private JPanel
 * jContentPane = null;
 * 
 * public MergeDriveUI() { super(); initialize(); }
 * 
 * private void initialize() { this.setSize(300, 500);
 * this.setContentPane(getJContentPane()); this.setTitle("JFrame"); }
 * 
 * private JPanel getJContentPane() { if (jContentPane == null) { jContentPane =
 * new JPanel(); jContentPane.setLayout(null);
 * 
 * JPanel panel = new JPanel();
 * 
 * panel.setLayout(new VerticalLayout());
 * 
 * jContentPane.add(panel);
 * 
 * DefaultMutableTreeNode GoogleDrive = new DefaultMutableTreeNode(
 * "Google Drive"); DefaultMutableTreeNode Dropbox = new
 * DefaultMutableTreeNode("Dropbox"); DefaultMutableTreeNode Box = new
 * DefaultMutableTreeNode("Box");
 * 
 * this.setLayout(new VerticalLayout(2, 2)); tree1 = new JTree(GoogleDrive);
 * tree2 = new JTree(Dropbox); tree3 = new JTree(Box); panel.add(tree1);
 * panel.add(tree2); panel.add(tree3); } return jContentPane; }
 * 
 * public static void main(String[] args) throws Exception { MergeDriveUI frame
 * = new MergeDriveUI(); frame.setVisible(true);
 * frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); } }
 */
