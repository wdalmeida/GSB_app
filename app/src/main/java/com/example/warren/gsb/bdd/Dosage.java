package com.example.warren.gsb.bdd;

/**
 * Created by warren on 23/04/15, GSB
 */
public class Dosage {
    protected String id_medoc;
    protected String code_indiv;
    protected String quantite;
    protected String unité;
    protected String durée;

    public Dosage(String id_medoc, String code_indiv, String quantite, String unité, String durée) {
        this.id_medoc = id_medoc;
        this.code_indiv = code_indiv;
        this.quantite = quantite;
        this.unité = unité;
        this.durée = durée;
    }

    public Dosage() {
    }

    public String getId_medoc() {
        return id_medoc;
    }

    public void setId_medoc(String id_medoc) {
        this.id_medoc = id_medoc;
    }

    public String getCode_indiv() {
        return code_indiv;
    }

    public void setCode_indiv(String code_indiv) {
        this.code_indiv = code_indiv;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getUnité() {
        return unité;
    }

    public void setUnité(String unité) {
        this.unité = unité;
    }

    public String getDurée() {
        return durée;
    }

    public void setDurée(String durée) {
        this.durée = durée;
    }
}