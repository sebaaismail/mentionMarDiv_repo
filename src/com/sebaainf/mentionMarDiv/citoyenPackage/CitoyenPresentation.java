package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ComponentValueModel;
import com.jgoodies.binding.value.ValueModel;

/**
 * Created by ${sebaainf.com} on 22/10/2015.
 */
public class CitoyenPresentation {

    // TODO à modifier

    final public Citoyen citoyen;


    private ComponentValueModel id_cit;
    private ComponentValueModel nom_fr;
    private ComponentValueModel prenom_fr;
    private ComponentValueModel nom_ar;
    private ComponentValueModel prenom_ar;
    private ComponentValueModel date_naiss;
    private ComponentValueModel code_lieunaiss;
    //private ComponentValueModel sit_famil;
    private ValueModel sit_famil;
    private ComponentValueModel p_pere;
    private ComponentValueModel np_mere;
    private ComponentValueModel est_masculin;
    private ComponentValueModel id_deces;
    private ComponentValueModel date_est_presume;


    public CitoyenPresentation(Citoyen citoyen) {

        this.citoyen = citoyen;
        PresentationModel citoyenAdapter = new PresentationModel(citoyen);


        nom_fr = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_NOM_FR);
        prenom_fr = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_PRENOM_FR);
        nom_ar = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_NOM_AR);
        prenom_ar = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_PRENOM_AR);
        id_cit = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_ID_CIT);
        date_naiss = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_DATE_NAISS);
        code_lieunaiss = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_CODE_LIEUNAISS);
        sit_famil = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_SIT_FAMIL);
        p_pere = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_P_PERE);
        np_mere = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_NP_MERE);
        est_masculin = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_EST_MASCULIN);
        id_deces = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_ID_DECES);
        date_est_presume = citoyenAdapter.getComponentModel(Citoyen.PROPERTY_DATE_EST_PRESUME);


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

    public ComponentValueModel getCode_lieunaiss() {

        return code_lieunaiss;
    }

    public ValueModel getSit_famil() {

        return sit_famil;
    }


    public ComponentValueModel getP_pere() {

        return p_pere;
    }

    public ComponentValueModel getNp_mere() {

        return np_mere;
    }

    public ComponentValueModel getEst_masculin() {

        return est_masculin;
    }

    public ComponentValueModel getId_deces() {

        return id_deces;
    }

    public ComponentValueModel getDate_est_presume() {

        return date_est_presume;
    }
}
