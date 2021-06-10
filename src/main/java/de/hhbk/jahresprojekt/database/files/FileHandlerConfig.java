package de.hhbk.jahresprojekt.database.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandlerConfig {
    private Path storagePath;
    private String outlookPath;

    public FileHandlerConfig(){
        String homePath = System.getProperty("user.home");
        storagePath = Paths.get(homePath, ".storage");
        outlookPath = "C:\\Program Files (x86)\\Microsoft Office\\Office16\\OUTLOOK.EXE";
    }

    public String getOutlookPath() {
        return outlookPath;
    }

    public Path getStoragePath() {
        return storagePath;
    }
}
