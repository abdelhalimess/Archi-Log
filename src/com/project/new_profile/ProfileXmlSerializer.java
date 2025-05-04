package com.project.new_profile;

import java.io.FileWriter;

public class ProfileXmlSerializer {

    // invoquer lors de synchronisation des profiles ajout au registre 

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
        
        
        System.out.println("Generated XML:\n" + xml);
        System.out.println("Saved to: " + filePath);
    }
	

    // invoquer lors de l'initialisation des profiles (registre vide)
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
        
        try {
            FileWriter writer = new FileWriter("Profiles/"+filePath);  
            writer.write(xml.toString());                  
            writer.close();                                
            System.out.println("Generated XML:\n" + xml);
            System.out.println("Saved to:  /Profiles/" + filePath);
        } catch (Exception e) {
            System.err.println("couldn't write the file : " + e.getMessage());
        }

    }
}