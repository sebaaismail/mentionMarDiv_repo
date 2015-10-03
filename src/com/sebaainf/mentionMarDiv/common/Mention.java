package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.itf.PersistenceException;
import com.sebaainf.mentionMarDiv.citoyen.Citoyen;
import com.sebaainf.mentionMarDiv.persistance.MyDaos;
import com.sebaainf.mentionMarDiv.persistance.MyDaosCitoyen;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Created by admin on 10/01/2015.
 */
public class Mention {

    private String marge1 = "غير متوفي";
    private String marge2 = "غير متوفي";
    private SimpleDateFormat simpleDateFormat;

    private Citoyen citoyen;
    private Mariage selectedFamily;
    //this parameters need fonctions to get formated arabic string
    private String dateNaissFormated;
    private String lieuNaissFormated;
    private String dateMariageFormated;
    //private Citoyen selectedConjoint;

    private TreeSet<Mariage> families = new TreeSet<Mariage>();

    /**
     * Constructeur
     *
     * @param citoyen
     * @should return ficheFam for the citoyen citoyen with the last mariage as selectedFamily
     */
    public Mention(Citoyen citoyen) {

        try {
            if (citoyen != null) {

                // maybe is not need for that sentence ???? if we have a full citoyen
                this.citoyen = MyDaosCitoyen.getCitoyen(citoyen.getId_cit());

                TreeSet<Mariage> mariages = MyDaos.getFamilies(this.citoyen);


                for (Mariage mariage : mariages) {
                    mariage.setEpoux(this.getCitoyen());
                }
                this.setFamilies(mariages);

                this.setSelectedFamily(mariages.first());


                this.prepareTexts();
            } else {
                //message d'erreur TODO
            }

        } catch (PersistenceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Constructeur
     *
     * @param numact_mar
     * @param stringDate_mar
     * @param lieu_mar
     * @should return ficheFam for current mariage to the appropriete citoyen
     */
/*    public FicheFam(int numact_mar, String stringDate_mar, int lieu_mar) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date_mar = new Date(dateFormat.parse(stringDate_mar).getTime());


            Mariage mar = MyDaos.getMariage(numact_mar, date_mar, lieu_mar);

            if (mar != null) {
                Citoyen cit = mar.getEpoux();

                String sitfamil = cit.getSit_famil();
                if (cit.getSit_famil().equals("m")) {

                    this.setCitoyen(cit);

                    TreeSet<Mariage> mariages = MyDaos.getFamilies(this.citoyen);

                    for (Mariage mariage : mariages) {
                        mariage.setEpoux(this.getCitoyen());
                    }
                    this.setFamilies(mariages);

                    this.setSelectedFamily(mar);
                    this.prepareTexts();
*//*
                    if (conjoint.getSit_famil().equals("m")) {

                        /*//************************************
     this.setSelectedConjoint(conjoint);

     } else {
     //erreur conjoint nest pas marquée comme mariée ???
     }
     *//*
                } else {
                    //erreur citoyen nest pas marqué comme marié ???
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Constructeur
     *
     * @param numact_mar
     * @param date_mar
     * @param lieu_mar
     * @should return ficheFam for current mariage to the appropriete citoyen
     */
    public Mention(int numact_mar, Date date_mar, int lieu_mar) {


        Mariage mar = MyDaos.getMariage(numact_mar, date_mar, lieu_mar);

        if (mar != null) {
            Citoyen cit = mar.getEpoux();

            String sitfamil = cit.getSit_famil();
            if (cit.getSit_famil().equals("m")) {

                this.setCitoyen(cit);

                TreeSet<Mariage> mariages = MyDaos.getFamilies(this.citoyen);

                for (Mariage mariage : mariages) {
                    mariage.setEpoux(this.getCitoyen());
                }
                this.setFamilies(mariages);

                this.setSelectedFamily(mar);

                //this.prepareTexts();
/*
                    if (conjoint.getSit_famil().equals("m")) {

                        //************************************
                        this.setSelectedConjoint(conjoint);

                    } else {
                        //erreur conjoint nest pas marquée comme mariée ???
                    }
*/
            } else {
                //erreur citoyen nest pas marqué comme marié ???
            }
        }

    }

    public String getMarge1() {

        return marge1;
    }

    public void setMarge1(String marge1) {

        this.marge1 = marge1;
    }

    public String getMarge2() {

        return marge2;
    }

    public void setMarge2(String marge2) {

        this.marge2 = marge2;
    }

    public SimpleDateFormat getSimpleDateFormat() {

        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {

        this.simpleDateFormat = simpleDateFormat;
    }

    public Citoyen getCitoyen() {

        return citoyen;
    }

    public void setCitoyen(Citoyen citoyen) {

        this.citoyen = citoyen;
    }

    public Mariage getSelectedFamily() {

        return selectedFamily;
    }

    public void setSelectedFamily(Mariage selectedFamily) {

        this.selectedFamily = selectedFamily;
    }


    public TreeSet<Mariage> getFamilies() {

        return families;
    }

    public void setFamilies(TreeSet<Mariage> families) {

        this.families = families;
    }

    public void prepareTexts() {

        if (this.getCitoyen().getId_deces() == 0) {
            if (!this.getCitoyen().getEst_masculin()) {
                this.setMarge1(this.getMarge1() + "ة");
            }
        } else {
            //TODO citoyen est decede
        }

        if (this.getSelectedFamily().getEpouse().getId_deces() == 0) {
            if (!this.getSelectedFamily().getEpouse().getEst_masculin()) {
                this.setMarge2(this.getMarge2() + "ة");
            }
        } else {
            //TODO epouse est decedee
        }

        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("ar"));
        dfs.setMonths(new String[]{
                "جانفي",
                "فيفري",
                "مارس",
                "أفريل",
                "مــاي",
                "جــوان",
                "جــويلية",
                "أوت",
                "سبتمبـر",
                "أكتوبـر",
                "نوفمبـر",
                "ديسمبر",

        });
        this.setSimpleDateFormat(new SimpleDateFormat("dd MMMM yyyy", dfs));


    }


}
