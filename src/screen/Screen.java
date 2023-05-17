package screen;

import javax.swing.*;

public class Screen {
    public static String getInput(String label) {
        return JOptionPane.showInputDialog(label);
    }

    public static void showMessage(String label) {
        JOptionPane.showMessageDialog(null, label);
    }
}
