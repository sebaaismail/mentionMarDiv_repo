package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ComponentValueModel;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 */
public final class CitoyenEditorModel extends PresentationModel<IPerson> {

    private ValueModel id_cit;
    private ValueModel nom_fr;
    private ValueModel prenom_fr;
    private ValueModel nom_ar;
    private ValueModel prenom_ar;

    private ValueModel date_naiss;
    private ValueModel lieunaiss;
    private ValueModel daira_naiss;
    private ValueModel wilaya_naiss;


    private ValueModel emploi;

    private ValueModel p_pere;
    private ValueModel np_mere;

    private ValueModel dateNaiss_est_presume;
    private ValueModel est_masculin;


    // maybe delete this ???
    private Citoyen citoyen;

    /**
     * @param citoyen
     */
    public CitoyenEditorModel(Citoyen citoyen) {


        super(citoyen);
        //this.setTriggerChannel(new Trigger());
        this.citoyen = citoyen;
        initComponents();
    }


    /**
     *
     */
    private void initComponents() {

        nom_fr = this.getBufferedModel(Citoyen.PROPERTY_NOM_FR);
        prenom_fr = this.getBufferedModel(Citoyen.PROPERTY_PRENOM_FR);
        nom_ar = this.getBufferedModel(Citoyen.PROPERTY_NOM_AR);
        prenom_ar = this.getBufferedModel(Citoyen.PROPERTY_PRENOM_AR);
        id_cit = this.getBufferedModel(Citoyen.PROPERTY_ID_CIT);

        date_naiss = this.getBufferedModel(Citoyen.PROPERTY_DATE_NAISS);
        //date_naiss = this.getModel(Citoyen.PROPERTY_DATE_NAISS);
        lieunaiss = this.getBufferedModel(Citoyen.PROPERTY_LIEUNAISS);
        daira_naiss = this.getBufferedModel(Citoyen.PROPERTY_DAIRA_NAISS);
        wilaya_naiss = this.getBufferedModel(Citoyen.PROPERTY_WILAYA_NAISS);


        emploi = this.getBufferedModel(Citoyen.PROPERTY_EMPLOI);

        p_pere = this.getBufferedModel(Citoyen.PROPERTY_P_PERE);
        np_mere = this.getBufferedModel(Citoyen.PROPERTY_NP_MERE);

        dateNaiss_est_presume = this.getBufferedModel(Citoyen.PROPERTY_DATENAISS_EST_PRESUME);
        est_masculin = this.getBufferedModel(Citoyen.PROPERTY_EST_MASCULIN);

    }

    public ValueModel getId_cit() {

        return id_cit;
    }

    public ValueModel getNom_fr() {

        return nom_fr;
    }

    public ValueModel getPrenom_fr() {

        return prenom_fr;
    }

    public ValueModel getNom_ar() {

        return nom_ar;
    }

    public ValueModel getPrenom_ar() {

        return prenom_ar;
    }

    public ValueModel getDate_naiss() {

        return date_naiss;
    }

    public ValueModel getLieunaiss() {

        return lieunaiss;
    }

    public ValueModel getDaira_naiss() {

        return daira_naiss;
    }

    public ValueModel getWilaya_naiss() {

        return wilaya_naiss;
    }

    public ValueModel getEmploi() {

        return emploi;
    }

    public ValueModel getP_pere() {

        return p_pere;
    }

    public ValueModel getNp_mere() {

        return np_mere;
    }


    public ValueModel getDateNaiss_est_presume() {

        return dateNaiss_est_presume;
    }

    public ValueModel getEst_masculin() {

        return est_masculin;
    }

}
