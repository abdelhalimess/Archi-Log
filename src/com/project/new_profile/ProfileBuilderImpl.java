package com.project.new_profile;

public class ProfileBuilderImpl implements ProfileBuilder {
    private Profile profile = new Profile();

    @Override
    public ProfileBuilder setName(String name) {
        profile.setName(name);
        return this;
    }

    @Override
    public ProfileBuilder setFolderA(String path) {
        profile.setFolderA(path);
        return this;
    }

    @Override
    public ProfileBuilder setFolderB(String path) {
        profile.setFolderB(path);
        return this;
    }

    @Override
    public ProfileBuilder setRegister(String register) {
        profile.setRegister(register);
        return this;
    }




    @Override
    public Profile build() {
        // Validation facultative ici
        return profile;
    }
}