package com.project.new_profile;

import com.project.syncStat.ProfileVisitor;

public class Profile {
    private String name;
    private String folderA;
    private String folderB;
    private String register;

    // Setters package-private
    void setName(String name) { this.name = name; }
    void setFolderA(String folderA) { this.folderA = folderA; }
    void setFolderB(String folderB) { this.folderB = folderB; }
    void setRegister(String register) { this.register = register; }

    // Getters publics
    public String getName() { return name; }
    public String getFolderA() { return folderA; }
    public String getFolderB() { return folderB; }
    public String getRegister(){return register;}


    public void accept(ProfileVisitor visitor){
        visitor.visitProfile(this);    
    
    };

    


}