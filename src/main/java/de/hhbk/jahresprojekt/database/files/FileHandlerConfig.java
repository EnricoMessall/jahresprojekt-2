package de.hhbk.jahresprojekt.database.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandlerConfig {
    private Path storagePath;

    public FileHandlerConfig(){
        String homePath = System.getProperty("user.home");
        storagePath = Paths.get(homePath, ".storage");
    }

    public Path getStoragePath() {
        return storagePath;
    }
}
