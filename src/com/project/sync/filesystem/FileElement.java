package com.project.sync.filesystem;

import java.util.Date;

import com.project.sync.visitor.SyncVisitor;

public class FileElement implements FileSystemElement{
	private String path;

    public FileElement(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Date getLastModified() {
        // Logic to return the last modified date
        return new Date();
    }

    @Override
    public boolean exists() {
        // Logic to check if the file exists
        return true;
    }

    @Override
    public void accept(SyncVisitor visitor) {
        visitor.visitFile(this);
    }
}
