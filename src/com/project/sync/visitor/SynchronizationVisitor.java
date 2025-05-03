package com.project.sync.visitor;

import com.project.sync.filesystem.FileSystemElement;
import com.project.sync.observer.ConflictManager;
import com.project.sync.observer.ConflictResolution;
import com.project.sync.filesystem.FileElement;
import com.project.sync.filesystem.DirectoryElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.project.sync.adapter.WebDavDirectoryAdapter;
import com.project.sync.adapter.WebDavFileAdapter;
import com.project.sync.visitor.Result;

public class SynchronizationVisitor extends ConflictManager implements SyncVisitor {
    private FileSystemElement sourceRoot;
    private FileSystemElement destRoot;
    private Profile profile;

    public SynchronizationVisitor(FileSystemElement sourceRoot, FileSystemElement destRoot, Profile profile) {
        this.sourceRoot = sourceRoot;
        this.destRoot = destRoot;
        this.profile = profile;
    }

    @Override
    public void visitFile(FileElement file) {
        System.out.println("Visiting file: " + file.getPath());
        if (file.exists()) {
            System.out.println("File exists. Checking for updates...");
            Result result = compareFiles(file, file);
            System.out.println("Comparison result: " + result);
        } else {
            System.out.println("File does not exist.");
        }
    }

    @Override
    public void visitDirectory(DirectoryElement directory) {
        System.out.println("Visiting directory: " + directory.getPath());
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visitWebFile(WebDavFileAdapter webDavFile) {
        System.out.println("Visiting WebDAV file: " + webDavFile.getPath());
        if (webDavFile.exists()) {
            System.out.println("WebDAV file exists. Syncing...");
        } else {
            System.out.println("WebDAV file missing.");
        }
    }

    @Override
    public void visitWebDirectory(WebDavDirectoryAdapter webDavDir) {
        System.out.println("Visiting WebDAV directory: " + webDavDir.getPath());
        for (FileSystemElement child : webDavDir.getChildren()) {
            child.accept(this);
        }
    }

    public Result compareFiles(FileSystemElement A, FileSystemElement B) {
        if (A.getLastModified().equals(B.getLastModified())) {
            return Result.EQUAL;
        } else if (A.getLastModified().after(B.getLastModified())) {
            return Result.A_PLUS;
        } else {
            return Result.B_PLUS;
        }
    }

    public void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
            System.out.println("Deleted file at path: " + path);
        } catch (IOException e) {
            System.out.println("Failed to delete file at path: " + path + " - " + e.getMessage());
        }
    }
    
    
    public void copyFile(FileSystemElement A, String path) {
        Path sourcePath = Paths.get(A.getPath());
        Path destPath = Paths.get(path);
        
        try {
            Files.createDirectories(destPath.getParent()); // Assure que le dossier destination existe
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied file from " + A.getPath() + " to " + path);
        } catch (IOException e) {
            System.out.println("Failed to copy file: " + e.getMessage());
        }
    }
    
    
    public void handleConflict(FileSystemElement source, FileSystemElement dest) {
        ConflictResolution resolution = notifyConflict(source, dest);
        switch (resolution) {
            case ACCEPT_A:
                System.out.println("Source accepted, synchronizing...");
                copyFile(source, dest.getPath());
                break;
            case ACCEPT_B:
                System.out.println("Destination accepted, synchronizing...");
                copyFile(dest, source.getPath());
                break;
            case FUSION:
                System.out.println("Merging changes...");
                deleteFile(dest.getPath());
                copyFile(source, dest.getPath());
                break;
            case CANCEL:
                System.out.println("Conflict canceled.");
                break;
        }
    }
}
