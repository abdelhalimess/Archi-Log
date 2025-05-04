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
            NodeList fileNodes = doc.getElementsByTagName("file");
            StringBuilder registerBuilder = new StringBuilder();

            for (int i = 0; i < fileNodes.getLength(); i++) {
                Node fileNode = fileNodes.item(i);
                if (fileNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fileElement = (Element) fileNode;
                    String path = fileElement.getAttribute("path");
                    String lastSync = fileElement.getAttribute("lastSync");

                    if (!path.isEmpty()) {
                        registerBuilder.append(path);
                        if (!lastSync.isEmpty()) {
                            registerBuilder.append(" (lastSync: ").append(lastSync).append(")");
                        }
                        registerBuilder.append("\n");
                    }
                }
            }

            String register = registerBuilder.toString().trim();


 
            return new ProfileBuilderImpl()
                    .setName(name)
                    .setFolderA(folderA)
                    .setFolderB(folderB)
                    .setRegister(register)
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
