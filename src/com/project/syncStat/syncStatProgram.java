package com.project.syncStat;


import com.project.new_profile.Profile;

public class syncStatProgram {
    public static void main(String[] args) {
        
        // search if the profile exists

        if (args.length != 1) {
            System.err.println("Usage: java -cp . com.project.syncStat.syncStatProgram <profile_name>");
            System.exit(1);
        }

        String profileName = args[0];
        String profilePath = "Profiles/" + profileName + ".sync";

        // Désérialiser le profil
        ProfileXmlDeserializer deserializer = new ProfileXmlDeserializer();
        Profile profile = deserializer.deserialize(profilePath);

        if (profile == null) {
            System.err.println("Profil non trouvé ou invalide : " + profilePath);
            System.exit(1);
        }
        ProfileVisitor statVisitor = (ProfileVisitor) new StatisticsVisitor();

        profile.accept(statVisitor);

         System.out.println("Profile Content with registre : "+deserializer.readRawXml(profilePath));

     
}

}
