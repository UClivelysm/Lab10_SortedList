import javax.swing.*;
import java.awt.*;

public class BinSortFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // UI components for Add panel
    private JTextArea listTextArea;
    private JTextArea logTextArea;
    private JTextField addTermTF;

    // UI components for Search panel
    private JTextArea searchTextArea;
    private JTextArea searchLogTextArea;
    private JTextField searchInput;

    // Model
    private final BinSortArrayList binSortArrayList = new BinSortArrayList();
    private StringBuilder cumulativeLog = new StringBuilder();

    public BinSortFrame() {
        super("BinSortArrayList Tester");
        setLayout(new BorderLayout());

        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("BinSortArrayList tester GUI V0.0.1", SwingConstants.CENTER));
        return northPanel;
    }

    private JPanel createCenterPanel() {
        // Initialize CardLayout and container
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add both cards
        cardPanel.add(createAddPanel(), "AddView");
        cardPanel.add(createSearchPanel(), "SearchView");

        // Show AddView by default
        cardLayout.show(cardPanel, "AddView");
        return cardPanel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Text areas split pane
        listTextArea = new JTextArea(); listTextArea.setEditable(false);
        logTextArea = new JTextArea(); logTextArea.setEditable(false);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(listTextArea), new JScrollPane(logTextArea));
        split.setResizeWeight(0.5);
        panel.add(split, BorderLayout.CENTER);

        // Controls at bottom
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addTermTF = new JTextField(30);
        addTermTF.setText("Hello World!");
        JButton addButton = new JButton("Add Value");
        JButton removeButton = new JButton("Remove Last");
        JButton switchToSearch = new JButton("Switch to Search");

        addButton.addActionListener(e -> handleAdd());
        removeButton.addActionListener(e -> handleRemoveLast());
        switchToSearch.addActionListener(e -> cardLayout.show(cardPanel, "SearchView"));

        controls.add(new JLabel("String to add:"));
        controls.add(addTermTF);
        controls.add(addButton);
        controls.add(removeButton);
        controls.add(switchToSearch);

        panel.add(controls, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Text & log split
        searchTextArea = new JTextArea();
        searchTextArea.setEditable(false);
        searchLogTextArea = new JTextArea();
        searchLogTextArea.setEditable(false);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(searchTextArea), new JScrollPane(searchLogTextArea));
        split.setResizeWeight(0.5);
        panel.add(split, BorderLayout.CENTER);

        // Controls at bottom
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchInput = new JTextField(30);
        JButton searchButton = new JButton("Search List");
        JButton switchToAdd = new JButton("Switch to Add");

        searchButton.addActionListener(e -> handleSearch());
        switchToAdd.addActionListener(e -> cardLayout.show(cardPanel, "AddView"));

        controls.add(new JLabel("Search term:"));
        controls.add(searchInput);
        controls.add(searchButton);
        controls.add(switchToAdd);

        panel.add(controls, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createSouthPanel() {
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));
        south.add(quit);
        return south;
    }

    private void handleAdd() {
        String term = addTermTF.getText();
        binSortArrayList.add(term);
        String log = binSortArrayList.getLog();
        cumulativeLog.append(log);
        updateAddDisplay();
        updateSearchLog();
    }

    private void handleRemoveLast() {
        binSortArrayList.deleteLastAdded();
        String log = binSortArrayList.getLog();
        cumulativeLog.append(log);
        updateAddDisplay();
        updateSearchLog();
    }

    private void handleSearch() {
        String term = searchInput.getText();
        String result = binSortArrayList.binSearch(term);
        searchTextArea.append(result + "\n");
        String log = binSortArrayList.getLog();
        cumulativeLog.append(log);
        updateLogAreas();
    }

    private void updateAddDisplay() {
        // list
        StringBuilder sb = new StringBuilder();
        for (String s : binSortArrayList.toList()) sb.append(s).append("\n");
        listTextArea.setText(sb.toString());

        // primary log
        logTextArea.setText(cumulativeLog.toString());
    }

    private void updateSearchLog() {
        // mirror primary log
        searchLogTextArea.setText(cumulativeLog.toString());
    }

    private void updateLogAreas() {
        logTextArea.setText(cumulativeLog.toString());
        searchLogTextArea.setText(cumulativeLog.toString());
    }

}
