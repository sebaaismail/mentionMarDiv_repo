package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ComponentValueModel;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 */
public final class CitoyenEditorModel extends PresentationModel<IPerson> {

    private ComponentValueModel id_cit;
    private ComponentValueModel nom_fr;
    private ComponentValueModel prenom_fr;
    private ComponentValueModel nom_ar;
    private ComponentValueModel prenom_ar;
    private ComponentValueModel date_naiss;
    private ComponentValueModel lieunaiss;
    private ComponentValueModel emploi;

    private ComponentValueModel p_pere;
    private ComponentValueModel np_mere;

    private ComponentValueModel date_est_presume;
    private ComponentValueModel est_masculin;


    // maybe delete this ???
    private Citoyen citoyen;

    /**
     * @param citoyen
     */
    public CitoyenEditorModel(Citoyen citoyen) {

        super(citoyen);
        this.citoyen = citoyen;
        initComponents();
    }


    /**
     *
     */
    private void initComponents() {

        nom_fr = this.getComponentModel(Citoyen.PROPERTY_NOM_FR);
        prenom_fr = this.getComponentModel(Citoyen.PROPERTY_PRENOM_FR);
        nom_ar = this.getComponentModel(Citoyen.PROPERTY_NOM_AR);
        prenom_ar = this.getComponentModel(Citoyen.PROPERTY_PRENOM_AR);
        id_cit = this.getComponentModel(Citoyen.PROPERTY_ID_CIT);
        date_naiss = this.getComponentModel(Citoyen.PROPERTY_DATE_NAISS);
        lieunaiss = this.getComponentModel(Citoyen.PROPERTY_LIEUNAISS);
        emploi = this.getComponentModel(Citoyen.PROPERTY_EMPLOI);

        p_pere = this.getComponentModel(Citoyen.PROPERTY_P_PERE);
        np_mere = this.getComponentModel(Citoyen.PROPERTY_NP_MERE);

        date_est_presume = this.getComponentModel(Citoyen.PROPERTY_DATENAISS_EST_PRESUME);
        est_masculin = this.getComponentModel(Citoyen.PROPERTY_EST_MASCULIN);

    }

    public ComponentValueModel getId_cit() {

        return id_cit;
    }

    public ComponentValueModel getNom_fr() {

        return nom_fr;
    }

    public ComponentValueModel getPrenom_fr() {

        return prenom_fr;
    }

    public ComponentValueModel getNom_ar() {

        return nom_ar;
    }

    public ComponentValueModel getPrenom_ar() {

        return prenom_ar;
    }

    public ComponentValueModel getDate_naiss() {

        return date_naiss;
    }

    public ComponentValueModel getLieunaiss() {

        return lieunaiss;
    }

    public ComponentValueModel getEmploi() {

        return emploi;
    }

    public ComponentValueModel getP_pere() {

        return p_pere;
    }

    public ComponentValueModel getNp_mere() {

        return np_mere;
    }


    public ComponentValueModel getDate_est_presume() {

        return date_est_presume;
    }

    public ComponentValueModel getEst_masculin() {

        return est_masculin;
    }

}
