package testes;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import br.com.backup.BackupDao;
import br.com.exception.DaoException;

public class FileChooser extends JFrame
                             implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    
    JFileChooser chooser = new JFileChooser();
       
    public FileChooser() {
      //  super(new BorderLayout());
    	super("Gerar Backup");
    	setBounds(100, 100, 450, 300);		
    	
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Open a File...",
                                 createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...",
                                 createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

    	String caminho;
    	BackupDao bkpDao = new BackupDao();
    	
        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooser.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	
//            	caminho = fc.getSelectedFile().getAbsolutePath();
            	caminho = fc.getSelectedFile().getAbsolutePath().replace("\\" , "\\\\" );                                
//                JOptionPane.showMessageDialog(null, caminho); //se tá modificando
                
            	if(caminho.contains("C:")){
            		JOptionPane.showMessageDialog(null, "Por gentileza salvar em outro disco!");
            	}
            	
            	else{
            	
            	
            		if(caminho.contains(".bak")){
            		try {
						bkpDao.gerarBackupNovo(caminho);
					} catch (DaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	
            		}else{
            			String file;
            			file = caminho + ".bak";
            			try {
            				bkpDao.gerarBackupNovo(file);
            			} catch (DaoException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            		}
            		
                
            	
                File file = fc.getSelectedFile();
                file = new File(file+".bak");                 
//                
//                backup.setFileBackup(caminho);
//                JOptionPane.showMessageDialog(null, caminho);
//                
//                
//                try {
//					bkpDao.gerarBackupNovo(caminho);
//				} catch (DaoException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
                
                //This is where a real application would save the file.
                log.append("Salvando: " + file.getName() + "." + newline);
            	}
            	} else {
                log.append("Opção de salvar cancelada." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    @SuppressWarnings("unused")
	private static void createAndShowGUI() {
        //Create and set up the window.
//        JFrame frame = new JFrame("FileChooserDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        FileChooser frame = new FileChooser();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new FileChooser());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	FileChooser frame = new FileChooser();            	
//            	frame.pack();
            	frame.setVisible(true);
				
		       
                //Turn off metal's use of bold fonts
//				UIManager.put("swing.boldMetal", Boolean.FALSE);                 
//                createAndShowGUI();
            }
        });
    }
    
    public void gerarBackup() {
		BackupDao bkpDao = new BackupDao();
		boolean sucesso = false;
		try {
			bkpDao.gerarBackup(sucesso);					
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
