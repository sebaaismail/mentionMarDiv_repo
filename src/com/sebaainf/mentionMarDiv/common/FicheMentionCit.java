package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.itf.PersistenceException;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MyDaosMention;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Created by admin on 21/10/2015.
 */
public class FicheMentionCit {


    private String margeMention = "زواج";
    private SimpleDateFormat simpleDateFormat;

    private Citoyen citoyen;
    private Mention selectedMention;

    /**
     * citoyen and mention full objects may be passed into this new BigObject
     * @param cit
     * @param mention
     */

    public FicheMentionCit(Citoyen cit ,Mention mention) {

        this.citoyen = cit;
        this.selectedMention = mention;

    }

    public String getMargeMention() {

        return margeMention;
    }

    public void setMargeMention(String marge) {

        this.margeMention = marge;
    }

    public Citoyen getCitoyen() {

        return citoyen;
    }

    public void setCitoyen(Citoyen citoyen) {

        this.citoyen = citoyen;
    }

    public Mention getSelectedMention() {

        return selectedMention;
    }

    public void setSelectedFamily(Mention mention) {

        this.selectedMention = mention;
    }

}
