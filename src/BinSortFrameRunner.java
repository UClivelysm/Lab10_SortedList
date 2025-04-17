import javax.swing.*;

public class BinSortFrameRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BinSortFrame frame = new BinSortFrame();
            frame.setVisible(true);
        });
    }
}
