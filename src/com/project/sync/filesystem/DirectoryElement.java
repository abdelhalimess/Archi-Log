package com.project.sync.filesystem;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.project.sync.visitor.SyncVisitor;

public class DirectoryElement implements FileSystemElement {
    private String path;
    private List<FileSystemElement> children;

    public DirectoryElement(String path) {
        this.path = path;
        this.children = new ArrayList<>();
    }

    public void addChild(FileSystemElement element) {
        children.add(element);
    }

    public List<FileSystemElement> getChildren() {
        return children;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Date getLastModified() {
        // Logic to return the last modified date (could be based on the last modified date of its children)
        return new Date();
    }

    @Override
    public boolean exists() {
        // Logic to check if the directory exists
        return true;
    }

    @Override
    public void accept(SyncVisitor visitor) {
        visitor.visitDirectory(this);
        for (FileSystemElement child : children) {
            child.accept(visitor);
        }
    }
}
