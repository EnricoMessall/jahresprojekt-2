package de.hhbk.jahresprojekt.database.files;

import javax.swing.*;

public class OpenFileDialog extends JFileChooser {
    public OpenFileDialog(){
        setDialogType(OPEN_DIALOG);
        setFileSelectionMode(FILES_ONLY);
        setMultiSelectionEnabled(false);
        setDialogTitle("Neue Version hinzufügen");
    }

    public String getFile(){
        setVisible(true);
        final int result = showOpenDialog(null);
        setVisible(false);
        if(result == JFileChooser.APPROVE_OPTION)
            return getSelectedFile().getAbsolutePath();
        return null;
    }
}
