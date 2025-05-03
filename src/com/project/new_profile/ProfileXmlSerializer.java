package com.project.new_profile;

public class ProfileXmlSerializer {
	public void serialize(Profile profile, String filePath, StringBuilder syncLog) {
        StringBuilder xml = new StringBuilder();
        xml.append("<profile name=\"").append(profile.getName()).append("\">\n");
        xml.append("  <folders>\n");
        xml.append("    <folderA>").append(profile.getFolderA()).append("</folderA>\n");
        xml.append("    <folderB>").append(profile.getFolderB()).append("</folderB>\n");
        xml.append("  </folders>\n");
        xml.append("  <synchronization>\n");
        
        xml.append(syncLog);
        
        xml.append("  </synchronization>\n");
        xml.append("</profile>");
        
        // Écriture réelle dans le fichier (exemple simplifié)
        System.out.println("Generated XML:\n" + xml);
        System.out.println("Saved to: " + filePath);
    }
	
	public void serialize2(Profile profile, String filePath) {
        StringBuilder xml = new StringBuilder();
        xml.append("<profile name=\"").append(profile.getName()).append("\">\n");
        xml.append("  <folders>\n");
        xml.append("    <folderA>").append(profile.getFolderA()).append("</folderA>\n");
        xml.append("    <folderB>").append(profile.getFolderB()).append("</folderB>\n");
        xml.append("  </folders>\n");
        xml.append("  <synchronization>\n");
        
        xml.append("  </synchronization>\n");
        xml.append("</profile>");
        
        // Écriture réelle dans le fichier (exemple simplifié)
        System.out.println("Generated XML:\n" + xml);
        System.out.println("Saved to: " + filePath);
    }
}