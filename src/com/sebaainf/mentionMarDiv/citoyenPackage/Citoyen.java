package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jenkov.db.itf.mapping.AGetterMapping;
import com.jgoodies.common.bean.Bean;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//import com.sebaainf.fichfamil.common.Deces;

//import com.sebaainf.fichfamil.persistance.MyDaosCitoyen;

/**
 * Created by admin on 10/01/2015.
 * for butterfly mapping
 * 1- we have follow the naming conditions of class and properties
 * just match the table and their columns in data base
 * 2- we have initialise all properties in class with defaults values
 * 3- put GETTer anotations just before their getter methods
 */

public class Citoyen extends Bean implements IPerson {


    public static final String PROPERTY_ID_CIT = "id_cit";

    public static final String PROPERTY_NOM_FR = "nom_fr";
    public static final String PROPERTY_PRENOM_FR = "prenom_fr";
    public static final String PROPERTY_NOM_AR = "nom_ar";
    public static final String PROPERTY_PRENOM_AR = "prenom_ar";

    public static final String PROPERTY_DATE_NAISS = "date_naiss";
    public static final String PROPERTY_LIEUNAISS = "lieunaiss";
    public static final String PROPERTY_EMPLOI = "emploi";

    public static final String PROPERTY_P_PERE = "p_pere";
    public static final String PROPERTY_NP_MERE = "np_mere";
    public static final String PROPERTY_EST_MASCULIN = "est_masculin";

    public static final String PROPERTY_DATENAISS_EST_PRESUME = "dateNaiss_est_presume";

    private int id_cit;


    private String nom_fr = "";
    private String prenom_fr = "";
    private String nom_ar = "";
    private String prenom_ar = "";

    private Date date_naiss;
    private String lieunaiss = "";
    private String emploi = "";

    private String p_pere = "";
    private String np_mere = "";
    private Boolean est_masculin = true;

    private Boolean dateNaiss_est_presume = false;

    private List<Mention> listMentions = new ArrayList<Mention>();


    @AGetterMapping(databaseGenerated = true)

    public int getId_cit() {

        return id_cit;
    }

    public void setId_cit(int newId_cit) {

        int oldId_cit = this.id_cit;
        this.id_cit = newId_cit;
        if (oldId_cit != newId_cit) {
            firePropertyChange(Citoyen.PROPERTY_ID_CIT, oldId_cit, newId_cit);
        }

    }

    public String getNom_fr() {

        return nom_fr;
    }

    public void setNom_fr(String newNom_fr) {

        String oldNom_fr = this.nom_fr;
        this.nom_fr = newNom_fr;
        if (!oldNom_fr.equals(newNom_fr)) {
            firePropertyChange(Citoyen.PROPERTY_NOM_FR, oldNom_fr, newNom_fr);
        }

    }

    public String getPrenom_fr() {

        return prenom_fr;
    }

    public void setPrenom_fr(String newPrenom_fr) {

        String oldPrenom_fr = this.prenom_fr;
        this.prenom_fr = newPrenom_fr;
        if (!oldPrenom_fr.equals(newPrenom_fr)) {
            firePropertyChange(Citoyen.PROPERTY_PRENOM_FR, oldPrenom_fr, newPrenom_fr);
        }

    }

    public String getNom_ar() {

        return nom_ar;
    }

    public void setNom_ar(String newNom_ar) {

        String oldNom_ar = this.nom_ar;
        this.nom_ar = newNom_ar;
        if (!oldNom_ar.equals(newNom_ar)) {
            firePropertyChange(Citoyen.PROPERTY_NOM_AR, oldNom_ar, newNom_ar);
        }

    }

    public String getPrenom_ar() {

        return prenom_ar;
    }

    public void setPrenom_ar(String newPrenom_ar) {

        String oldPrenom_ar = this.prenom_ar;
        this.prenom_ar = newPrenom_ar;
        if (!oldPrenom_ar.equals(newPrenom_ar)) {
            firePropertyChange(Citoyen.PROPERTY_PRENOM_AR, oldPrenom_ar, newPrenom_ar);
        }

    }

    @Override
    public Date getDate_naiss() {

        return date_naiss;
    }


    @Override
    public void setDate_naiss(Date newDate_naiss) {

        Date oldDate_naiss = this.date_naiss;
        this.date_naiss = newDate_naiss;
        if (oldDate_naiss != newDate_naiss) {
            firePropertyChange(Citoyen.PROPERTY_DATE_NAISS, oldDate_naiss, newDate_naiss);
        }

    }

    public String getP_pere() {

        return p_pere;
    }

    public void setP_pere(String newP_pere) {

        String oldP_pere = this.p_pere;
        this.p_pere = newP_pere;
        if (!oldP_pere.equals(newP_pere)) {
            firePropertyChange(Citoyen.PROPERTY_P_PERE, oldP_pere, newP_pere);
        }

    }

    public String getNp_mere() {

        return np_mere;
    }

    public void setNp_mere(String newNp_mere) {

        String oldNp_mere = this.np_mere;
        this.np_mere = newNp_mere;
        if (!oldNp_mere.equals(newNp_mere)) {
            firePropertyChange(Citoyen.PROPERTY_NP_MERE, oldNp_mere, newNp_mere);
        }

    }

    public Boolean getEst_masculin() {

        return est_masculin;
    }

    public void setEst_masculin(Boolean newEst_masculin) {

        Boolean oldEst_masculin = this.est_masculin;
        this.est_masculin = newEst_masculin;
        if (oldEst_masculin != newEst_masculin) {
            firePropertyChange(Citoyen.PROPERTY_EST_MASCULIN, oldEst_masculin, newEst_masculin);
        }

    }

    public String getLieunaiss() {

        return lieunaiss;
    }

    public void setLieunaiss(String new_lieunaiss) {

        String old_lieunaiss = this.lieunaiss;
        this.lieunaiss = new_lieunaiss;
        if (!old_lieunaiss.equals(new_lieunaiss)) {
            firePropertyChange(Citoyen.PROPERTY_LIEUNAISS, old_lieunaiss, new_lieunaiss);
        }
    }

    public String getEmploi() {

        return emploi;
    }

    public void setEmploi(String new_emploi) {

        String old_emploi = this.emploi;
        this.emploi = new_emploi;
        if (!old_emploi.equals(new_emploi)) {
            firePropertyChange(Citoyen.PROPERTY_EMPLOI,
                    old_emploi, new_emploi);
        }
    }


    public Boolean getDateNaiss_est_presume() {

        return dateNaiss_est_presume;
    }

    public void setDateNaiss_est_presume(Boolean newDateNaiss_est_presume) {

        Boolean oldDateNaiss_est_presume = this.dateNaiss_est_presume;
        this.dateNaiss_est_presume = newDateNaiss_est_presume;
        if (oldDateNaiss_est_presume != newDateNaiss_est_presume) {
            firePropertyChange(Citoyen.PROPERTY_DATENAISS_EST_PRESUME,
                    oldDateNaiss_est_presume, newDateNaiss_est_presume);
        }

    }

    public List<Mention> getListMentions() {

        return listMentions;
    }

    public void setListMentions(List<Mention> listMentions) {

        this.listMentions = listMentions;
    }

    public void addMention(Mention ment) {

        this.listMentions.add(ment);
    }

    public void removeMention(Mention ment) {

        this.listMentions.remove(ment);
    }
}