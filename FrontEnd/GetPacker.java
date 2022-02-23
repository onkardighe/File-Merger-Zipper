import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import javax.swing.filechooser.*;


// Getting packer source folder and destination
public class GetPacker extends JPanel
{
    JFrame mainFrameObj;
    JPanel panelObj, viewerPanelObj, buttonPanel;
    static RoundedPanel proceedButtonPanel;
    JFileChooser fileChooser, destChooser;
    File sourceFolder;
    static File destFolder;
    static File currentFile;
    File[] innerFiles, selectedFiles;
    Font font;
    JButton packerSelectFolderButton,packerChangeFolderButton,packerSelectDestButton;
    static JButton proceedButton = new JButton("PROCEED");
    static JButton currentFileButton, selectFileButton, removeFileIconButton;
    JButton[] buttonList;   // Array of object of buttons
    JTextPane dataInFile = new JTextPane();
    JScrollPane scroll;
    private static JTextField folderName = null;
    private static JButton destinationPath;


    String data;
    String folder = "";

    Selection selectionObj = new Selection();         // Object of Selection class


    private void addFolderPath(String folderPath)
    {
        if(folderName == null)
        {
            folderName = new JTextField();
        }
        folderName.setText("Path : "+folderPath);
        folderName.setBounds(30, 60, 240, 35);
        folderName.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
        folderName.setFont(font.deriveFont(Font.BOLD,16f));
        folderName.setForeground(Color.white);
        folderName.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        folderName.setBackground(null);
        panelObj.add(folderName);
    }

    private void addChangeFolderButton()
    {
        ImageIcon folderIcon = new ImageIcon("assets/folderIcon.png");
        Image menuImg = folderIcon.getImage();
        menuImg = menuImg.getScaledInstance(35, 30, java.awt.Image.SCALE_SMOOTH);
        folderIcon = new ImageIcon(menuImg);
        packerChangeFolderButton = addIconButtons(folderIcon);
        packerChangeFolderButton.setBounds(280, 55, 40, 40);
        panelObj.add(packerChangeFolderButton);

        // Menu Select Folder Button (ICON)
        packerChangeFolderButton.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    getSource();
                }
            }
        );
    }

    // Function - parameter : ImageIcon
                // returns object of JButton
    private JButton addIconButtons(ImageIcon icon) {
        JButton newButton = new JButton(icon);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.setFocusPainted(false);
        newButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return newButton;
    }

    public void addInitialButtons()
    {
        packerSelectFolderButton = new JButton();
        packerSelectFolderButton.setText("Select Folder");
        packerSelectFolderButton.setFont(font.deriveFont(Font.BOLD, 20f));
        packerSelectFolderButton.setForeground(Color.RED);
        packerSelectFolderButton.setBounds(55, 200, 180, 60);
        packerSelectFolderButton.setHorizontalAlignment(SwingConstants.CENTER); 
        packerSelectFolderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(packerSelectFolderButton);


        // Function to add button Actions
        addClickActions();
    }
    private void addClickActions()
    {
        // Menu Select Folder Button
        packerSelectFolderButton.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    packerSelectFolderButton.setVisible(false);
                    getSource();
                }
            }
        );
    }

    private JFileChooser openFileChooser(String name)
    {
        JFileChooser fC =  new JFileChooser(FileSystemView.getFileSystemView());
        fC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fC.setDialogTitle(name);
        fC.setApproveButtonText(name);
        return fC;
    }

    private void getSource()
    {
        fileChooser = openFileChooser("Select Folder");
        int r = fileChooser.showOpenDialog(null);


        if (r == JFileChooser.APPROVE_OPTION)
        {
            // print the path of the selected file
            sourceFolder = fileChooser.getSelectedFile();
            addFileList(sourceFolder);

            addFolderPath(sourceFolder.getAbsolutePath());
            addChangeFolderButton();

            if(scroll != null)
            {
                mainFrameObj.remove(scroll);
            }
            addPreviewFile();
            
            dataInFile.setText("Select a file !!");
            dataInFile.setEditable(false);

            if(selectFileButton!= null)
            {
                selectFileButton.setText("Select this File");
                selectButtonState(selectFileButton, false);
            }

        }
        // if the user cancelled the operation
        else
        {
            // path is absent
            if((folderName == null))
            {
                packerSelectFolderButton.setVisible(true);
            }
        }
    }
    private void removePreviousButtonList()
    {
        if(buttonList != null)
        {
            for(JButton button : buttonList)
            {
                if(button != null)
                {
                    button.setVisible(false);
                    button = null;
                }
            }
        }

    }

    // Adding fileList using JTable on JPanel
    private void addFileList(File f)
    {   
        removePreviousButtonList();

        innerFiles = f.listFiles();  

        if(innerFiles.length > 10)
        {
            buttonList = new JButton[10];
         
        }
        else
        {
            buttonList = new JButton[innerFiles.length];
        }
        
        int i = 0;  
        for (File file : innerFiles) 
        {
            if(i > 9)
            {
                break;
            }

            if(file.isFile())
            {
                JButton fButton = addFileButton(file, i);
                buttonList[i] = fButton;
                i++;
            }    
        }

        addButtonList();
    }

    private void addButtonList()
    {

        for(JButton button : buttonList)
        {
            if(button != null)
            {
                this.add(button);
            }
        }
    }


    private JButton addFileButton(File f, int index)
    {

        JButton fileButton = new JButton();

        String name = f.getName();
        if(name.length() >= 29)
        {
            name = name.substring(0,29)+"...";
        }
        fileButton.setText(name);

        fileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileButton.setBounds(5, 5+index*45, 280, 42);
        fileButton.setFont(font.deriveFont(Font.BOLD,15f));
        fileButton.setForeground(Color.red);

        // if file already selected
        if(selectionObj.selectedFiles.contains(f))
        {
            removeFileIconButton.setVisible(true);
            selectFileButton.setText("Selected");
            selectButtonState(fileButton, true);
            fileButton.setForeground(Color.white);
        }

        fileButton.addKeyListener(

            new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e) 
                {

                }

                @Override
                public void keyReleased(KeyEvent e) 
                {

                }
                @Override
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if(!(selectionObj.selectedFiles.contains(currentFile)))
                        {
                            selectionObj.select(currentFile);
                            selectButtonState(selectFileButton, true);
                            selectFileButton.setText("Selected");
                            selectButtonState(fileButton, true);
                            fileButton.setForeground(Color.white);
    
                            removeFileIcon();
                        }
                    }
                }
            }
        );


        fileButton.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    mainFrameObj.setSize(1050, 660);
                    mainFrameObj.setLocationRelativeTo(null);
                    getFileData(f);
                    previewFileData();
                    addSelectButton(f, fileButton);
                    currentFile = f;
                    currentFileButton = fileButton;
                    


                    if(selectionObj.selectedFiles.contains(f))
                    {
                        selectFileButton.setBounds(50,565, 210, 50);
                        panelObj.add(removeFileIconButton);
                        checkFileContains();
                    }
                    else
                    {
                        if(removeFileIconButton != null)
                        {
                            panelObj.remove(removeFileIconButton);
                        }
                    }

                }
            }
        );
        return fileButton;
    } 

    private void addSelectButton(File file, JButton fileButton)
    {
        if(selectFileButton != null)
        {
            selectFileButton.setVisible(false);
        }
        
        
        selectFileButton = new JButton("Select this File");
        // File Already Selected
        if(selectionObj.selectedFiles.contains(file))
        {
            selectFileButton.setText("Selected");
            selectButtonState(selectFileButton, true);
        }
        else
        {
            selectFileButton.setText("Select this File");
            selectButtonState(selectFileButton, false);
        }

        selectFileButton.setFont(font.deriveFont(Font.BOLD, 20f));
        selectFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectFileButton.setBounds(70,565, 210, 50);
        panelObj.add(selectFileButton);



        selectFileButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(!(selectionObj.selectedFiles.contains(file)))
                    {
                        selectionObj.select(file);
                        selectButtonState(selectFileButton, true);
                        selectFileButton.setText("Selected");
                        selectButtonState(fileButton, true);
                        fileButton.setForeground(Color.white);

                        checkFileContains();

                        removeFileIcon();
                    }
                }
            }
        );
    }

    public void addProceedBbutton()
    {

        proceedButtonPanel = new RoundedPanel(10,new Color(213, 134, 145, 123));
        proceedButtonPanel.setBounds(40,600, 270, 50);
        proceedButtonPanel.setOpaque(false);
        proceedButtonPanel.setLayout(null);
        
        proceedButton.setBounds(0,0, 270, 50);
        proceedButton.setFont(font.deriveFont(Font.BOLD, 20f));
        proceedButton.setForeground(Color.white);
        proceedButton.setOpaque(false);
        proceedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        proceedButton.setBorder(BorderFactory.createEmptyBorder());
        proceedButtonPanel.add(proceedButton);
        viewerPanelObj.add(proceedButtonPanel);
        proceedButtonPanel.setVisible(false);

        proceedButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    if(destFolder == null)
                    {
                        String[] opt = {"Select Now", "Select Later"};
                        int n = JOptionPane.showOptionDialog(mainFrameObj, "Select Destination First !!", "Select Destination First !!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, opt, opt[0]);
                        
                        if(n == 0)
                        {
                            addDestFileChooser();
                            selectionObj.proceedToPack(destFolder);
                            JOptionPane.showMessageDialog(mainFrameObj, "Successfully Done !!", "Successful", JOptionPane.PLAIN_MESSAGE, null);
                            openOutputFolder(destFolder);
                        }
                    }
                    else
                    {
                        selectionObj.proceedToPack(destFolder);
                        JOptionPane.showMessageDialog(mainFrameObj, "Successfully Done !!", "Successful", JOptionPane.PLAIN_MESSAGE, null);
                        openOutputFolder(destFolder);
                    }
                };
            }
        );
    }

    private void openOutputFolder(File f)
    {
        try 
        {
            if(Desktop.isDesktopSupported()) 
            {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(f);
            }
            else 
            {
                System.out.println("desktop is not supported");
            }
        }
        catch(IOException e)
        { 
            System.out.println(e);
        } 
    }

    public void getDestination()
    {
        if(destinationPath == null)
        {
            destinationPath = new JButton("Select Destination");
            destinationPath.setBounds(40, 560, 270, 35);
            destinationPath.setHorizontalAlignment(SwingConstants.CENTER);
            destinationPath.setCursor(new Cursor(Cursor.HAND_CURSOR));
            destinationPath.setFont(font.deriveFont(Font.BOLD,16f));
            destinationPath.setForeground(Color.red);
            destinationPath.setBackground(null);
            viewerPanelObj.add(destinationPath);
        }

        if(destFolder == null)
        {
            destinationPath.setText("Select Destination");
        }

        destinationPath.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    addDestFileChooser();
                }
            }
        );
    }


    // opens new chooser window to choose a folder 
    // for destination
    private void addDestFileChooser()
    {
        destChooser = openFileChooser("Select Destination");

        int r = destChooser.showOpenDialog(null);


        if (r == JFileChooser.APPROVE_OPTION)
        {
            destFolder = destChooser.getSelectedFile();
            destinationPath.setText("DEST : "+destFolder.getAbsolutePath());
            destinationPath.setForeground(Color.WHITE);
            destinationPath.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private void checkFileContains()
    {
        if(selectionObj.selectedFiles.size() >= 1)
        {
            proceedButtonPanel.setVisible(true);            
        }
        else
        {
            proceedButtonPanel.setVisible(false);

        }
    }

    private void removeFileIcon()
    {
        selectFileButton.setBounds(50,565, 210, 50);

        ImageIcon trashIcon = new ImageIcon("assets/trashIcon.png");
        Image trashImg = trashIcon.getImage();
        trashImg = trashImg.getScaledInstance(30, 40, java.awt.Image.SCALE_SMOOTH);
        trashIcon = new ImageIcon(trashImg);
        removeFileIconButton = addIconButtons(trashIcon);
        removeFileIconButton.setBounds(270, 565, 40, 55);
        panelObj.add(removeFileIconButton);

        removeFileIconButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // remove file from selection
                    selectionObj.remove(currentFile);

                    panelObj.remove(removeFileIconButton);
                    selectFileButton.setText("Select this File");
                    selectButtonState(selectFileButton, false);
                    selectFileButton.setBounds(70,565, 210, 50);

                    // refresh current selection list
                    removePreviousButtonList();
                    addFileList(sourceFolder);
                    addButtonList();
                    checkFileContains();
                }
            }
        );   
    }


    private void selectButtonState(JButton button,  boolean s)
    {
        if(s)
        {
            button.setBorder(new LineBorder(Color.GREEN));
            button.setForeground(Color.green);
        }
        else
        {
            button.setBorder(new LineBorder(Color.white));
            button.setForeground(Color.white);
        }
    }

    private void previewFileData()
    {
        if(scroll != null)
        {
            mainFrameObj.remove(scroll);
        }
        addPreviewFile();
        
        dataInFile.setText(data);
        dataInFile.setEditable(false);
    }

    private void getFileData(File f) 
    {
        data = "  ";
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                data = data+"\n"+"  "+line;
            }
            dataInFile.setText(data+"\n");
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } 
        catch(IOException e)
        {
            System.out.println(e);
        }

    }

    private void getFonts() 
    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("assets/fonts/quicksand.ttf"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    ////////////////Viewer Code/////////////
    public void addPreviewFile()
    {
        dataInFile.setBounds(0, 0, 330, 490);
        dataInFile.setFont(font.deriveFont(12f));
        dataInFile.setForeground(Color.white);
        dataInFile.setBorder(BorderFactory.createEmptyBorder());
        dataInFile.setBackground(new Color(193, 53, 78, 255));
        

        scroll = new JScrollPane (dataInFile, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(710, 60, 330, 490);
        scroll.setBackground(new Color(193, 53, 78, 255));
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(4,0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0,4));

        
        mainFrameObj.add(scroll);
    }
    
    // Constructor
    GetPacker(JFrame mainFrame, JPanel packerPanelObj, JPanel viewerPanel) 
    {
        mainFrameObj = mainFrame;
        panelObj = packerPanelObj;
        viewerPanelObj = viewerPanel;
        setBounds(30,100,290,455);
        setLayout(null);
        getFonts(); 
    }
}


