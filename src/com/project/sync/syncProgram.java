package com.project.sync;

import com.project.new_profile.Profile;
import com.project.sync.filesystem.*;
import com.project.sync.visitor.SynchronizationVisitor;
import com.project.syncStat.ProfileXmlDeserializer;

public class syncProgram {

    public static void main(String[] args) {

        // consider the argument as the profile name
        if (args.length != 1) {
            System.err.println("Usage: java -cp . com.project.syncStat.syncStatProgram <profile_name>");
            System.exit(1);
        }

        String profileName = args[0];

        //set the path to profile
        String profilePath = "Profiles/" + profileName + ".sync";

        // Désérialiser le profil
        // if the file isn't found the profil will  be null
        ProfileXmlDeserializer deserializer = new ProfileXmlDeserializer();
        Profile profile = deserializer.deserialize(profilePath);


        if (profile == null) {
            System.err.println("Profil non trouvé ou invalide : " + profilePath);
            System.exit(1);
        }

        // getting the target folders 

        String sourcePath = profile.getFolderA() ;
        String destinationPath = profile.getFolderB();

        // create the tree
        
        FileSystemElement sourceRoot = buildFileSystem(sourcePath);
        FileSystemElement destRoot = buildFileSystem(destinationPath);

        // 4. Créer le visitor avec le profil
        SynchronizationVisitor visitor = new SynchronizationVisitor(sourceRoot, destRoot, profile);

        // 5. Visiter les fichiers
        sourceRoot.accept(visitor);

        // 6. Sauvegarder le log de synchronisation dans un fichier .sync
        visitor.finish("MonProfile.sync");

        System.out.println("Synchronization complete.");
    }

    public static FileSystemElement buildFileSystem(String rootPath) {
        // Ce serait une fonction récursive qui parcourt le système de fichiers
        // et retourne un DirectoryElement avec tous les FileElement dedans.
        DirectoryElement root = new DirectoryElement(rootPath);
        java.io.File rootFile = new java.io.File(rootPath);

        for (java.io.File file : rootFile.listFiles()) {
            if (file.isDirectory()) {
                root.addChild(buildFileSystem(file.getPath())); // recursive
            } else {
                root.addChild(new FileElement(file.getPath()));
            }
        }
        return root;
    }
}
