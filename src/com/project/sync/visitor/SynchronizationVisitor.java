package com.project.sync.visitor;

import com.project.sync.filesystem.FileSystemElement;
import com.project.sync.observer.ConflictManager;
import com.project.sync.observer.ConflictResolution;
import com.project.sync.filesystem.FileElement;
import com.project.sync.filesystem.DirectoryElement;
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
        // Logic for file synchronization
    }

    @Override
    public void visitDirectory(DirectoryElement directory) {
        // Logic for directory synchronization
    }

    @Override
    public void visitWebFile(WebDavFileAdapter webDavFile) {
        // Logic for WebDAV file synchronization
    }

    @Override
    public void visitWebDirectory(WebDavDirectoryAdapter webDavDir) {
        // Logic for WebDAV directory synchronization
    }

    public Result compareFiles(FileSystemElement A, FileSystemElement B) {
        // Logic to compare two files or directories
        return Result.EQUAL;
    }

    public void deleteFile(String path) {
        // Logic to delete a file from the system
    }

    public void copyFile(FileSystemElement A, String path) {
        // Logic to copy a file
    }
    
    public void handleConflict(FileSystemElement source, FileSystemElement dest) {
        ConflictResolution resolution = notifyConflict(source, dest);
        switch (resolution) {
            case ACCEPT_A:
                System.out.println("Source accepted, synchronizing...");
                break;
            case ACCEPT_B:
                System.out.println("Destination accepted, synchronizing...");
                break;
            case FUSION:
                System.out.println("Merging changes...");
                break;
            case CANCEL:
                System.out.println("Conflict canceled.");
                break;
        }
    }
}
