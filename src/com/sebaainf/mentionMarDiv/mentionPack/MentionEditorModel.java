package com.sebaainf.mentionMarDiv.mentionPack;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ComponentValueModel;
import com.jgoodies.binding.value.ValueModel;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 */
public final class MentionEditorModel extends PresentationModel {

    private ValueModel id_ment;
    private ValueModel id_cit;

    private ValueModel numact_mar;
    private ValueModel date_mar;
    private ValueModel date_acte_mar;
    private ValueModel annee_mar;
    private ValueModel acte_ecrit_par;

    private ValueModel np_conj_ar;
    private ValueModel np_conj_fr;

    private ValueModel est_divorce;
    private ValueModel tribunal_div;
    private ValueModel date_div;


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

        id_ment = this.getBufferedModel(Mention.PROPERTY_ID_MENT);
        id_cit = this.getBufferedModel(Mention.PROPERTY_ID_CIT);
        numact_mar = this.getBufferedModel(Mention.PROPERTY_NUMACT_MAR);
        date_mar = this.getBufferedModel(Mention.PROPERTY_DATE_MAR);
        id_cit = this.getBufferedModel(Mention.PROPERTY_ID_CIT);
        date_acte_mar = this.getBufferedModel(Mention.PROPERTY_DATE_ACTE_MAR);
        annee_mar = this.getBufferedModel(Mention.PROPERTY_ANNEE_MAR);
        acte_ecrit_par = this.getBufferedModel(Mention.PROPERTY_ACTE_ECRIT_PAR);

        np_conj_ar = this.getBufferedModel(Mention.PROPERTY_NP_CONJ_AR);
        np_conj_fr = this.getBufferedModel(Mention.PROPERTY_NP_CONJ_FR);

        est_divorce = this.getBufferedModel(Mention.PROPERTY_EST_DIVORCE);
        tribunal_div = this.getBufferedModel(Mention.PROPERTY_TRIBUNAL_DIV);
        date_div = this.getBufferedModel(Mention.PROPERTY_DATE_DIV);

    }


    public ValueModel getId_ment() {

        return id_ment;
    }

    public ValueModel getId_cit() {

        return id_cit;
    }

    public ValueModel getNumact_mar() {

        return numact_mar;
    }

    public ValueModel getDate_mar() {

        return date_mar;
    }

    public ValueModel getDate_acte_mar() {

        return date_acte_mar;
    }

    public ValueModel getAnnee_mar() {

        return annee_mar;
    }

    public ValueModel getActe_ecrit_par() {

        return acte_ecrit_par;
    }

    public ValueModel getNp_conj_ar() {

        return np_conj_ar;
    }

    public ValueModel getNp_conj_fr() {

        return np_conj_fr;
    }

    public ValueModel getEst_divorce() {

        return est_divorce;
    }

    public ValueModel getTribunal_div() {

        return tribunal_div;
    }

    public ValueModel getDate_div() {

        return date_div;
    }

    public Mention getMention() {

        return mention;
    }


}
