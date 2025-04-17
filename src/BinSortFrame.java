import javax.swing.*;
import java.awt.*;

public class BinSortFrame extends JFrame {

    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JPanel centerNorthPanel;

    JPanel southButtonsPanel;
    JPanel southInputPanel;

    JPanel centerTAPanel;
    JPanel centerFileBtnPanel;

    JLabel placeholderLabel;

    JLabel minCountLabel;

    JButton goButton;
    JButton quitButton;

    JButton removeLastValueButton;
    JButton addOutputFileButton;

    JTextArea listTextArea;
    JScrollPane listScrollPane;

    JTextArea logTextArea;
    JScrollPane logScrollPane;

    JTextField addTermTF;
    
    String listString = "";
    String logString = "";
    BinSortArrayList binSortArrayList = new BinSortArrayList();


    public BinSortFrame() {
        super("File Stream Searcher");

        setLayout(new BorderLayout());

        // Add panels to their respective positions
        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null); // Center on screen
    }

    private JPanel createNorthPanel() {
        northPanel = new JPanel();
        placeholderLabel = new JLabel("BinSortArrayList tester GUI V0.0.1", SwingConstants.CENTER);
        northPanel.add(placeholderLabel);
        return northPanel;
    }

    private JPanel createCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());


        centerNorthPanel = new JPanel(new GridLayout(2, 1));

        listTextArea = new JTextArea();
        listTextArea.setEditable(false);
        listTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        listScrollPane = new JScrollPane(listTextArea);

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        logScrollPane = new JScrollPane(logTextArea);

        centerTAPanel = new JPanel(new GridLayout(1, 2));
        centerTAPanel.add(listScrollPane);
        centerTAPanel.add(logScrollPane);



        centerFileBtnPanel = new JPanel();
        centerFileBtnPanel.setLayout(new GridLayout(1, 1));

        removeLastValueButton = new JButton("Remove Last Value");
        removeLastValueButton.addActionListener(e -> {
            System.out.println("Remove Last Value");
            binSortArrayList.deleteLastAdded();
            listString = "";
            for (String s : binSortArrayList.toList()) {
                listString += s + "\n";
            }
            listTextArea.setText(listString);
            logString += binSortArrayList.getLog();
            logTextArea.setText(logString);
        });
        centerFileBtnPanel.add(removeLastValueButton);



        centerPanel.add(centerFileBtnPanel, BorderLayout.SOUTH);
        centerPanel.add(centerTAPanel, BorderLayout.CENTER);
        centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
        return centerPanel;
    }

    private JPanel createSouthPanel() {
        southPanel = new JPanel(new GridLayout(1, 2));

        goButton = new JButton("Search");
        quitButton = new JButton("Quit");
        addOutputFileButton = new JButton("Output to File");
        minCountLabel = new JLabel("Search Term:");
        addTermTF = new JTextField(35);
        addTermTF.setText("Hello World!");

        southButtonsPanel = new JPanel(new GridLayout(1, 2));
        southInputPanel = new JPanel(new GridLayout(2, 1));


        goButton.addActionListener(e -> {
            System.out.println("Go Button");
            String addTerm = addTermTF.getText();
            binSortArrayList.add(addTerm);
            listString = "";
            for (String s : binSortArrayList.toList()) {
                listString += s + "\n";
            }
            listTextArea.setText(listString);
            logString += binSortArrayList.getLog();
            logTextArea.setText(logString);
        });

        quitButton.addActionListener(e -> System.exit(0));


        southInputPanel.add(minCountLabel);
        southInputPanel.add(addTermTF);
        southButtonsPanel.add(goButton);
        southButtonsPanel.add(quitButton);

        southPanel.add(southInputPanel);
        southPanel.add(southButtonsPanel);

        return southPanel;
    }

}