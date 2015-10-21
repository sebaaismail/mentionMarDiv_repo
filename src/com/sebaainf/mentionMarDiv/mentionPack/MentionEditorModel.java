package com.sebaainf.mentionMarDiv.mentionPack;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ComponentValueModel;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.IPerson;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 */
public final class MentionEditorModel extends PresentationModel {

    private ComponentValueModel id_ment;
    private ComponentValueModel id_cit;

    private ComponentValueModel numact_mar;
    private ComponentValueModel date_mar;
    private ComponentValueModel date_acte_mar;
    private ComponentValueModel annee_mar;
    private ComponentValueModel acte_ecrit_par;

    private ComponentValueModel np_conj_ar;
    private ComponentValueModel np_conj_fr;

    private ComponentValueModel est_divorce;
    private ComponentValueModel tribunal_div;
    private ComponentValueModel date_div;


    // maybe delete this ???
    private Mention mention;

    /**
     * @param mention
     */
    public MentionEditorModel(Mention mention) {

        super(mention);
        this.mention = mention;
        initComponents();
    }


    /**
     *
     */
    private void initComponents() {

        id_ment = this.getComponentModel(Mention.PROPERTY_ID_MENT);
        id_cit = this.getComponentModel(Mention.PROPERTY_ID_CIT);
        numact_mar = this.getComponentModel(Mention.PROPERTY_NUMACT_MAR);
        date_mar = this.getComponentModel(Mention.PROPERTY_DATE_MAR);
        id_cit = this.getComponentModel(Mention.PROPERTY_ID_CIT);
        date_acte_mar = this.getComponentModel(Mention.PROPERTY_DATE_ACTE_MAR);
        annee_mar = this.getComponentModel(Mention.PROPERTY_ANNEE_MAR);
        acte_ecrit_par = this.getComponentModel(Mention.PROPERTY_ACTE_ECRIT_PAR);

        np_conj_ar = this.getComponentModel(Mention.PROPERTY_NP_CONJ_AR);
        np_conj_fr = this.getComponentModel(Mention.PROPERTY_NP_CONJ_FR);

        est_divorce = this.getComponentModel(Mention.PROPERTY_EST_DIVORCE);
        tribunal_div = this.getComponentModel(Mention.PROPERTY_TRIBUNAL_DIV);
        date_div = this.getComponentModel(Mention.PROPERTY_DATE_DIV);

    }


    public ComponentValueModel getId_ment() {

        return id_ment;
    }

    public ComponentValueModel getId_cit() {

        return id_cit;
    }

    public ComponentValueModel getNumact_mar() {

        return numact_mar;
    }

    public ComponentValueModel getDate_mar() {

        return date_mar;
    }

    public ComponentValueModel getDate_acte_mar() {

        return date_acte_mar;
    }

    public ComponentValueModel getAnnee_mar() {

        return annee_mar;
    }

    public ComponentValueModel getActe_ecrit_par() {

        return acte_ecrit_par;
    }

    public ComponentValueModel getNp_conj_ar() {

        return np_conj_ar;
    }

    public ComponentValueModel getNp_conj_fr() {

        return np_conj_fr;
    }

    public ComponentValueModel getEst_divorce() {

        return est_divorce;
    }

    public ComponentValueModel getTribunal_div() {

        return tribunal_div;
    }

    public ComponentValueModel getDate_div() {

        return date_div;
    }

    public Mention getMention() {

        return mention;
    }


}
