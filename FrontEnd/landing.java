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
    JButton menuButton;

    JPanel menuHeaderPanelObj = new JPanel();
    JPanel packerHeaderPanelObj = new JPanel();
    JPanel menuPanelObj = new JPanel();
    JPanel packerPanelObj = new JPanel();
    JPanel menuPanelPack,menuPanelUnpack,menuPanelRecents,menuPanelSettings;

    private ImageIcon menuIcon, menuPackIcon,menuUnpackIcon, recentsIcon, settingsIcon;

    JLabel menuHeaderLabel;
    Font font;

    // Buttons
    JButton menuPackIconButton, menuUnpackIconButton,menuRecentsIconButton,menuSettingsIconButton;

    private void addPanel() {
        // Menu Header Panel
        menuHeaderPanelObj.setBackground(new Color(227, 79, 67, 255));
        menuHeaderPanelObj.setBounds(0, 0, 350, 50);
        menuHeaderPanelObj.setLayout(null);
        add(menuHeaderPanelObj);

        // Packer Header Panel
        packerHeaderPanelObj.setBackground(new Color(186, 46, 73, 255));
        packerHeaderPanelObj.setBounds(350, 0, 400, 50);
        add(packerHeaderPanelObj);

        // Menu panel
        menuPanelObj.setBackground(new Color(202, 62, 87, 255));
        menuPanelObj.setBounds(0, 51, 350, 660);
        menuPanelObj.setLayout(null);
        add(menuPanelObj);

        // Packer panel
        packerPanelObj.setBackground(new Color(193, 53, 78, 255));
        packerPanelObj.setBounds(350, 50, 400, 660);
        add(packerPanelObj);

        addMenuPanel();
    }

    private void addMenuPanel() {
        // JPanel menuPanelPack = new JPanel();
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
        menuHeaderLabel = new JLabel("MENU");
        menuHeaderLabel.setBounds(10, 0, 200, 50);
        menuHeaderLabel.setFont(font.deriveFont(Font.BOLD, 25f));
        menuHeaderLabel.setForeground(Color.WHITE);
        menuHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        menuHeaderPanelObj.add(menuHeaderLabel);

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
                    setSize(700,660);
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

class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;
    }

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draws the rounded panel with borders.
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        // graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
    }
}
