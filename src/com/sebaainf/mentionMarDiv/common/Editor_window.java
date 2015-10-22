package com.sebaainf.mentionMarDiv.common;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.CitoyenEditorModel;
import com.sebaainf.mentionMarDiv.citoyenPackage.CitoyenValidator;
import com.sebaainf.mentionMarDiv.citoyenPackage.MyDaosCitoyen;
import com.sebaainf.mentionMarDiv.mentionPack.MentionEditorModel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * Created by ${sebaainf.com} on 22/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class Editor_window extends JFrame {

    private static Editor_window uniqueInstance;


    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();
    private JComponent messageLabel = ValidationResultViewFactory.createReportList(validationResultModel);

    private CitoyenEditorModel citModel;
    private MentionEditorModel mentModel;
    private JTextField nom_fr;
    private JTextField prenom_fr;
    private JTextField nom_ar;
    private JTextField prenom_ar;

    private JDatePickerImpl date_naiss;
    private JTextField lieunaiss;

    private JTextField p_pere;
    private JTextField np_mere;
    private JCheckBox dateNaiss_est_presume;


    private JButton validerButton;
    private JButton annulerButton;

    //***************************************************************


    public Editor_window(CitoyenEditorModel citModel, MentionEditorModel mentModel) {

        this.citModel = citModel;
        this.mentModel = mentModel;
        initComponents();

    }


    private void initComponents() {


        nom_fr = IsmComponentFactory.createTextField(citModel.getNom_fr());
        prenom_fr = IsmComponentFactory.createTextField(citModel.getPrenom_fr());
        nom_ar = IsmComponentFactory.createTextField(citModel.getNom_ar());
        prenom_ar = IsmComponentFactory.createTextField(citModel.getPrenom_ar());

        lieunaiss = IsmComponentFactory.createTextField(citModel.getLieunaiss());

        date_naiss = IsmComponentFactory.createDatePickerImpl(citModel, "yyyy/MM/dd");
        dateNaiss_est_presume = IsmComponentFactory.createCheckBox(
                citModel.getDate_est_presume(), "* ?????");

        // we use createBooleanNegator to return the inverse of Boolean
        //id_deces = IsmComponentFactory.createCheckBox(citModel.getId_deces(), "* ??? ??? ??????");

        //*

        //*/

        p_pere = IsmComponentFactory.createTextField(citModel.getP_pere());
        np_mere = IsmComponentFactory.createTextField(citModel.getNp_mere());

        // HorizontalAlignment of JTextField
        nom_ar.setHorizontalAlignment(JTextField.RIGHT);
        prenom_ar.setHorizontalAlignment(JTextField.RIGHT);
        p_pere.setHorizontalAlignment(JTextField.RIGHT);
        np_mere.setHorizontalAlignment(JTextField.RIGHT);

        /*
        if ((Integer)(citModel.getId_deces().getValue()) > 0) {
            id_deces.setSelected(false);
            decesInfosEnable(true);
        } else {
            id_deces.setSelected(true);
            decesInfosEnable(false);
        }
        //*/

        validerButton = new JButton(new CitoyenValidationAction());
        annulerButton = new JButton("Annuler");
/*        messageLabel.getComponent(1)
        .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);*/
        // TODO later alignement
        ((JScrollBar) messageLabel.getComponent(1)).setAlignmentY(JScrollBar.RIGHT_ALIGNMENT);


    }


    private JComponent buildContent() {

        MyCommonUtils.setListComponentsEnabled(getListComponents(), true);


/*
        FormLayout layout = new FormLayout(

                //      textField         label         textField           label     //
                "  4dlu,pref:grow,4dlu,right:pref,3dlu ,pref:grow  ,4dlu ,right:pref"//, //columns
                //      --------          ------        ---------           -------
        );
*/

        CellConstraints cc = new CellConstraints();

        //JComponent form =

        return FormBuilder.create()
                .columns("40dlu,fill:default,8dlu,left:pref,100dlu " +
                        ",150dlu, 8dlu, fill:default, 40dlu")
                .rows("40dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu," +
                        "p,6dlu,p,6dlu,p,40dlu,p,40dlu,p")
                .columnGroups(new int[][]{{2, 6}, {4, 8}})
                .rowGroups(new int[][]{{12, 14}})
                .add("??????? :").xy(8, 2)
                .add(nom_ar).xy(6, 2)
                .add(": Nom").xy(4, 2)
                .add(nom_fr).xy(2, 2)
                .add("??????? :").xy(8, 4)
                .add(prenom_ar).xy(6, 4)
                .add(": Prenom").xy(4, 4)
                .add(prenom_fr).xy(2, 4)
                .add("????? ???????? :").xy(8, 6)
                .add(date_naiss).xy(6, 6)
                .add(dateNaiss_est_presume).at(cc.xy(6, 8, CellConstraints.RIGHT,
                        CellConstraints.CENTER))
                .add("???? ???????? :").xy(4, 6)
                .add("*********").xy(2, 6)
                .add("*********").xy(2, 8)
                .add("??? ???? ???? :").xy(8, 10)
                .add(p_pere).xy(6, 10)
                .add("??? ???? ???? :").xy(4, 10)
                .add(np_mere).xy(2, 10)
                .add("????? :").xy(4, 12)
                .add("*********").at(cc.xy(2, 12, CellConstraints.RIGHT,
                        CellConstraints.CENTER))
                .add("*********").at(cc.xy(2, 14, CellConstraints.RIGHT,
                        CellConstraints.CENTER))
                .add("*********").xy(6, 12)
                .add("*********").xy(8, 16)
                .add("*********").xy(6, 16)
                .add("*********").xy(4, 16)
                .add("*********").xy(2, 16)
                .add(messageLabel).xy(4, 20)
                .addBar(validerButton, annulerButton).xy(6, 18)
                        //.add(validerButton).xywh(4, 18,2,1)
                        //.add(annulerButton).xy(6, 18)


                        // TODO buttons ok annuler ?

                .build();


    }

    public JComponent showDialog(EventObject e) {

        return buildContent();

    }

    public ArrayList<JComponent> getListComponents() {

        ArrayList<JComponent> list = new ArrayList<JComponent>();
        list.add(nom_ar);
        list.add(prenom_ar);
        list.add(nom_fr);
        list.add(prenom_fr);
        list.add(date_naiss);

        list.add(p_pere);
        list.add(np_mere);

        return list;

    }


    private class CitoyenValidationAction extends AbstractAction {


        CitoyenValidationAction() {

            super("Valider");

        }

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */


        @Override
        public void actionPerformed(ActionEvent e) {

            validationResultModel.setResult(ValidationResult.EMPTY);
            CitoyenValidator validator = new CitoyenValidator(citModel);
            ValidationResult result = validator.validate((Citoyen) citModel.getBean());
            validationResultModel.setResult(result);

            if (!result.hasErrors()) {
                //mettre a jour data base citoyen infos
                MyDaosCitoyen.updateCitoyen((Citoyen) citModel.getBean());
                //((Citoyen) citModel.getBean()).setDeces(citModel.getBean().getDeces());
            }

        }
    }

}
