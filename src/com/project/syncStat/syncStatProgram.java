package com.project.syncStat;


import com.project.new_profile.Profile;
import com.project.new_profile.ProfileBuilderImpl;
import com.project.sync.syncProgram;
import com.project.sync.filesystem.*;
import java.text.SimpleDateFormat;

public class syncStatProgram {
    public static void main(String[] args) {
        // 1. Load Profile
        ProfileBuilderImpl profileBuilder = new ProfileBuilderImpl();
        
        profileBuilder.setFolderA("MonProfile");
        profileBuilder.setFolderA("src_dir");
        profileBuilder.setFolderA("dest_dir");
        
        Profile profile = profileBuilder.build();

        // 2. Build filesystem tree from profile source path
        String sourcePath = "src_dir"; // You can extract this from profile if you store it there
        FileSystemElement sourceRoot = syncProgram.buildFileSystem(sourcePath);

        // 3. Create StatisticsVisitor
        StatisticsVisitor statVisitor = new StatisticsVisitor();

        // 4. Traverse filesystem
        sourceRoot.accept(statVisitor);

        // 5. Display stats
        System.out.println("========== Sync Statistics ==========");
        System.out.println("Profile: " + profile.getName());
        System.out.println("Directories: " + statVisitor.getDirCount());
        System.out.println("Files: " + statVisitor.getFileCount());
        System.out.println("Total Size: " + formatSize(statVisitor.getTotalSize()));
        System.out.println("Last Modified: " + formatDate(statVisitor.getLastModified()));
        System.out.println("=====================================");
    }

    public static String formatSize(long sizeInBytes) {
        String[] units = {"B", "KB", "MB", "GB"};
        int unitIndex = 0;
        double size = sizeInBytes;
        while (size > 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }
        return String.format("%.2f %s", size, units[unitIndex]);
    }

    public static String formatDate(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
