package com.project.new_profile;

public class ProfileDirector {
    private ProfileBuilder builder;

    public ProfileDirector(ProfileBuilder builder) {
        this.builder = builder;
    }


    public Profile createFullProfile(String name, String src, String dest, String register) {
        builder.setName(name)
               .setFolderA(src)
               .setFolderB(dest)
               .setRegister(register);

  

        return builder.build();
    }
}