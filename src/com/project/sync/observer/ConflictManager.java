package com.project.sync.observer;

import java.util.ArrayList;
import java.util.List;

import com.project.sync.filesystem.FileSystemElement;

public abstract class ConflictManager {
    protected List<ConflictObserver> observers;

    public ConflictManager() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(ConflictObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ConflictObserver observer) {
        observers.remove(observer);
    }

    public ConflictResolution notifyConflict(FileSystemElement source, FileSystemElement dest) {
        for (ConflictObserver observer : observers) {
            ConflictResolution resolution = observer.onConflictDetected(source, dest);
            if (resolution != null) {
                return resolution;
            }
        }
        return ConflictResolution.CANCEL; // Default if no observer handles the conflict
    }
}
