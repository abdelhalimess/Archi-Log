package com.project.sync.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.sync.filesystem.FileSystemElement;
import com.project.sync.visitor.SyncVisitor;


public class WebDavDirectoryAdapter implements FileSystemElement {
    private String url;
	private WebDavClient webDavClient;
	private List<FileSystemElement> children;

    public WebDavDirectoryAdapter(String path, WebDavClient webDavClient, String url) {
        this.webDavClient = webDavClient;
        this.url = url;
        this.children = new ArrayList<>();
    }

    public void addChildren(FileSystemElement element) {
        children.add(element);
    }

    public List<FileSystemElement> getChildren() {
        return children;
    }

    @Override
    public String getPath() {
        return url;
    }

    @Override
    public Date getLastModified() {
        return webDavClient.getLastModified(url);
    }

    @Override
    public boolean exists() {
        try {
            webDavClient.listDirectory(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void accept(SyncVisitor visitor) {
        visitor.visitWebDirectory(this);
    }
}

