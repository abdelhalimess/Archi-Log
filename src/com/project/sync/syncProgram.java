package com.project.sync;

import com.project.sync.filesystem.*;
import com.project.sync.visitor.SynchronizationVisitor;
import com.project.new_profile.Profile;
import com.project.new_profile.ProfileBuilderImpl;

public class syncProgram {

    public static void main(String[] args) {
        // 1. Charger ou créer un profil
        ProfileBuilderImpl profileBuilder = new ProfileBuilderImpl();
        
        profileBuilder.setFolderA("MonProfile");
        profileBuilder.setFolderA("src_dir");
        profileBuilder.setFolderA("dest_dir");
        
        Profile profile = profileBuilder.build();
        
        // 2. Définir les chemins source et destination
        String sourcePath = profile.getFolderA() ;
        String destinationPath = profile.getFolderB();

        // 3. Créer les arborescences de fichiers
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
