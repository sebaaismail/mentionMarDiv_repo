package com.sebaainf.mentionMarDiv.mentionPack;

import com.jenkov.db.itf.mapping.AGetterMapping;
import com.jgoodies.common.bean.Bean;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;

import java.util.Date;

/**
 * Created by admin on 10/01/2015.
 */
public class Mention extends Bean implements Comparable<Mention> {

    // always becarfull declare boolean as boolean with small b miniscul , not Boolean
    // because Boolean will causes a problem in binding part

    public static final String PROPERTY_ID_MENT = "id_ment";
    public static final String PROPERTY_ID_CIT = "id_cit";
    public static final String PROPERTY_NUMACT_MAR = "numact_mar";
    public static final String PROPERTY_DATE_MAR = "date_mar";
    public static final String PROPERTY_DATE_ACTE_MAR = "date_acte_mar";
    public static final String PROPERTY_ANNEE_MAR = "annee_mar";
    public static final String PROPERTY_ACTE_ECRIT_PAR = "acte_ecrit_par";
    public static final String PROPERTY_NP_CONJ_AR = "np_conj_ar";
    public static final String PROPERTY_NP_CONJ_FR = "np_conj_fr";
    public static final String PROPERTY_EST_DIVORCE = "est_divorce";
    public static final String PROPERTY_TRIBUNAL_DIV = "tribunal_div";
    public static final String PROPERTY_DATE_DIV = "date_div";
    private int id_ment = -1; // default id_ment is -1 to help us when we will added new mention and when we update a mention
    private int id_cit;
    private int numact_mar;
    private Date date_mar;
    private Date date_acte_mar;
    private int annee_mar;
    private String acte_ecrit_par = "ضابط الحالة المدنية";
    private String np_conj_ar;
    private String np_conj_fr;
    private boolean est_divorce;
    private String tribunal_div;
    private Date date_div;


    public Mention() {

    }

    public Mention(Citoyen cit) {

        this.setId_cit(cit.getId_cit());
    }

    @AGetterMapping(databaseGenerated = true)

    public int getId_cit() {

        return id_cit;
    }

    public void setId_cit(int newId_cit) {

        int oldId_cit = this.id_cit;
        this.id_cit = newId_cit;
        if (oldId_cit != newId_cit) {
            firePropertyChange(PROPERTY_ID_CIT, oldId_cit, newId_cit);
        }

    }

    public int getNumact_mar() {

        return numact_mar;
    }

    public void setNumact_mar(int newNumact_mar) {

        int oldValue = this.numact_mar;
        this.numact_mar = newNumact_mar;
        firePropertyChange(PROPERTY_NUMACT_MAR, oldValue, newNumact_mar);
    }


    @Override
    public int compareTo(Mention ment) {

        return ment.getDate_mar().compareTo(this.getDate_mar());
    }

    @Override
    public int hashCode() {

        return new Integer(id_ment).hashCode();
    }

    @Override
    public boolean equals(Object aMent) {

        Mention m = (Mention) aMent;
        return new Integer((this.getId_ment())).equals(new Integer((m.getId_ment())));
    }

    public Date getDate_mar() {

        return date_mar;
    }

    public void setDate_mar(Date newDate_mar) {

        Date oldValue = this.date_mar;
        this.date_mar = newDate_mar;
        firePropertyChange(PROPERTY_DATE_MAR, oldValue, newDate_mar);
    }


    public int getId_ment() {

        return id_ment;
    }

    public void setId_ment(int new_id_ment) {

        int oldValue = this.id_ment;
        this.id_ment = new_id_ment;
        firePropertyChange(PROPERTY_ID_MENT, oldValue, new_id_ment);
    }

    public Date getDate_acte_mar() {

        return date_acte_mar;
    }

    public void setDate_acte_mar(Date new_date_acte_mar) {

        Date oldValue = this.date_acte_mar;
        this.date_acte_mar = new_date_acte_mar;
        firePropertyChange(PROPERTY_DATE_ACTE_MAR, oldValue, new_date_acte_mar);
    }

    public int getAnnee_mar() {

        return annee_mar;
    }

    public void setAnnee_mar(int new_annee_mar) {

        int oldValue = this.annee_mar;
        this.annee_mar = new_annee_mar;
        firePropertyChange(PROPERTY_ANNEE_MAR, oldValue, new_annee_mar);
    }

    public String getActe_ecrit_par() {

        return acte_ecrit_par;
    }

    public void setActe_ecrit_par(String new_acte_ecrit_par) {

        String oldValue = this.acte_ecrit_par;
        this.acte_ecrit_par = new_acte_ecrit_par;
        firePropertyChange(PROPERTY_ACTE_ECRIT_PAR, oldValue, new_acte_ecrit_par);
    }

    public String getNp_conj_ar() {

        return np_conj_ar;
    }

    public void setNp_conj_ar(String new_np_conj_ar) {

        String oldValue = this.np_conj_ar;
        this.np_conj_ar = new_np_conj_ar;
        firePropertyChange(PROPERTY_NP_CONJ_AR, oldValue, new_np_conj_ar);
    }

    public String getNp_conj_fr() {

        return np_conj_fr;
    }

    public void setNp_conj_fr(String new_np_conj_fr) {

        String oldValue = this.np_conj_fr;
        this.np_conj_fr = new_np_conj_fr;
        firePropertyChange(PROPERTY_NP_CONJ_FR, oldValue, new_np_conj_fr);
    }

    public boolean getEst_divorce() {

        return est_divorce;
    }

    public void setEst_divorce(boolean new_est_divorce) {


        boolean oldValue = this.est_divorce;
        this.est_divorce = new_est_divorce;
        if (oldValue != new_est_divorce) {
            firePropertyChange(PROPERTY_EST_DIVORCE, oldValue, new_est_divorce);
        }
    }

    public String getTribunal_div() {

        return tribunal_div;
    }

    public void setTribunal_div(String new_tribunal_div) {

        String oldValue = this.tribunal_div;
        this.tribunal_div = new_tribunal_div;
        firePropertyChange(PROPERTY_TRIBUNAL_DIV, oldValue, new_tribunal_div);
    }

    public Date getDate_div() {

        return date_div;
    }

    public void setDate_div(Date new_date_div) {

        Date oldValue = this.date_div;
        this.date_div = new_date_div;
        firePropertyChange(PROPERTY_DATE_DIV, oldValue, new_date_div);
    }

}