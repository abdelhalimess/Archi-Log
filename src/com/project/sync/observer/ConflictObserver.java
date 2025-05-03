package com.project.sync.observer;

import com.project.sync.filesystem.FileSystemElement;

public interface ConflictObserver {
    ConflictResolution onConflictDetected(FileSystemElement source, FileSystemElement dest);
}