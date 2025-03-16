package com.database;

import javafx.beans.property.SimpleStringProperty;

public class PatientFile {
    private SimpleStringProperty name;
    private SimpleStringProperty date;
    private SimpleStringProperty mrn;
    private SimpleStringProperty etm;
    private SimpleStringProperty procedures;

    public PatientFile() {}

    public PatientFile(String name, String date, String mrn, String etm, String procedures) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.mrn = new SimpleStringProperty(mrn);
        this.etm = new SimpleStringProperty(etm);
        this.procedures = new SimpleStringProperty(procedures);
    }

    public String getName() {
        return name.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getMrn() {
        return mrn.get();
    }

    public String getEtm() {
        return etm.get();
    }

    public String getProcedures() {
        return procedures.get();
    }
}
