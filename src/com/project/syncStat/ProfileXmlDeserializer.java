package com.project.syncStat;

import com.project.new_profile.Profile;
import com.project.new_profile.ProfileBuilderImpl;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class ProfileXmlDeserializer {
    public Profile deserialize(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            String name = doc.getDocumentElement().getAttribute("name");
            String folderA = doc.getElementsByTagName("folderA").item(0).getTextContent();
            String folderB = doc.getElementsByTagName("folderB").item(0).getTextContent();


 
            return new ProfileBuilderImpl()
                    .setName(name)
                    .setFolderA(folderA)
                    .setFolderB(folderB)
                    .build();

        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du fichier de profil : " + e.getMessage());
            return null;
        }
    }

    public String readRawXml(String filePath) {
        try {
            return new String(java.nio.file.Files.readAllBytes(new File(filePath).toPath()));
        } catch (Exception e) {
            return "[Erreur lors de la lecture brute XML]";
        }
    }
}
