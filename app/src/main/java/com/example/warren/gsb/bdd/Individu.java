package com.example.warren.gsb.bdd;

/**
 * Created by warren on 23/04/15, GSB
 */
public class Individu {
    protected String code;
    protected String ageMin;
    protected String ageMax;
    protected String libelle;

    public Individu(String code, String ageMin, String ageMax, String libelle) {
        this.code = code;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.libelle = libelle;
    }

    public Individu() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(String ageMin) {
        this.ageMin = ageMin;
    }

    public String getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(String ageMax) {
        this.ageMax = ageMax;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
