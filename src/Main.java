import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        new MainFrame();
    }

    public static void install(String modPackName) throws IOException {
        String username = System.getProperty("user.name");

        Path prismModPackPath = Paths.get("C:\\Users\\" + username + "\\AppData\\Roaming\\PrismLauncher\\instances\\" + modPackName + "\\.minecraft");

        if (!Files.exists(prismModPackPath)) {
            JOptionPane.showMessageDialog(null, "Mod-pack instance not found in Prism instances");
            return;
        }

        Path tlModPackPath = Paths.get("C:\\Users\\" + username + "\\AppData\\Roaming\\.minecraft\\versions\\" + modPackName);

        if (!Files.exists(tlModPackPath)) {
            JOptionPane.showMessageDialog(null, "Mod-pack instance not found in Prism instances");
            return;
        }

        String[] directories = {
                "config",
                "mods",
                "resourcepacks",
                "shaderpacks"
        };

        for (final String directory : directories) {
            File folder = new File(prismModPackPath + "\\" + directory);

            if (!folder.exists() && !folder.mkdir()) {
                JOptionPane.showMessageDialog(null, "Could not create folder: " + folder);
                return;
            }

            Path link = Paths.get(tlModPackPath + "\\" + directory);

            Files.createSymbolicLink(link, folder.toPath());
        }

        JOptionPane.showMessageDialog(null, "Done!");

        System.exit(0);
    }

}