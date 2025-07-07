package com.example.quan_ly_sinh_vien_codegym.entity;

public class Module {
    int moduleId;
    String moduleName;

    public Module() {
    }

    public Module(int moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }

    public Module(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
