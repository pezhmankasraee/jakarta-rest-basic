package com.pezhmankasraee.jakartarestbasic.models;

public class DeveloperInfo {

    private final String fullname;
    private final String programmingLanguage;
    private final String programmingLanguageVersion;
    private final String framework;
    private final String frameworkVersion;


    public DeveloperInfo() {
        this.fullname = "Pezhman Kasraee";
        this.programmingLanguage = "Java SE";
        this.programmingLanguageVersion = "jdk 21";
        this.framework = "Jakarta EE";
        this.frameworkVersion = "11";
    }

    public String getFullname() {
        return fullname;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getProgrammingLanguageVersion() {
        return programmingLanguageVersion;
    }

    public String getFramework() {
        return framework;
    }

    public String getFrameworkVersion() {
        return frameworkVersion;
    }
}
