package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.itf.PersistenceException;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.MyDaosCitoyen;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MyDaosMention;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by ${sebaainf.com} on 01/11/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class ReportedBean {

    private Citoyen cit;
    private Mention ment;

    private String marge = "زواج";


    public ReportedBean(Citoyen cit, Mention ment) {

        this.cit = cit;
        this.ment = ment;

    }

    public Citoyen getCit() {

        return cit;
    }

    public void setCit(Citoyen cit) {

        this.cit = cit;
    }

    public Mention getMent() {

        return ment;
    }

    public void setMent(Mention ment) {

        this.ment = ment;
    }

    public String getMarge() {

        return marge;
    }

    public void setMarge(String marge) {

        this.marge = marge;
    }




}
