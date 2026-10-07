import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingControlDemo implements ActionListener {
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
       // scrollPane.setVerticalScrollBar();
        JButton resetButton = new JButton("Reset");
        JButton submitButton = new JButton("Submit");


        resetButton.setActionCommand("Reset");
        submitButton.setActionCommand("Submit");



        resetButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());


        controlPanel.add(resetButton);
        controlPanel.add(submitButton);


        mainFrame.setVisible(true);
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

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Submit")) {
                outputArea.setText(inputField.getText());
            } else if (command.equals("Reset")) {
                inputField.setText("");
                outputArea.setText("");

            }
        }
    }
}
