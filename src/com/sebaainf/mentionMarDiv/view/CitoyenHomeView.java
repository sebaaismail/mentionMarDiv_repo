package com.sebaainf.mentionMarDiv.view;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.sebaainf.mentionMarDiv.presentation.CitoyenEditorModel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * Created by ${sebaainf.com} on 30/03/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class CitoyenHomeView {

    // TODO
    private final CitoyenEditorModel model;
    private JLabel nom_fr;
    private JLabel prenom_fr;
    private JLabel nom_ar;
    private JLabel prenom_ar;

    private JDatePickerImpl date_naiss;
    private JLabel code_lieunaiss;
    private JLabel sit_famil;
    private JLabel masculinChoice;

    private JLabel p_pere;
    private JLabel np_mere;
    private JLabel id_deces;
    private JCheckBox date_est_presume;


    CitoyenHomeView(CitoyenEditorModel model) {

        this.model = model;
        initComponents();

    }

    private void initComponents() {


        nom_fr = BasicComponentFactory.createLabel(model.getNom_fr());
        prenom_fr = BasicComponentFactory.createLabel(model.getPrenom_fr());
        nom_ar = BasicComponentFactory.createLabel(model.getNom_ar());
        prenom_ar = BasicComponentFactory.createLabel(model.getPrenom_ar());

        //code_lieunaiss = BasicComponentFactory.createComboBox(model.getCode_lieunaiss());
        sit_famil = IsmComponentFactory.createLabel(model.getSit_famil());

        //TODO masculinChoice = IsmComponentFactory.createLabel(model.getEst_masculin()
          //      , true, "ذكر");

//        masculinChoice.setHorizontalTextPosition(SwingConstants.LEFT);


        date_naiss = IsmComponentFactory.createDatePickerImpl(model, "yyyy/MM/dd");
        date_est_presume = IsmComponentFactory.createCheckBox(
                model.getDate_est_presume(), "* مفترض");

        // we use createBooleanNegator to return the inverse of Boolean
        //id_deces = IsmComponentFactory.createCheckBox(model.getId_deces(), "* على قيد الحياة");
/*         TODO
        id_deces = new JCheckBox("* على قيد الحياة");
        if ((Integer)(model.getId_deces().getValue()) > 0) {
            id_deces.setSelected(true);
        } else {
            id_deces.setSelected(false);
        }*/
        //id_deces.addActionListener(ActionDeces);

        p_pere = IsmComponentFactory.createLabel(model.getP_pere());
        np_mere = IsmComponentFactory.createLabel(model.getNp_mere());

        // HorizontalAlignment of JTextField
        nom_ar.setHorizontalAlignment(JTextField.RIGHT);
        prenom_ar.setHorizontalAlignment(JTextField.RIGHT);
        p_pere.setHorizontalAlignment(JTextField.RIGHT);
        np_mere.setHorizontalAlignment(JTextField.RIGHT);




    }


    private JComponent buildContent() {

        //MyCommonUtils.setListComponentsEnabled(getListComponents(), false);


/*
        FormLayout layout = new FormLayout(

                //      textField         label         textField           label     //
                "  4dlu,pref:grow,4dlu,right:pref,3dlu ,pref:grow  ,4dlu ,right:pref"//, //columns
                //      --------          ------        ---------           -------
        );
*/

        CellConstraints cc = new CellConstraints();

        return FormBuilder.create()
                .columns("40dlu,fill:default,8dlu,left:pref,100dlu ,150dlu, 8dlu, fill:default, 40dlu")
                .rows("40dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu,p,6dlu,p")
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
                .add(p_pere).xy(6,10)
                .add("إسم ولقب الأم :").xy(4, 10)
                .add(np_mere).xy(2, 10)
                .add("الجنس :").xy(4, 12)
                .add(masculinChoice).at(cc.xy(2, 12, CellConstraints.RIGHT,
                        CellConstraints.CENTER))

                .add(id_deces).xy(6, 12)

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
        list.add(id_deces);

        return list;

    }

}
