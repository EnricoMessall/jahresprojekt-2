package de.hhbk.jahresprojekt.database.files;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.FileRepository;
import de.hhbk.jahresprojekt.model.File;
import de.hhbk.jahresprojekt.model.builder.FileBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

public class FileHandler {
    private static FileHandler fileHandler;
    public static FileHandler getInstance() throws IOException { if(fileHandler == null) fileHandler = new FileHandler(new OpenFileDialog(), new FileHandlerConfig());return fileHandler; }
    private OpenFileDialog openFileDialog;
    private FileHandlerConfig fileHandlerConfig;

    private FileHandler(OpenFileDialog openFileDialog, FileHandlerConfig fileHandlerConfig) throws IOException {
        this.openFileDialog = openFileDialog;
        this.fileHandlerConfig = fileHandlerConfig;

        assureStorageFolder();
    }

    private void assureStorageFolder() throws IOException {
        if(!Files.exists(fileHandlerConfig.getStoragePath())){
            Files.createDirectory(fileHandlerConfig.getStoragePath());
        }
    }

    public File addNewVersion() throws IOException {
        String path = openFileDialog.getFile();
        if(path == null) return null;

        java.io.File original = new java.io.File(path);
        Path copy = Paths.get(fileHandlerConfig.getStoragePath().toString(), getRandomString() + getExtension(original.getName()));
        Files.copy(original.toPath(), copy, StandardCopyOption.REPLACE_EXISTING);

        File file = FileBuilder.aFile().withDate(new Date()).withPath(copy.toString()).build();
        RepositoryContainer.get(File.class).save(file);
        return file;
    }

    private String getExtension(String filename){
        String[] parts = filename.split("\\.");
        return parts.length > 1 ? "." + parts[parts.length - 1] : "";
    }

    private String getRandomString(){
        String s = "";
        for(; s.length() <20; s += "abcdefghijklmnopqrstuvwxyz".charAt(new Random().nextInt(26)));
        return s;
    }

    public void delete(String path) throws IOException {
        Files.delete(Path.of(path));
    }

    public void send(String path){
        try {
            Process process = Runtime.getRuntime().exec(fileHandlerConfig.getOutlookPath() + " /c ipm.note /a " + path);
        } catch (Exception e) {
            new Error(e.getMessage());
        }
    }
}
