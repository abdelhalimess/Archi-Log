package com.project.sync.observer;

import com.project.sync.filesystem.FileSystemElement;

public class ConsoleConflictResolver implements ConflictObserver {
    @Override
    public ConflictResolution onConflictDetected(FileSystemElement source, FileSystemElement dest) {
        System.out.println("Conflict detected between source: " + source.getPath() + " and destination: " + dest.getPath());
        return ConflictResolution.ACCEPT_A;
    }
}
