package com.project.syncStat;

import com.project.sync.filesystem.*;
import com.project.sync.visitor.SyncVisitor;
import com.project.sync.adapter.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class StatisticsVisitor implements SyncVisitor {
    private int fileCount = 0;
    private int dirCount = 0;
    private long totalSize = 0;
    private Date lastModified = new Date(0);

    @Override
    public void visitFile(FileElement file) {
        fileCount++;
        Path path = Paths.get(file.getPath());
        try {
            totalSize += Files.size(path);
        } catch (IOException e) {
            System.err.println("Failed to access file: " + path + " - " + e.getMessage());
        }
        if (file.getLastModified().after(lastModified)) {
            lastModified = file.getLastModified();
        }
    }

    @Override
    public void visitDirectory(DirectoryElement directory) {
        dirCount++;
        Path path = Paths.get(directory.getPath());
        try {
            totalSize += Files.size(path);
        } catch (IOException e) {
            System.err.println("Failed to access file: " + path + " - " + e.getMessage());
        }
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visitWebFile(WebDavFileAdapter webDavFile) {
        fileCount++;
        Path path = Paths.get(webDavFile.getPath());
        try {
            totalSize += Files.size(path);
        } catch (IOException e) {
            System.err.println("Failed to access file: " + path + " - " + e.getMessage());
        }
        if (webDavFile.getLastModified().after(lastModified)) {
            lastModified = webDavFile.getLastModified();
        }
    }

    @Override
    public void visitWebDirectory(WebDavDirectoryAdapter webDavDir) {
        dirCount++;
        Path path = Paths.get(webDavDir.getPath());
        try {
            totalSize += Files.size(path);
        } catch (IOException e) {
            System.err.println("Failed to access file: " + path + " - " + e.getMessage());
        }
        for (FileSystemElement child : webDavDir.getChildren()) {
            child.accept(this);
        }
    }

    // Getters
    public int getFileCount() { return fileCount; }
    public int getDirCount() { return dirCount; }
    public long getTotalSize() { return totalSize; }
    public Date getLastModified() { return lastModified; }
}
