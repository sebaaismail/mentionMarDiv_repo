/*
 * Created by JFormDesigner on Wed Feb 04 16:06:42 CET 2015
 */

package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jenkov.db.itf.PersistenceException;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;
import com.sebaainf.mentionMarDiv.common.IsmComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author sebaa ismail
 */
public class SearchCit_window extends JFrame implements Runnable {

    // why implements Runnable ? ----> because it is the only
    // way to ensure that one instance of application is run
    // Singleton dont work with the first JFrame


    //the port number using
    private static final int PORT = 5555;
    //count of tried instances
    private int triedInstances = 0;
    private ValidationResultModel cit_validationResultModel = new DefaultValidationResultModel();
    private PresentationModel<Citoyen> cit_adapter = new PresentationModel<Citoyen>(new Citoyen());
    //private JLabel messageLabel = ValidationResultViewFactory.createReportIconAndTextLabel(validationResultModel);
    private JComponent cit_messageLabel = ValidationResultViewFactory.createReportList(cit_validationResultModel);

    private JDatePickerImpl datePicker;

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


        final JTextField nomFr_Field = new JTextField();
        final JTextField nomAr_Field = new JTextField();
        final JTextField prenomFr_Field = new JTextField();
        JTextField prenomAr_Field = new JTextField();

        JButton buttonOk = new JButton("Ok");
        JButton buttonQuit = new JButton("Quitter");
        JButton buttonNouveauCit = new JButton("* Nouveau Citoyen");

        buttonOk.setPreferredSize(buttonQuit.getPreferredSize());

        JRadioButton enFrance = new JRadioButton("En français");
        JRadioButton enArabe = new JRadioButton("بالعربية");
        enFrance.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(enFrance);
        bg.add(enArabe);

        nomFr_Field.setPreferredSize(new Dimension(140, nomFr_Field.getPreferredSize().height));

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    java.util.List<Citoyen> listCit = MyDaosCitoyen.getListCit(nomFr_Field.getText(),
                            prenomFr_Field.getText(), true);
                    if (listCit.size() > 0) {
                        JFrame winTable = ResultaRech_window.getInstance(listCit);
                        winTable.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "غير مسجل في قاعدة البيانات !");
                    }
                } catch (PersistenceException e1) {
                    e1.printStackTrace();
                }

            }
        });

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

        date_naiss = IsmComponentFactory.createDatePickerImpl(new CitoyenEditorModel(citTesting)
                ,Citoyen.PROPERTY_DATE_NAISS, "yyyy/MM/dd");



        //*************************



        buttonOk.addActionListener(new CitoyenValidationAction());
        //TODO file:///C:/projects/modules/Jgoodies/jgoodies-forms-1.9.0/docs/api/index.html
        return FormBuilder.create()
                .columns("right:pref, 4dlu, pref, 4dlu, left:pref, left:default")
                .rows("pref, $lg, pref, 16dlu, pref, $lg, pref, 8dlu, pref, 14dlu, pref")
                .padding(Paddings.DLU14)

                .add("Nom :").xy(1, 1)
                .add(nomFr_Field).xy(3, 1)
                .add("Prenom :").xy(1, 3)
                .add(prenomFr_Field).xy(3, 3)
                .add(": اللقب").xy(1, 5)
                .add(nomAr_Field).xy(3, 5)
                .add(": الإسم").xy(1, 7)
                .add(prenomAr_Field).xy(3, 7)

                .add(enFrance).xy(6, 3)
                .add(enArabe).xy(6, 5)
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