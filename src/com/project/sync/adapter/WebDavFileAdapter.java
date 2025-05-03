package com.project.sync.adapter;

import java.util.Date;

import com.project.sync.filesystem.FileSystemElement;
import com.project.sync.visitor.SyncVisitor;

public class WebDavFileAdapter implements FileSystemElement {
    private String path;

    public WebDavFileAdapter(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Date getLastModified() {
        // Logic for WebDAV file's last modified date
        return new Date();
    }

    @Override
    public boolean exists() {
        // Logic for checking if WebDAV file exists
        return true;
    }

    @Override
    public void accept(SyncVisitor visitor) {
        visitor.visitWebFile(this);
    }
}

