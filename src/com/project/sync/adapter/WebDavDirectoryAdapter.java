package com.project.sync.adapter;

import java.util.Date;

import com.project.sync.filesystem.FileSystemElement;
import com.project.sync.visitor.SyncVisitor;


public class WebDavDirectoryAdapter implements FileSystemElement {
    private String path;

    public WebDavDirectoryAdapter(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Date getLastModified() {
        // Logic for WebDAV directory's last modified date
        return new Date();
    }

    @Override
    public boolean exists() {
        // Logic for checking if WebDAV directory exists
        return true;
    }

    @Override
    public void accept(SyncVisitor visitor) {
        visitor.visitWebDirectory(this);
    }
}

