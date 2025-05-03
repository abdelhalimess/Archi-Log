package com.project.new_profile;

public interface ProfileBuilder {
    ProfileBuilder setName(String name);
    ProfileBuilder setFolderA(String path);
    ProfileBuilder setFolderB(String path);
    ProfileBuilder setRegister(String register);
    Profile build();
}