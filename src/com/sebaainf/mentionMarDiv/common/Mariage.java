package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.itf.mapping.AGetterMapping;
import com.jgoodies.common.bean.Bean;
import com.sebaainf.mentionMarDiv.citoyen.Citoyen;

import java.util.Date;

/**
 * Created by admin on 10/01/2015.
 */
public class Mariage extends Bean implements Comparable<Mariage> {

    Citoyen epoux;
    Citoyen epouse;
    String obs;
    private int id_mar;
    private int numact_mar;
    private Date date_mar;
    private int lieu_mar;
    private int id_epouse;
    private int id_epoux;

    public static final String PROPERTY_ID_MAR = "id_mar";
    public static final String PROPERTY_NUMACT_MAR = "numact_mar";
    public static final String PROPERTY_DATE_MAR = "date_mar";
    public static final String PROPERTY_LIEU_MAR = "lieu_mar";
    public static final String PROPERTY_ID_EPOUSE = "id_epouse";
    public static final String PROPERTY_ID_EPOUX = "id_epoux";

    public int getId_epoux() {

        return id_epoux;
    }

    public void setId_epoux(int id_epoux) {

        this.id_epoux = id_epoux;
    }


    public int getLieu_mar() {

        return lieu_mar;
    }

    public void setLieu_mar(int lieu_mar) {

        this.lieu_mar = lieu_mar;
    }

    public int getId_epouse() {

        return id_epouse;
    }

    public void setId_epouse(int id_epouse) {

        this.id_epouse = id_epouse;
    }

    public String getObs() {

        return obs;
    }

    public void setObs(String obs) {

        this.obs = obs;
    }

    public Citoyen getEpouse() {

        return epouse;
    }

    public void setEpouse(Citoyen epouse) {

        this.epouse = epouse;
    }

    public Citoyen getEpoux() {

        return epoux;
    }

    public void setEpoux(Citoyen epoux) {

        this.epoux = epoux;
    }

    public int getNumact_mar() {

        return numact_mar;
    }

    public void setNumact_mar(int numact_mar) {

        int oldValue = this.numact_mar;
        this.numact_mar = numact_mar;
        firePropertyChange("numact_mar", oldValue, this.numact_mar);
    }

    @AGetterMapping(databaseGenerated = true)


    @Override
    public int compareTo(Mariage mar) {

        return mar.getDate_mar().compareTo(this.getDate_mar());
    }

    @Override
    public int hashCode() {

        return new Integer(id_mar).hashCode();
    }

    @Override
    public boolean equals(Object aFam) {

        Mariage f = (Mariage) aFam;
        return new Integer((this.getId_mar())).equals(new Integer((f.getId_mar())));
    }

    public Date getDate_mar() {

        return date_mar;
    }

    public void setDate_mar(Date date_mar) {

        Date oldValue = this.date_mar;
        this.date_mar = date_mar;
        firePropertyChange("date_mar", oldValue, this.date_mar);
    }


    public int getId_mar() {

        return id_mar;
    }

    public void setId_mar(int id_mar) {

        this.id_mar = id_mar;
    }

}