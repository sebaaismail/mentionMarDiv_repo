/*
 * Created by JFormDesigner on Wed Feb 04 16:06:42 CET 2015
 */

package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jenkov.db.itf.PersistenceException;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.sebaainf.mentionMarDiv.common.Editor_window;
import com.sebaainf.mentionMarDiv.ismUtils.IsmComponentFactory;
import com.sebaainf.mentionMarDiv.ismUtils.IsmPrintStream;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MentionEditorModel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sebaa ismail
 */
public class SearchCit_window extends JFrame implements Runnable {


    // Binding *************************************************
    private PresentationModel<Citoyen> cit_adapter =
            new PresentationModel<Citoyen>(new Citoyen());
    private ValidationResultModel validationResultModel =
            new DefaultValidationResultModel();

    //private JTextArea messageError = ValidationResultViewFactory.createReportTextArea(validationResultModel);
    //  *************************************************

    // why implements Runnable ? ----> because it is the only
    // way to ensure that one instance of application is run
    // Singleton dont work with the first JFrame


    //the port number using
    private static final int PORT = 5555;
    //count of tried instances
    private int triedInstances = 0;

    private JDatePickerImpl datePicker;

    JTextField nomFr_Field;
    JTextField nomAr_Field;
    JTextField prenomFr_Field;
    JTextField prenomAr_Field;


    JRadioButton inFrench = new JRadioButton("En français");
    JRadioButton inArabic = new JRadioButton("بالعربية");
    ButtonGroup bg = new ButtonGroup();

    JLabel nomFrLabel = new JLabel("Nom :");
    JLabel prenomFrLabel = new JLabel("Prenom :");
    JLabel nomArLabel = new JLabel(": اللقب");
    JLabel prenomArLabel = new JLabel(": الإسم");

    JButton buttonOk = new JButton(new CitoyenValidationAction());
    JButton buttonQuit = new JButton("Quitter");
    JButton buttonNouveauCit = new JButton("* Nouveau Citoyen");

    public SearchCit_window() {

        this.setTitle("Mention App                                  / Recherche d'un citoyen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.createPanel());
        this.pack();
        this.setLocationRelativeTo(null); //to center the frame in the middle of screen
        //this.setSize(500, 500);

        //run socket listening inside a thread
        Thread thread = new Thread(this);
        thread.start();

    }

    public JComponent createPanel() {


        // Binding *************************************************
        nomFr_Field = IsmComponentFactory.createTextField(
                cit_adapter.getModel(Citoyen.PROPERTY_NOM_FR));

        prenomFr_Field = IsmComponentFactory.createTextField(
                cit_adapter.getModel(Citoyen.PROPERTY_PRENOM_FR));

        nomAr_Field = IsmComponentFactory.createArabTextField(
                cit_adapter.getModel(Citoyen.PROPERTY_NOM_AR));

        prenomAr_Field = IsmComponentFactory.createArabTextField(
                cit_adapter.getModel(Citoyen.PROPERTY_PRENOM_AR));
        //  *********************************************************


        buttonOk.setText("Ok");
        buttonOk.setPreferredSize(buttonQuit.getPreferredSize());

        inFrench.setSelected(true);

        nomAr_Field.setEnabled(false);
        prenomAr_Field.setEnabled(false);
        nomArLabel.setEnabled(false);
        prenomArLabel.setEnabled(false);

        bg.add(inFrench);
        bg.add(inArabic);

        ChangeListener radioChangeLitener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                nomFr_Field.setEnabled(inFrench.isSelected());
                prenomFr_Field.setEnabled(inFrench.isSelected());
                nomFrLabel.setEnabled(inFrench.isSelected());
                prenomFrLabel.setEnabled(inFrench.isSelected());



                nomAr_Field.setEnabled(!inFrench.isSelected());
                prenomAr_Field.setEnabled(!inFrench.isSelected());
                nomArLabel.setEnabled(!inFrench.isSelected());
                prenomArLabel.setEnabled(!inFrench.isSelected());

                if (inFrench.isSelected()){
                    nomAr_Field.setText("");
                    prenomAr_Field.setText("");
                } else {
                    nomFr_Field.setText("");
                    prenomFr_Field.setText("");
                }
            }
        };
        inArabic.addChangeListener(radioChangeLitener);
        inFrench.addChangeListener(radioChangeLitener);


        nomFr_Field.setPreferredSize(new Dimension(140, nomFr_Field.getPreferredSize().height));

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        //  buttonNouveauCit ActionListener *******************************************

        buttonNouveauCit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CitoyenEditorModel citModel = new CitoyenEditorModel(new Citoyen());
                MentionEditorModel mentModel = new MentionEditorModel(new Mention());

                Editor_window view = new Editor_window(citModel, mentModel);
                view.setVisible(true);
            }
        });


        //  buttonQuit ActionListener **********************************************

        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        // testing datpicker********

        JDatePickerImpl date_naiss;
        Citoyen citTesting = new Citoyen();
        citTesting.setDate_naiss(new java.sql.Date(1990,8,20));

      //  date_naiss = IsmComponentFactory.createDatePickerImpl(new CitoyenEditorModel(citTesting)
       //         ,Citoyen.PROPERTY_DATE_NAISS, "yyyy/MM/dd");



        //*************************

        //TODO file:///C:/projects/modules/Jgoodies/jgoodies-forms-1.9.0/docs/api/index.html
        return FormBuilder.create()
                .columns("right:pref, 4dlu, pref, 4dlu, left:pref, left:default")
                .rows("pref, $lg, pref, 16dlu, pref, $lg, pref, 8dlu, pref, 14dlu, pref")
                .padding(Paddings.DLU14)

                .add(nomFrLabel).xy(1, 1)
                .add(nomFr_Field).xy(3, 1)
                .add(prenomFrLabel).xy(1, 3)
                .add(prenomFr_Field).xy(3, 3)
                .add(nomArLabel).xy(1, 5)
                .add(nomAr_Field).xy(3, 5)
                .add(prenomArLabel).xy(1, 7)
                .add(prenomAr_Field).xy(3, 7)

                .add(inFrench).xy(6, 3)
                .add(inArabic).xy(6, 5)
                //.add(buttonQuit).xyw(5, 9, 2, "left, fill")
                .addBar(buttonOk).xy(3, 9, "right, fill")
                .addBar(buttonQuit).xy(5, 9, "left, fill")
                .addBar(buttonNouveauCit).xy(3, 11)
                .build();
        // i create tabbedPanel with MyFormBuilder
        // to centralize attributes like font size etc ...
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        try {
            //create server socket
            ServerSocket serverSocket = new ServerSocket(PORT);

            //listing the socket to check new instances
            while (true) {
                try {
                    //another instance accessed the socket
                    serverSocket.accept();
                    //bring this to front
                    toFront();

                    //change the title (addtional);
                    triedInstances++;
                    setTitle("Tried another instances : " + triedInstances);
                } catch (IOException ex) {
                    //cannot accept socket
                }
            }
        } catch (IOException ex) {
            //fail if there is an instance already exists
            try {
                //connect to the main instance server socket
                new Socket(InetAddress.getLocalHost(), PORT);
            } catch (IOException ex1) {
                //do nothing
            } finally {
                //exit the system leavng the first instance
                System.exit(0);
            }
        }
    }


    private class HandlerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private class CitoyenValidationAction extends AbstractAction {

        public CitoyenValidationAction() {

            super("Chercher");

        }

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            validationResultModel.setResult(ValidationResult.EMPTY);

            CitoyenValidator validator = new CitoyenValidator(cit_adapter);
            ValidationResult result = validator.validate(cit_adapter.getBean(), !inFrench.isSelected());

            if (!result.hasErrors()) {
                IsmPrintStream.logging("looking at database");

                try {

                    java.util.List<Citoyen> listCit;
                    if (inFrench.isSelected()) {
                        listCit = MyDaosCitoyen.getListCit(cit_adapter.getBean().getNom_fr(),
                                cit_adapter.getBean().getPrenom_fr(), true);
                    } else {
                        listCit = MyDaosCitoyen.getListCit(cit_adapter.getBean().getNom_ar(),
                                cit_adapter.getBean().getPrenom_ar(), false);
                    }

                    if (listCit.size() > 0) {
                        JFrame winTable = ResultaRech_window.getInstance(listCit);
                        winTable.setVisible(true);
                        //initilizer();
                    } else {
                        JOptionPane.showMessageDialog(null, "غير مسجل في قاعدة البيانات !");
                    }
                } catch (PersistenceException e1) {
                    e1.printStackTrace();
                }


            } else {

                JOptionPane.showMessageDialog(null, result.getMessagesText());
            }

        }

        private class ValidationListener implements PropertyChangeListener {

            /**
             * This method gets called when a bound property is changed.
             *
             * @param evt A PropertyChangeEvent object describing the event source
             *            and the property that has changed.
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {


                if (evt.getPropertyName() == ValidationResultModel.PROPERTY_RESULT) {
                    JOptionPane.showMessageDialog(null, "Validation has been performed");
                } else if (evt.getPropertyName() == ValidationResultModel.PROPERTY_MESSAGES) {

                }

            }
        }
    }
}