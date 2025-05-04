package com.project.new_profile;

public class newProfile {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java -cp . new_profile.Main <profile_name> <folderA> <folderB>");
            System.exit(1);
        }

        Profile profile = new ProfileBuilderImpl()
            .setName(args[0])
            .setFolderA(args[1])
            .setFolderB(args[2])
            .build();

        ProfileXmlSerializer serializer = new ProfileXmlSerializer();
        String outputFile = args[0] + ".sync";
        serializer.serialize2(profile, outputFile);
    }
}