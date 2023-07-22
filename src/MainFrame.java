import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JLabel modPackNameLabel;
    private JTextField modPackNameText;
    private JButton confirmation;
    private JPanel mainPanel;

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Mod-pack Installer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 140);
        setLocationRelativeTo(null);
        setVisible(true);
        confirmation.addActionListener(e -> {
            if (modPackNameText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must enter a name");
                return;
            }

            try {
                Main.install(modPackNameText.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Something went wrong");
            }
        });
    }
}
