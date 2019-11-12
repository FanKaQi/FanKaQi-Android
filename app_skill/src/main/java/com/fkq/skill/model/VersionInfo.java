package com.fkq.skill.model;

public class VersionInfo {
    private int versioncode;
    private String versionname;
    private String description;
    private long versionsize;

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getVersionsize() {
        return versionsize;
    }

    public void setVersionsize(long versionsize) {
        this.versionsize = versionsize;
    }
}
