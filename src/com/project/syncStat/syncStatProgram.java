package com.project.syncStat;


import com.project.new_profile.Profile;

public class syncStatProgram {
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

        //the visitor will be responsible of printing the folders, the profile name ...
        ProfileVisitor statVisitor = (ProfileVisitor) new StatisticsVisitor();

        profile.accept(statVisitor);


     
}

}
