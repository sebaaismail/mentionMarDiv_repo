package com.sebaainf.mentionMarDiv.common;

import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.LayoutMap;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;
import com.sebaainf.mentionMarDiv.citoyenPackage.*;
import com.sebaainf.mentionMarDiv.ismUtils.IsmAbstractJFrame;
import com.sebaainf.mentionMarDiv.ismUtils.IsmButtonBarBuilder;
import com.sebaainf.mentionMarDiv.ismUtils.IsmButtonStackBuilder;
import com.sebaainf.mentionMarDiv.ismUtils.IsmComponentFactory;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MentionEditorModel;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * Created by ${sebaainf.com} on 22/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class Editor_window extends IsmAbstractJFrame {

    private static Editor_window uniqueInstance;
    private boolean add_mode = false;


    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();
    private JComponent messageLabel = ValidationResultViewFactory.createReportList(validationResultModel);

    private CitoyenEditorModel citModel;
    private MentionEditorModel mentModel;
    private JTextField nom_fr;
    private JTextField prenom_fr;
    public JTextField nom_ar;
    public JTextField prenom_ar;

    private JTextField emploi;

    private JDatePickerImpl date_naiss;
    private JTextField lieunaiss;
    private JTextField daira_naiss;
    private JTextField wilaya_naiss;

    private JTextField p_pere;
    private JTextField np_mere;
    private JCheckBox dateNaiss_est_presume;


    private JButton validerAddButton;
    private JButton annulerButton;
    private JButton imprimerrButton;
    private JButton annulerModifButton;

    // ***** pour Mention

    private JTextField numact_mar;
    private JTextField np_conj_ar;
    private JTextField np_conj_fr;
    private JTextField acte_ecrit_par;
    private JDatePickerImpl date_mar;
    private JTextField annee_mar;
    private JDatePickerImpl date_acte_mar;

    //private JTextField est_divorce;
    private JTextField tribunal_div;
    private JDatePickerImpl date_div;


    private JLabel tribunLabel = new JLabel("تم الطلاق بحكم صادر عن محكمة :");
    private JLabel dateDivLabel = new JLabel("بتاريـخ :");

    private JRadioButton mariage;
    private JRadioButton divorce;





    //***************************************************************


    public Editor_window(CitoyenEditorModel citModel, MentionEditorModel mentModel) {

        int idc = ((Citoyen)(citModel.getBean())).getId_cit();
        int idm = ((Mention)(mentModel.getBean())).getId_ment();

        // check if mode is add new citoyen
        if ((idc <= 0) && (idm <= 0)) {
        //if (idc <= 0) {
            this.add_mode = true;
        }

        this.citModel = citModel;
        this.mentModel = mentModel;
        initComponents();
        configContent();

    }


    protected void initComponents() {

        // initializer les buttons

        validerAddButton = new JButton();
        annulerButton = new JButton("Annuler");
        imprimerrButton = new JButton("Imprimer");
        annulerModifButton = new JButton("Annuler Modif");

        if (this.add_mode) {
            validerAddButton.setText("Ajouter");
            annulerButton.setVisible(false);
            imprimerrButton.setVisible(false);
            annulerModifButton.setVisible(false);

        } else {
            validerAddButton.setText("Valider");
        }



        this.setTitle("Application des Mentions                         " +
                "                                                           " +
                "                                               Boufatis©2015");

        nom_fr = IsmComponentFactory.createTextField(citModel.getNom_fr());
        prenom_fr = IsmComponentFactory.createTextField(citModel.getPrenom_fr());
        nom_ar = IsmComponentFactory.createArabTextField(citModel.getNom_ar());
        prenom_ar = IsmComponentFactory.createArabTextField(citModel.getPrenom_ar());

        emploi = IsmComponentFactory.createArabTextField(citModel.getEmploi());
        //emploi = IsmComponentFactory.createArabTextField(citModel.getBufferedModel(Citoyen.PROPERTY_EMPLOI));

        lieunaiss = IsmComponentFactory.createArabTextField(citModel.getLieunaiss());
        daira_naiss = IsmComponentFactory.createArabTextField(citModel.getDaira_naiss());
        wilaya_naiss = IsmComponentFactory.createArabTextField(citModel.getWilaya_naiss());

        // Binding jdatepicker with dateNaiss
        date_naiss = IsmComponentFactory.createDatePickerImpl(citModel,
                Citoyen.PROPERTY_DATE_NAISS, "yyyy/MM/dd");
        /*
        date_naiss = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), new Properties())
                , new IsmDateFormatter());
        //IsmComponentFactory.createDateField()
        Bindings.bind(date_naiss.getJFormattedTextField(), citModel.getDate_naiss());
        //*/

        //IsmComponentFactory.createDateField
        //


        //date_naiss = IsmComponentFactory.createDatePickerImpl(citModel, Citoyen.PROPERTY_DATE_NAISS, "yyyy/MM/dd");
        dateNaiss_est_presume = IsmComponentFactory.createCheckBox(
                citModel.getDateNaiss_est_presume(), "* تاريخ مفترض");


        //*

        //*/

        p_pere = IsmComponentFactory.createArabTextField(citModel.getP_pere());
        np_mere = IsmComponentFactory.createArabTextField(citModel.getNp_mere());

        //******** Now the mention fields

        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);                // to remove the thousand separator



        numact_mar = IsmComponentFactory.createIntegerField(mentModel.getNumact_mar(), nf, null);
        np_conj_ar = IsmComponentFactory.createArabTextField(mentModel.getNp_conj_ar());
        np_conj_fr = IsmComponentFactory.createTextField(mentModel.getNp_conj_fr());


        annee_mar = IsmComponentFactory.createIntegerField(mentModel.getAnnee_mar(), nf, null);


        acte_ecrit_par = IsmComponentFactory.createArabTextField(mentModel.getActe_ecrit_par());
        date_mar = IsmComponentFactory.createDatePickerImpl(mentModel
                , Mention.PROPERTY_DATE_MAR, "yyyy/MM/dd");
        date_acte_mar = IsmComponentFactory.createDatePickerImpl(mentModel
                , Mention.PROPERTY_DATE_ACTE_MAR, "yyyy/MM/dd");

        date_div = IsmComponentFactory.createDatePickerImpl(mentModel
                , Mention.PROPERTY_DATE_DIV, "yyyy/MM/dd");

        tribunal_div = IsmComponentFactory.createArabTextField(mentModel.getTribunal_div());

        divorce = IsmComponentFactory.createRadioButton(mentModel.getEst_divorce(), true, "طلاق");
        //mariage = new JRadioButton("زواج");
        mariage = IsmComponentFactory.createRadioButton(mentModel.getEst_divorce(), false, "زواج");





/*        messageLabel.getComponent(1)
        .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);*/
        ((JScrollBar) messageLabel.getComponent(1))
                .setAlignmentY(JScrollBar.RIGHT_ALIGNMENT);


    }


    protected JComponent buildContent() {

        nom_ar.setPreferredSize(date_naiss.getPreferredSize());
        prenom_ar.setPreferredSize(date_naiss.getPreferredSize());

        annee_mar.setColumns(4);
        numact_mar.setColumns(6);
        annee_mar.setHorizontalAlignment(JTextField.RIGHT);
        numact_mar.setHorizontalAlignment(JTextField.RIGHT);


        //setting border
        for(JComponent comp:getListComponents()) {
            if (!comp.getClass().getSimpleName().equals("JDatePickerImpl"))
            comp.setBorder(BorderFactory.createEtchedBorder());
        }

        mariage.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mariage.isSelected()) {

                }
            }
        });

        /*ButtonGroup bg = new ButtonGroup();
        bg.add(mariage);
        bg.add(divorce);*/


        LayoutMap.getRoot().columnPut("label_ar", "left:pref");
        LayoutMap.getRoot().columnPut("label_fr", "right:pref");

        //MyCommonUtils.setListComponentsEnabled(getListComponents(), true);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(editorCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(westButtonsPanel(), BorderLayout.WEST);
        mainPanel.add(southButtonsPanel(), BorderLayout.SOUTH);

        checkDivorceComponents();

        ActionListener listenerRadio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDivorceComponents();
            }
        };
        divorce.addActionListener(listenerRadio);
        mariage.addActionListener(listenerRadio);


        MyCommonUtils.setListComponentsEnabled(getListComponents(),true);

        return mainPanel;
    }

    private JComponent editorCenterPanel() {

        JScrollPane fullPanel = new JScrollPane(citEditorPanel());


        // thi code just for centring the JScrollPane in BorderLayout.Center
        // we will use GridBagLayout for the parent of the component inside it

        JViewport viewport = fullPanel.getViewport();
        ((JPanel)viewport.getView()).getParent().setLayout(new GridBagLayout());

        return fullPanel;

    }

    private JComponent citEditorPanel() {

        // Building citEditorPanel()


        return FormBuilder.create()
                //.debug(true)
                .columns("pref, $lcgap, $label_ar, $ugap , pref, $lcgap, $label_ar, $lcgap, $label_ar")
                .rows("p, $lgap, p, $lgap, p, $lgap, p, $lgap, p ,$lgap , p, $lgap, p, 14dlu,"       // for Citoyen Zone
                        + "p, $lgap, p, $lgap, p, $lgap, p, $lgap, p, $lgap, p, $pgap, p, $lgap, p")//for mention Zone
                .columnGroups(new int[][]{{1, 5}})
                .padding(Paddings.DLU9)

                .add("اللقب :").xy(7, 1)
                .add(nom_ar).xy(5, 1)
                .add("الإسم :").xy(7, 3)
                .add(prenom_ar).xy(5, 3)

                .add(": Nom").xy(3, 1)
                .add(nom_fr).xy(1, 1)
                .add(": Prenom").xy(3, 3)
                .add(prenom_fr).xy(1, 3)


                .add("المهنة :").xy(7, 5)
                .add(emploi).xy(5, 5)

                .add("المولود في :").xy(7, 7)
                .add(date_naiss).xy(5, 7)
                .add(dateNaiss_est_presume).xy(5, 9, "right, center")
                .add("بـــ  :").xy(3, 7)
                .add(lieunaiss).xy(1, 7)

                .add("دائرة :").xy(7, 11)
                .add(daira_naiss).xy(5, 11)
                .add("ولايـة :").xy(3, 11)
                .add(wilaya_naiss).xy(1, 11)

                .add("إبــن :").xy(7, 13)
                .add(p_pere).xy(5, 13)
                .add("و    :").xy(3, 13)
                .add(np_mere).xy(1, 13)

                // mention zone began

                .add("تم عقد زواجه مع :").xyw(3, 15, 3)
                .add(np_conj_ar).xy(1, 15)
                .add(": L'épouse").xyw(3, 17, 3)
                .add(np_conj_fr).xy(1, 17)

                .add("حرر من طرف :").xyw(3, 19, 3)
                .add(acte_ecrit_par).xy(1, 19)
                .add("بتاريخ :").xyw(3, 21, 3)
                .add(date_mar).xy(1, 21)
                .add("عـام :").xyw(3, 23, 3)
                .add(annee_mar).xy(1, 23, "right,center")
                .add("قيد في سجلات الزواج بتاريخ :").xyw(3, 25, 3)
                .add(date_acte_mar).xy(1, 25)

                .add(tribunLabel).xyw(3, 27, 3)
                .add(tribunal_div).xy(1, 27)
                .add(dateDivLabel).xyw(3, 29, 3)
                .add(date_div).xy(1, 29)

                .add(mariage).xy(5, 17, "right,center")
                .add(divorce).xy(5, 19, "right,center")
                .add("رقـم :").xy(7, 21)
                .add(numact_mar).xy(5, 21, "right,center")

                .build();

    }

    private void checkDivorceComponents(){
        this.tribunLabel.setEnabled(divorce.isSelected());
        this.tribunal_div.setEnabled(divorce.isSelected());
        this.dateDivLabel.setEnabled(divorce.isSelected());
        this.date_div.getJFormattedTextField().setEnabled(divorce.isSelected());
        this.date_div.getComponent(1).setVisible(divorce.isSelected());
    }

    private JComponent westButtonsPanel() {

        JPanel pan = new JPanel();
        IsmButtonStackBuilder bsBuilder = new IsmButtonStackBuilder(pan, screenSize);
        bsBuilder.setBackground(MyApp.theme.buttonBarColor);

        JButton buttonRetour = new JButton("Retour");
        JButton buttonSupprCit = new JButton("Supprimer Citoyen");
        JButton buttonAjouterMent = new JButton("Ajouter Mention");
        JButton buttonSupprMent = new JButton("Supprimer Mention");
        JButton buttonQuitter = new JButton("Quitter");

        buttonAjouterMent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewMention();
            }
        });

        buttonSupprCit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int reply = JOptionPane.showConfirmDialog(null,
                        "voulez vous supprimer cette enregistrement du citoyen ?",
                        "Suppression", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    MyDaosCitoyen.deleteCitoyen((Citoyen) citModel.getBean());
                    JOptionPane.showMessageDialog(null, "enregistrement supprimée avec succes");
                }
                else {
                    //JOptionPane.showMessageDialog(null, "GOODBYE");

                }

            }
        });

        bsBuilder.addGlue();

        bsBuilder.addButton(buttonRetour);
        bsBuilder.addUnrelatedGap();

        bsBuilder.addButton(buttonSupprCit);
        bsBuilder.addGlue();

        bsBuilder.addButton(buttonAjouterMent);
        bsBuilder.addUnrelatedGap();
        bsBuilder.addButton(buttonSupprMent);
        bsBuilder.addUnrelatedGap();

        bsBuilder.addButton(buttonQuitter);
        bsBuilder.addGlue();


        return bsBuilder.getPanel();

    }

    private JComponent southButtonsPanel() {


        JPanel southPan = new JPanel();
        southPan.setBackground(Color.gray);

        imprimerrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ReportedBean bean = new ReportedBean((Citoyen) citModel.getBean(),
                        (Mention) mentModel.getBean());
                ReportView.report(bean);
            }
        });

        final Trigger triggerCit = (Trigger) citModel.getTriggerChannel();
        final Trigger triggerMent = (Trigger) mentModel.getTriggerChannel();


        validerAddButton.setEnabled(false);
        annulerModifButton.setEnabled(false);

        // now to enable buttonValider when citModel or mentModel change
        PropertyConnector.connect(citModel, "buffering", validerAddButton, "enabled");
        PropertyConnector.connect(citModel, "buffering", annulerModifButton, "enabled");

        //PropertyConnector.connect(date_naiss.getJFormattedTextField(), "value", buttonValider, "enabled");

        //PropertyConnector.connect(citModel, Citoyen.PROPERTY_DATE_NAISS, date_naiss, "formattedTextField");


        PropertyConnector.connect(mentModel, "buffering", validerAddButton, "enabled");
        PropertyConnector.connect(mentModel, "buffering", annulerModifButton, "enabled");


        annulerModifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triggerCit.triggerFlush();
                triggerMent.triggerFlush();
           }
        });


        validerAddButton.addActionListener(new CommitActionListener
                (citModel, mentModel, divorce, add_mode));

        JPanel pan = IsmButtonBarBuilder.create(screenSize)
                //.addGlue()
                .addButton(validerAddButton)
                .addRelatedGap()
                .addButton(imprimerrButton)
                .addUnrelatedGap()
                .addUnrelatedGap()
                .addUnrelatedGap()
                .addButton(annulerModifButton)
                .padding(Paddings.DLU9)


                .build();

        southPan.add(pan);

        return southPan;

    }

    public JComponent showDialog(EventObject e) {

        return buildContent();

    }

    private void addNewMention() {


        this.mentModel.setBean(new Mention((Citoyen) this.citModel.getBean()));

        // todo to optimise ... do this in resetDatePicker(datePicker);
        // in IsmComponentFactory.java
        date_mar.getJFormattedTextField().setText("");
        date_acte_mar.getJFormattedTextField().setText("");
        date_div.getJFormattedTextField().setText("");

    }

    @Override
    public ArrayList<JComponent> getListComponents() {

        ArrayList<JComponent> list = new ArrayList<JComponent>();
        list.add(nom_ar);
        list.add(prenom_ar);
        list.add(nom_fr);
        list.add(prenom_fr);
        list.add(date_naiss);
        list.add(lieunaiss);
        list.add(emploi);
        list.add(daira_naiss);
        list.add(wilaya_naiss);

        list.add(p_pere);
        list.add(np_mere);

        list.add(np_conj_ar);
        list.add(np_conj_fr);
        list.add(acte_ecrit_par);
        list.add(date_mar);
        list.add(annee_mar);
        list.add(date_acte_mar);
        list.add(date_acte_mar);
        list.add(tribunal_div);
        list.add(date_div);
        list.add(numact_mar);

        return list;

    }

}
