package com.project.sync.adapter;

import java.util.List;
import java.util.Date;

public class WebDavClient {
    
    public byte[] get(String url) {
        return new byte[0];
    }

    public void put(String url, byte[] data) {
    }

    public void delete(String url) {

    }

    public List<String> listDirectory(String url) {
        return List.of();
    }

    public Date getLastModified(String url) {
        return new Date(); 
    }
}

