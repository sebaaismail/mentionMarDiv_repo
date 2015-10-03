package com.sebaainf.mentionMarDiv.citoyen;

import com.jenkov.db.itf.mapping.AGetterMapping;
import com.jgoodies.common.bean.Bean;
//import com.sebaainf.fichfamil.common.Deces;

import java.sql.Date;

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


    public static final String PROPERTY_SIT_FAMIL = "sit_famil";
    public static final String PROPERTY_ID_CIT = "id_cit";
    public static final String PROPERTY_NUM_ACTNAISS = "num_actnaiss";
    public static final String PROPERTY_ANNEE_ACTNAISS = "annee_actnaiss";
    public static final String PROPERTY_CODE_LIEUNAISS = "code_lieunaiss";
    public static final String PROPERTY_NOM_FR = "nom_fr";
    public static final String PROPERTY_PRENOM_FR = "prenom_fr";
    public static final String PROPERTY_NOM_AR = "nom_ar";
    public static final String PROPERTY_PRENOM_AR = "prenom_ar";
    public static final String PROPERTY_DATE_NAISS = "date_naiss";
    public static final String PROPERTY_P_PERE = "p_pere";
    public static final String PROPERTY_NP_MERE = "np_mere";
    public static final String PROPERTY_EST_MASCULIN = "est_masculin";
    public static final String PROPERTY_ID_DECES = "id_deces";
    public static final String PROPERTY_DECES = "deces";
    public static final String PROPERTY_DATE_EST_PRESUME = "date_est_presume";
    private String sit_famil = "c";
    private int id_cit;
    private int num_actnaiss;
    private int annee_actnaiss;
    private int code_lieunaiss;
    private String nom_fr = "";
    private String prenom_fr = "";
    private String nom_ar = "";
    private String prenom_ar = "";
    private Date date_naiss;
    private String p_pere = "";
    private String np_mere = "";
    private Boolean est_masculin = true;
    private int id_deces;
    //private Deces deces;

    private Boolean date_est_presume = false;


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

    public int getNum_actnaiss() {

        return num_actnaiss;
    }

    public void setNum_actnaiss(int newNum_actnaiss) {

        int oldNum_actnaiss = this.num_actnaiss;
        this.num_actnaiss = newNum_actnaiss;
        if (oldNum_actnaiss != newNum_actnaiss) {
            firePropertyChange(Citoyen.PROPERTY_NUM_ACTNAISS, oldNum_actnaiss, newNum_actnaiss);

        }
    }

    public int getAnnee_actnaiss() {

        return annee_actnaiss;
    }

    public void setAnnee_actnaiss(int newAnnee_actnaiss) {

        int oldAnnee_actnaiss = this.annee_actnaiss;
        this.annee_actnaiss = newAnnee_actnaiss;
        if (oldAnnee_actnaiss != newAnnee_actnaiss) {
            firePropertyChange(Citoyen.PROPERTY_ANNEE_ACTNAISS, oldAnnee_actnaiss, newAnnee_actnaiss);
        }

    }

    public int getCode_lieunaiss() {

        return code_lieunaiss;
    }

    public void setCode_lieunaiss(int newCode_lieunaiss) {

        int oldCode_lieunaiss = this.code_lieunaiss;
        this.code_lieunaiss = newCode_lieunaiss;
        if (oldCode_lieunaiss != newCode_lieunaiss) {
            firePropertyChange(Citoyen.PROPERTY_CODE_LIEUNAISS, oldCode_lieunaiss, newCode_lieunaiss);
        }

    }

    public String getNom_fr() {

        return nom_fr;
    }

    public void setNom_fr(String newNom_fr) {

        String oldNom_fr = this.nom_fr;
        this.nom_fr = newNom_fr;
        if (oldNom_fr != newNom_fr) {
            firePropertyChange(Citoyen.PROPERTY_NOM_FR, oldNom_fr, newNom_fr);
        }

    }

    public String getPrenom_fr() {

        return prenom_fr;
    }

    public void setPrenom_fr(String newPrenom_fr) {

        String oldPrenom_fr = this.prenom_fr;
        this.prenom_fr = newPrenom_fr;
        if (oldPrenom_fr != newPrenom_fr) {
            firePropertyChange(Citoyen.PROPERTY_PRENOM_FR, oldPrenom_fr, newPrenom_fr);
        }

    }

    public String getNom_ar() {

        return nom_ar;
    }

    public void setNom_ar(String newNom_ar) {

        String oldNom_ar = this.nom_ar;
        this.nom_ar = newNom_ar;
        if (oldNom_ar != newNom_ar) {
            firePropertyChange(Citoyen.PROPERTY_NOM_AR, oldNom_ar, newNom_ar);
        }

    }

    public String getPrenom_ar() {

        return prenom_ar;
    }

    public void setPrenom_ar(String newPrenom_ar) {

        String oldPrenom_ar = this.prenom_ar;
        this.prenom_ar = newPrenom_ar;
        if (oldPrenom_ar != newPrenom_ar) {
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
        if (oldP_pere != newP_pere) {
            firePropertyChange(Citoyen.PROPERTY_P_PERE, oldP_pere, newP_pere);
        }

    }

    public String getNp_mere() {

        return np_mere;
    }

    public void setNp_mere(String newNp_mere) {

        String oldNp_mere = this.np_mere;
        this.np_mere = newNp_mere;
        if (oldNp_mere != newNp_mere) {
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

    public int getId_deces() {

        return id_deces;
    }

    public void setId_deces(int newId_deces) {

        int oldId_deces = this.id_deces;
        this.id_deces = newId_deces;
        if (oldId_deces != newId_deces) {
            firePropertyChange(Citoyen.PROPERTY_ID_DECES, oldId_deces, newId_deces);
        }

    }

    public Boolean getDate_est_presume() {

        return date_est_presume;
    }

    public void setDate_est_presume(Boolean newDate_est_presume) {

        Boolean oldDate_est_presume = this.date_est_presume;
        this.date_est_presume = newDate_est_presume;
        if (oldDate_est_presume != newDate_est_presume) {
            firePropertyChange(Citoyen.PROPERTY_DATE_EST_PRESUME, oldDate_est_presume, newDate_est_presume);
        }
    }

    public String getSit_famil() {

        return sit_famil;
    }

    public void setSit_famil(String newSit_famil) {

        String oldSit_famil = this.sit_famil;
        this.sit_famil = newSit_famil;
        if (oldSit_famil != newSit_famil) {
            firePropertyChange(Citoyen.PROPERTY_SIT_FAMIL, oldSit_famil, newSit_famil);
        }
    }
/*    @Override
    public Deces getDeces() {

        return deces;
    }

    @Override
    public void setDeces(Deces new_deces) {

        Deces old_deces = this.deces;
        this.deces = new_deces;
        if (old_deces != new_deces) {
            firePropertyChange(Citoyen.PROPERTY_DECES, old_deces, new_deces);
        }*/


/*        if (old_deces != new_deces) {
            firePropertyChange(Citoyen.PROPERTY_DECES, old_deces, new_deces);
            if(old_deces == null) {
                MyDaosCitoyen.setDecesInfo()
            }
        }*/
    }

    /**
     *
     * @param dec
     * @return
     * @should set deces infos and return dec record
     */
/*    public void setDecesInfos(Deces dec, Boolean isDied) {

        if (!isDied) {
            // delete id_deces record by idDeces and set idDec to "0"
            //Citoyen est en vie
            //TODO
            Boolean flag = MyDaosCitoyen.deleteDecesInfos(this.id_deces);
            if (flag) {
                this.setId_deces(0);
                MyDaosCitoyen.updateCitoyen(this);
            }


        } else {
            // Citoyen est mort
            dec.setId_dec(this.id_deces);
            dec = MyDaosCitoyen.setDecesInfo(dec.getId_dec(), dec.getDate_dec(),
            dec.getLieu_dec());
            this.setId_deces(dec.getId_dec());
            MyDaosCitoyen.updateCitoyen(this);
        }

    }

    public Deces getDecesInfos() {

        Deces dec = null;
        if (this.id_deces > 0) {
            try {
                dec = MyDaosCitoyen.getDecesInfos(this.id_deces);
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }

        return dec;

    }*/

