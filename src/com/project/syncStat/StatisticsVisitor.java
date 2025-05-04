package com.project.syncStat;

import com.project.new_profile.Profile;
import java.util.Date;

public class StatisticsVisitor implements ProfileVisitor {

    public StatisticsVisitor() {
    }
    private int fileCount = 0;
    private int dirCount = 0;
    private long totalSize = 0;
    private Date lastModified = new Date(0);

    @Override
    public void visitProfile(Profile profile) {


        System.out.println("========== Sync Profile Report ==========");
        System.out.println("Profile Name:   " + profile.getName());
        System.out.println("Folders synchro: ");

        System.out.println("Source FolderA: " + profile.getFolderA());
        System.out.println("Target FolderB: " + profile.getFolderB());



       
    }

    

    // Getters
    public int getFileCount() { return fileCount; }
    public int getDirCount() { return dirCount; }
    public long getTotalSize() { return totalSize; }
    public Date getLastModified() { return lastModified; }


}
