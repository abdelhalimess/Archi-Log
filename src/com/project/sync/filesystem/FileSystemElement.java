package com.project.sync.filesystem;

import java.util.Date;

import com.project.sync.visitor.SyncVisitor;

public interface FileSystemElement {

    /**
     * Returns the path of the file or directory.
     */
    String getPath();

    /**
     * Returns the last modification date of the file or directory.
     */
    Date getLastModified();

    /**
     * Checks whether the file or directory exists.
     */
    boolean exists();

    /**
     * Accepts a SyncVisitor (Visitor pattern).
     */
    void accept(SyncVisitor visitor);
}

