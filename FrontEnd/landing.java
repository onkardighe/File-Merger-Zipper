import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class landing {

    public static void main(String[] args) throws Exception {

        new landingMain();
    }

}

class landingMain extends JFrame implements ActionListener
{
    Dimension onePanelDimension = new Dimension(350,660);
    Dimension twoPanelDimension = new Dimension(700,660);
    Dimension threePanelDimension = new Dimension(1050,660);
    Dimension currentPanelDimension;

    JButton menuButton;

    JPanel menuHeaderPanelObj = new JPanel();
    JPanel packerHeaderPanelObj = new JPanel();
    JPanel menuPanelObj = new JPanel();
    JPanel packerPanelObj;
    JPanel viewerPanelObj = new JPanel();
    JPanel viewerHeaderPanel;
    JPanel proceedButtonPanel;
    JPanel menuPanelPack,menuPanelUnpack,menuPanelRecents,menuPanelSettings;

    private ImageIcon menuIcon, menuPackIcon,menuUnpackIcon, recentsIcon, settingsIcon;

    JLabel menuHeaderLabel, packerHeaderLabel;
    Font font;

    // Buttons
    JButton menuPackIconButton, menuUnpackIconButton,menuRecentsIconButton,menuSettingsIconButton;

    String data;

    private void addPanel() {

        // Menu panel
        menuPanelObj.setBackground(new Color(202, 62, 87, 255));
        menuPanelObj.setBounds(0, 50, 350, 660);
        menuPanelObj.setLayout(null);
        add(menuPanelObj);

        

        // Viewer panel
        viewerPanelObj.setBackground(new Color(202, 62, 87, 255));
        viewerPanelObj.setBounds(700, 0, 350, 710);
        viewerPanelObj.setLayout(null);

        // Menu Header Panel
        menuHeaderPanelObj.setBackground(new Color(186, 46, 73, 255));
        menuHeaderPanelObj.setBounds(0, 0, 350, 50);
        menuHeaderPanelObj.setLayout(null);
        add(menuHeaderPanelObj);

        //Function to add packer panel
        addPackerPanel();
        addMenuPanel();
        addViewPanel();
    }

    private void addPackerPanel()
    {

        packerPanelObj = new JPanel();
        // Packer Header Panel
        packerHeaderPanelObj.setBackground(new Color(186, 46, 73, 255));
        packerHeaderPanelObj.setBounds(0, 0, 350, 50);
        packerPanelObj.add(packerHeaderPanelObj);

        // Packer panel
        packerPanelObj.setBackground(new Color(193, 53, 78, 255));
        packerPanelObj.setBounds(350, 0, 350, 710);
        packerPanelObj.setLayout(null);

        // Created object of class GetPacker
        // Getting packer source folder and destination - Returns object of a class extening JPanel 
        GetPacker packfileObj = new GetPacker(this, packerPanelObj,viewerPanelObj);
        packfileObj.setBackground(new Color(213, 134, 145, 123));
        packfileObj.setLayout(null);

        // button -- Select Folder
        packfileObj.addInitialButtons();
        packerPanelObj.add(packfileObj);
        add(packerPanelObj);

    }
    
    private void addViewPanel()
    {
        add(viewerPanelObj);

        //header panel for view panel
        viewerHeaderPanel = new JPanel();
        viewerHeaderPanel.setBackground(new Color(186, 46, 73, 255));
        viewerHeaderPanel.setBounds(0, 0, 350, 50);

        viewerPanelObj.add(viewerHeaderPanel);

        addViewerPanel();
    }


    private void addViewerPanel() 
    {
        try {
            getFonts();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        // Viewer Panel to view files
        GetPacker viewerObj = new GetPacker(this, packerPanelObj,viewerPanelObj);
        viewerObj.setLayout(null);
        viewerObj.setVisible(false);

        viewerObj.getDestination();
        viewerObj.addProceedBbutton();

        viewerPanelObj.add(viewerObj);
    }



    private void addMenuPanel() {
        menuPanelPack = new RoundedPanel(10,new Color(213, 134, 145, 123));
        menuPanelUnpack = new RoundedPanel(10,new Color(213, 134, 145, 123));
        menuPanelRecents = new RoundedPanel(10,new Color(213, 134, 145, 123));
        menuPanelSettings = new RoundedPanel(10,new Color(213, 134, 145, 123));

        // menu pack button panel
        menuPanelPack.setBounds(30, 50, 290, 75);
        menuPanelPack.setOpaque(false);
        menuPanelPack.setLayout(null);
        menuPanelObj.add(menuPanelPack);

        // Menu unpack button panel
        menuPanelUnpack.setBounds(30, 175, 290, 75);
        menuPanelUnpack.setOpaque(false);
        menuPanelUnpack.setLayout(null);
        menuPanelObj.add(menuPanelUnpack);

        // Menu recents button panel
        menuPanelRecents.setBounds(30, 300, 290, 75);
        menuPanelRecents.setOpaque(false);
        menuPanelRecents.setLayout(null);
        menuPanelObj.add(menuPanelRecents);
        
        // Menu recents button panel
        menuPanelSettings.setBounds(30, 425, 290, 75);
        menuPanelSettings.setOpaque(false);
        menuPanelSettings.setLayout(null);
        menuPanelObj.add(menuPanelSettings);
    }

    private void addLabel() throws Exception {
        getFonts();
        menuHeaderLabel = new JLabel("FILE ZIPPER");
        menuHeaderLabel.setBounds(80, 0, 200, 50);
        menuHeaderLabel.setFont(font.deriveFont(Font.BOLD, 25f));
        menuHeaderLabel.setForeground(Color.WHITE);
        menuHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        packerHeaderLabel = new JLabel("Select Files");
        packerHeaderLabel.setBounds(80, 0, 200, 50);
        packerHeaderLabel.setFont(font.deriveFont(Font.BOLD, 25f));
        packerHeaderLabel.setForeground(Color.WHITE);
        packerHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        packerHeaderPanelObj.setLayout(null);

        menuHeaderPanelObj.add(menuHeaderLabel);
        packerHeaderPanelObj.add(packerHeaderLabel);

    }

    private void getFonts() throws Exception {
        font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("assets/fonts/quicksand.ttf"));
    }

    private void addIcons() {
        menuIcon = new ImageIcon("assets/menuIcon.png");
        Image menuImg = menuIcon.getImage();
        menuImg = menuImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        menuIcon = new ImageIcon(menuImg);
        menuButton = addIconButtons(menuIcon);
        menuButton.setBounds(20, 5, 40, 40);
        menuHeaderPanelObj.add(menuButton);

        //Pack menu button
        menuPackIcon = new ImageIcon("assets/packIcon.png");
        Image menupackImg = menuPackIcon.getImage();
        menupackImg = menupackImg.getScaledInstance(60,60, java.awt.Image.SCALE_SMOOTH);
        menuPackIcon = new ImageIcon(menupackImg);
        menuPackIconButton = addIconButtons(menuPackIcon);
        menuPackIconButton.setText("     Pack");
        menuPackIconButton.setFont(font.deriveFont(Font.BOLD, 25f));
        menuPackIconButton.setForeground(Color.WHITE);
        menuPackIconButton.setBounds(0, 0, 290, 80);
        menuPackIconButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuPanelPack.add(menuPackIconButton);

        // Unpack menu button
        menuUnpackIcon = new ImageIcon("assets/unpackIcon.png");
        Image menuUnpackImg = menuUnpackIcon.getImage();
        menuUnpackImg = menuUnpackImg.getScaledInstance(60,60, java.awt.Image.SCALE_SMOOTH);
        menuUnpackIcon = new ImageIcon(menuUnpackImg);
        menuUnpackIconButton = addIconButtons(menuUnpackIcon);
        menuUnpackIconButton.setText("     Unpack");
        menuUnpackIconButton.setFont(font.deriveFont(Font.BOLD, 25f));
        menuUnpackIconButton.setForeground(Color.WHITE);
        menuUnpackIconButton.setBounds(0, 0, 290, 80);
        menuUnpackIconButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuPanelUnpack.add(menuUnpackIconButton);
        
        // recents menu Button
        recentsIcon = new ImageIcon("assets/recentsIcon.png");
        Image menuRecentsImg = recentsIcon.getImage();
        menuRecentsImg = menuRecentsImg.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
        recentsIcon = new ImageIcon(menuRecentsImg);
        menuRecentsIconButton = addIconButtons(recentsIcon);
        menuRecentsIconButton.setText("     Recents");
        menuRecentsIconButton.setFont(font.deriveFont(Font.BOLD, 25f));
        menuRecentsIconButton.setForeground(Color.WHITE);
        menuRecentsIconButton.setBounds(5, 0, 290, 80);
        menuRecentsIconButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuPanelRecents.add(menuRecentsIconButton);

        // Settings menu Button
        settingsIcon =  new ImageIcon("assets/settingsIcon.png");
        Image menuSettingsImg = settingsIcon.getImage();
        menuSettingsImg = menuSettingsImg.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(menuSettingsImg);
        menuSettingsIconButton = addIconButtons(settingsIcon);
        menuSettingsIconButton.setText("     Settings");
        menuSettingsIconButton.setFont(font.deriveFont(Font.BOLD, 25f));
        menuSettingsIconButton.setForeground(Color.WHITE);
        menuSettingsIconButton.setBounds(5, 0, 290, 80);
        menuSettingsIconButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuPanelSettings.add(menuSettingsIconButton);

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

    private void addButtonActions()
    {
        // Menu pack Icon Button
        menuPackIconButton.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {

                    currentPanelDimension = getSize();
                    if((currentPanelDimension.width != onePanelDimension.width) || (currentPanelDimension.height != onePanelDimension.height))
                    {
                        setSize(350,660);
                    }
                    else
                    {
                        setSize(700,660);
                    }
                    setLocationRelativeTo(null);
                }
            }
        );

        // Menu Unpack Icon Button
        menuUnpackIconButton.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    setSize(700,660);
                    setLocationRelativeTo(null);
                }
            }
        );

        // Menu Recents Icon Button
        menuRecentsIconButton.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    setSize(700,660);
                    setLocationRelativeTo(null);
                }
            }
        );

        // Menu Settings Icon Button
        menuSettingsIconButton.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    setSize(700,660);
                    setLocationRelativeTo(null);
                }
            }
        );

    }

    landingMain() throws Exception {
        super("Packer");
        setResizable(false);
        setSize(350, 660);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().setBackground(new Color(222, 49, 99));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addPanel();
        addLabel();
        addIcons();
        addButtonActions();

        // Creating instance of Menu Class
        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){}
}