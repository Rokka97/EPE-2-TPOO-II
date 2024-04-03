import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica main = new InterfazGrafica();
            main.setVisible(true);
        });
    }
}

