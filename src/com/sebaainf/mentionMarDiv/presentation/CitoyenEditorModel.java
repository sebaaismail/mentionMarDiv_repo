package com.sebaainf.mentionMarDiv.presentation;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ComponentValueModel;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.common.collect.ArrayListModel;
import com.sebaainf.mentionMarDiv.citoyen.Citoyen;
import com.sebaainf.mentionMarDiv.citoyen.IPerson;

import javax.swing.*;

/**
 * Created by ${sebaainf.com} on 14/03/2015.
 */
public final class CitoyenEditorModel extends PresentationModel<IPerson> {

    private ComponentValueModel id_cit;
    private ComponentValueModel nom_fr;
    private ComponentValueModel prenom_fr;
    private ComponentValueModel nom_ar;
    private ComponentValueModel prenom_ar;
    private ComponentValueModel date_naiss;
    private ComponentValueModel code_lieunaiss;
    private ValueModel sit_famil;

    private SelectionInList selList_sit_famil;
    private ComponentValueModel p_pere;
    private ComponentValueModel np_mere;

    //private ComponentValueModel id_deces;






    private ComponentValueModel date_est_presume;




    //private RadioButtonAdapter masculinAdapter;
    //private RadioButtonAdapter femininAdapter;



    public ComponentValueModel getEst_masculin() {

        return est_masculin;
    }

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
        code_lieunaiss = this.getComponentModel(Citoyen.PROPERTY_CODE_LIEUNAISS);
        sit_famil = this.getComponentModel(Citoyen.PROPERTY_SIT_FAMIL);

        p_pere = this.getComponentModel(Citoyen.PROPERTY_P_PERE);
        np_mere = this.getComponentModel(Citoyen.PROPERTY_NP_MERE);
        //id_deces = this.getComponentModel(Citoyen.PROPERTY_ID_DECES);
        //deces = this.getComponentModel(Citoyen.PROPERTY_DECES);
        date_est_presume = this.getComponentModel(Citoyen.PROPERTY_DATE_EST_PRESUME);
        est_masculin = this.getComponentModel(Citoyen.PROPERTY_EST_MASCULIN);

        //enableLieuDeces = new ValueHolder((new Integer(
          //      (this.id_deces.getValue().toString()) )> 0));

        // Radio Button for sex of citoyen masculin / feminin ?
        /*
        ComponentValueModel est_masculin
                = this.getComponentModel(Citoyen.PROPERTY_EST_MASCULIN);
        masculinAdapter = new RadioButtonAdapter(est_masculin, true);
        femininAdapter = new RadioButtonAdapter(est_masculin, false);
        //*/





    }

    public SelectionInList getSelList_sit_famil() {
        // ******* preparing selList_sit_famil sit_fam

        final ArrayListModel<SituationFam> situations_fam = new ArrayListModel();
        situations_fam.add(new SituationFam("c", "Celibataire"));
        situations_fam.add(new SituationFam("m", "Marié"));
        situations_fam.add(new SituationFam("d", "Divorcé"));
        situations_fam.add(new SituationFam("v", "Veuf"));

        // set the presentation model up to the first bean.
        SituationFam theSitFam = null;
        for (SituationFam obj : situations_fam) {
            if (obj.sitFam.equals(this.sit_famil.getValue())) {
                theSitFam = obj;
                break;
            }
        }
        final PresentationModel beanPresentationModel = new PresentationModel(theSitFam);

        // create a property adapter for the presentation model 'bean' property.
        ValueModel beanProperty = new PropertyAdapter(beanPresentationModel, "bean");

        // TODO when set mairié after changing selected comboBox client dont update ???
        //* good
        PropertyConnector.connectAndUpdate(beanPresentationModel.getModel("sitFam"),
                this.citoyen, Citoyen.PROPERTY_SIT_FAMIL);
        //  TODO but button modifier citoyen causes problem ???

        return new SelectionInList((ListModel) situations_fam, beanProperty);
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



    public ComponentValueModel getDate_est_presume() {

        return date_est_presume;
    }



    //**********************************************************************
    // INNER Class

    public class SituationFam extends Model {


        private String sitFam = "";
        private String text = "";

        public SituationFam(String sitFam, String text) {

            this.sitFam = sitFam;
            this.text = text;
        }

        public String getSitFam() {

            return sitFam;
        }

        public void setSitFam(String sitFam) {

            String oldValue = this.sitFam;
            this.sitFam = sitFam;
            //if (oldValue != sitFam) {
            this.firePropertyChange("sitFam", oldValue, sitFam);
            System.out.println("oldValue = " + oldValue + " new = " + sitFam);
            //}
        }

        public String getText() {

            return text;
        }

        public void setText(String text) {

            String oldValue = this.text;
            this.text = text;
            //if (oldValue != text) {
            this.firePropertyChange("text", oldValue, text);
            //}

        }

        public String toString() {

            return this.text;
        }

    }

}
