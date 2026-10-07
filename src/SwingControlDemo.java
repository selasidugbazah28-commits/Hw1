import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import java.awt.event.KeyListener;

public class SwingControlDemo implements ActionListener, KeyListener{
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JTextField inputField;
    private JTextArea outputArea;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private int WIDTH=800;
    private int HEIGHT=700;


    public SwingControlDemo() {
        prepareGUI();
    } // Prepare method

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo(); // Declare object
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout()); // makes boxes in the frame that are the exact same dimensions

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        inputField = new JTextField();
        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel inputPanel = new JPanel(new BorderLayout(5,5));
        inputPanel.add(new JLabel("Input: "), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
       // controlPanel = new JPanel();
      //  controlPanel.setLayout(new BorderLayout()); //set the layout of the pannel

        JPanel topPanel = new JPanel(new GridLayout(2,1));
        topPanel.add(inputPanel);
       topPanel.add(controlPanel);
        mainFrame.add(topPanel, BorderLayout.NORTH);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        //ta = new JTextArea(); //use it starting with a J, not without it
        //ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);
       // mainFrame.add(mb);  //add menu bar
       // mainFrame.add(ta);//add typing area
       // mainFrame.setJMenuBar(mb); //set menu bar



       statusLabel = new JLabel("", JLabel.CENTER); //after you click a button, an untypeable set of letters show up
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });


        //mainFrame.add(controlPanel);
       // mainFrame.add(statusLabel);
      //  mainFrame.setVisible(true);
    }

    private void showEventDemo() {


        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainFrame.add(scrollPane, BorderLayout.CENTER);
        outputArea.addKeyListener(this);
       // scrollPane.setVerticalScrollBar();
        JButton resetButton = new JButton("Reset");
        JButton submitButton = new JButton("Submit");
        JButton lowercaseButton = new JButton("Lowercase");


        resetButton.setActionCommand("Reset");
        submitButton.setActionCommand("Submit");
        lowercaseButton.setActionCommand("Lowercase");



        resetButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        lowercaseButton.addActionListener(new ButtonClickListener());


        controlPanel.add(resetButton);
        controlPanel.add(submitButton);
        controlPanel.add(lowercaseButton);


        mainFrame.setVisible(true);


        mainFrame.setBackground(new Color(64, 163, 69));// Main frame Background helped by Will
        outputArea.setBackground(new Color(114, 172, 114));
        inputField.setBackground(new Color(175, 218, 177));
        controlPanel.setBackground(new Color(175, 218, 177));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 10) {

            outputArea.setText(inputField.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == 10) {
            outputArea.setText(inputField.getText());

    }

    }


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Submit")) {// Sefa helped in Said code
                outputArea.setText(inputField.getText());
            } else if (command.equals("Reset")) {
                inputField.setText("");
                outputArea.setText("");

            } else if (command.equals("Lowercase")) {
                outputArea.setText(inputField.getText().toLowerCase());

            }
        }
    }
}
