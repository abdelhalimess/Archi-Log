package com.project.sync.filesystem;

import java.util.Date;

import com.project.sync.visitor.SyncVisitor;

public interface FileSystemElement {

    String getPath();

    Date getLastModified();

    boolean exists();

    void accept(SyncVisitor visitor);
}

