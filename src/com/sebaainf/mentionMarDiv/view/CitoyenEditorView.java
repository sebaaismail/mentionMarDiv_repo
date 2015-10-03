package com.sebaainf.mentionMarDiv.view;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;
import com.sebaainf.mentionMarDiv.citoyen.Citoyen;
import com.sebaainf.mentionMarDiv.common.MyCommonUtils;
import com.sebaainf.mentionMarDiv.persistance.MyDaosCitoyen;
import com.sebaainf.mentionMarDiv.presentation.CitoyenEditorModel;
import com.sebaainf.mentionMarDiv.presentation.CitoyenValidator;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * Created by ${sebaainf.com} on 14/03/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class CitoyenEditorView {

    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();
    private JComponent messageLabel = ValidationResultViewFactory.createReportList(validationResultModel);

    private final CitoyenEditorModel model;
    private JTextField nom_fr;
    private JTextField prenom_fr;
    private JTextField nom_ar;
    private JTextField prenom_ar;

    private JDatePickerImpl date_naiss;
    private JComboBox code_lieunaiss;
    private JComboBox sit_famil;
    private JRadioButton masculinChoice;
    private JRadioButton femininChoice;

    private JTextField p_pere;
    private JTextField np_mere;
    private JCheckBox date_est_presume;

    private JCheckBox id_deces;
    private JDatePickerImpl date_deces;
    private JTextField lieu_deces;
    private JLabel labelDateDeces;
    private JLabel labelLieuDeces;


    private JButton validerButton;
    private JButton annulerButton;


    CitoyenEditorView(CitoyenEditorModel model) {

        this.model = model;
        initComponents();

    }

    private void initComponents() {


        nom_fr = BasicComponentFactory.createTextField(model.getNom_fr());
        prenom_fr = BasicComponentFactory.createTextField(model.getPrenom_fr());
        nom_ar = BasicComponentFactory.createTextField(model.getNom_ar());
        prenom_ar = BasicComponentFactory.createTextField(model.getPrenom_ar());

        //code_lieunaiss = BasicComponentFactory.createComboBox(model.getCode_lieunaiss());
        sit_famil = IsmComponentFactory.createComboBox(model.getSelList_sit_famil());

        masculinChoice = IsmComponentFactory.createRadioButton(model.getEst_masculin()
                , true, "ذكر");
        femininChoice = IsmComponentFactory.createRadioButton(model.getEst_masculin()
                , false, "أنثى");
        masculinChoice.setHorizontalTextPosition(SwingConstants.LEFT);
        femininChoice.setHorizontalTextPosition(SwingConstants.LEFT);

        date_naiss = IsmComponentFactory.createDatePickerImpl(model, "yyyy/MM/dd");
        date_est_presume = IsmComponentFactory.createCheckBox(
                model.getDate_est_presume(), "* مفترض");

        // we use createBooleanNegator to return the inverse of Boolean
        //id_deces = IsmComponentFactory.createCheckBox(model.getId_deces(), "* على قيد الحياة");

        //*

        //*/

        p_pere = IsmComponentFactory.createTextField(model.getP_pere());
        np_mere = IsmComponentFactory.createTextField(model.getNp_mere());


        // HorizontalAlignment of JTextField
        nom_ar.setHorizontalAlignment(JTextField.RIGHT);
        prenom_ar.setHorizontalAlignment(JTextField.RIGHT);
        p_pere.setHorizontalAlignment(JTextField.RIGHT);
        np_mere.setHorizontalAlignment(JTextField.RIGHT);

        // code for configure infos deces components behavior

        labelDateDeces = IsmComponentFactory.createLabel(new ValueHolder());
        labelDateDeces.setText("تاريخ الوفاة :");

        labelLieuDeces = IsmComponentFactory.createLabel(new ValueHolder());
        labelLieuDeces.setText("مكان الوفاة :");


        date_deces = IsmComponentFactory.createDatePickerDecesImpl(model, "yyyy/MM/dd");;

        //BeanChannel
        /*
        TODO deleted
        BeanAdapter<Deces> adapter = new BeanAdapter<Deces>(model.getDeces());
        lieu_deces = IsmComponentFactory.createTextField(
                adapter.getValueModel(Deces.PROPERTY_LIEU_DEC));
*/
        id_deces = new JCheckBox("* على قيد الحياة");

        id_deces.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {

                    decesInfosEnable(true);

                } else if (e.getStateChange() == ItemEvent.SELECTED) {

                    decesInfosEnable(false);

                }
            }
        });

        /*
        if ((Integer)(model.getId_deces().getValue()) > 0) {
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
        ((JScrollBar)messageLabel.getComponent(1)).setAlignmentY(JScrollBar.RIGHT_ALIGNMENT);




    }


    private JComponent buildContent() {

        MyCommonUtils.setListComponentsEnabled(getListComponents(),true);


/*
        FormLayout layout = new FormLayout(

                //      textField         label         textField           label     //
                "  4dlu,pref:grow,4dlu,right:pref,3dlu ,pref:grow  ,4dlu ,right:pref"//, //columns
                //      --------          ------        ---------           -------
        );
*/

        CellConstraints cc = new CellConstraints();

        //JComponent form =

        return                FormBuilder.create()
                .columns("40dlu,fill:default,8dlu,left:pref,100dlu " +
                        ",150dlu, 8dlu, fill:default, 40dlu")
                .rows("40dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu," +
                        "p,6dlu,p,6dlu,p,40dlu,p,40dlu,p")
                .columnGroups(new int[][]{{2, 6}, {4, 8}})
                .rowGroups(new int[][]{{12, 14}})
                .add("اللقــب :").xy(8, 2)
                .add(nom_ar).xy(6, 2)
                .add(": Nom").xy(4, 2)
                .add(nom_fr).xy(2, 2)
                .add("الإســم :").xy(8, 4)
                .add(prenom_ar).xy(6, 4)
                .add(": Prenom").xy(4, 4)
                .add(prenom_fr).xy(2, 4)
                .add("تاريخ الإزدياد :").xy(8, 6)
                .add(date_naiss).xy(6, 6)
                .add(date_est_presume).at(cc.xy(6, 8, CellConstraints.RIGHT,
                        CellConstraints.CENTER))
                .add("مكان الإزدياد :").xy(4, 6)

                .add("إسم ولقب الأب :").xy(8, 10)
                .add(p_pere).xy(6, 10)
                .add("إسم ولقب الأم :").xy(4, 10)
                .add(np_mere).xy(2, 10)
                .add("الجنس :").xy(4, 12)
                .add(masculinChoice).at(cc.xy(2, 12, CellConstraints.RIGHT,
                        CellConstraints.CENTER))
                .add(femininChoice).at(cc.xy(2, 14, CellConstraints.RIGHT,
                        CellConstraints.CENTER))
                .add(id_deces).xy(6, 12)
                .add(labelDateDeces).xy(8, 16)
                .add(date_deces).xy(6, 16)
                .add(labelLieuDeces).xy(4, 16)
                .add(lieu_deces).xy(2, 16)
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
        list.add(date_est_presume);

        list.add(p_pere);
        list.add(np_mere);
        list.add(masculinChoice);
        list.add(femininChoice);
        list.add(id_deces);

        return list;

    }

    /**
     * method to set infos deces enable / desable
     * @param flag
     */
    protected void decesInfosEnable(boolean flag) {

        lieu_deces.setVisible(flag);
        date_deces.setVisible(flag);
        labelDateDeces.setVisible(flag);
        labelLieuDeces.setVisible(flag);
        date_deces.setVisible(flag);


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
            CitoyenValidator validator = new CitoyenValidator(model);
            ValidationResult result = validator.validate((Citoyen) model.getBean());
            validationResultModel.setResult(result);

            if(!result.hasErrors()) {
                //mettre a jour data base citoyen infos
                MyDaosCitoyen.updateCitoyen((Citoyen) model.getBean());
                //((Citoyen) model.getBean()).setDeces(model.getBean().getDeces());
            }

        }
    }
}
