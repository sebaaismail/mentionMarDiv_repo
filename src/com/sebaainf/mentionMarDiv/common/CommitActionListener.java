package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.itf.PersistenceException;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.validation.ValidationResult;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.CitoyenEditorModel;
import com.sebaainf.mentionMarDiv.citoyenPackage.CitoyenValidator;
import com.sebaainf.mentionMarDiv.citoyenPackage.MyDaosCitoyen;
import com.sebaainf.mentionMarDiv.ismUtils.IsmPrintStream;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MentionEditorModel;
import com.sebaainf.mentionMarDiv.mentionPack.MentionValidator;
import com.sebaainf.mentionMarDiv.mentionPack.MyDaosMention;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ${sebaainf.com} on 06/11/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 *
 * cette class pour valider les entrees de user dans form EditorWindow
 * est appelé quand on click sur le bouton Valider
 * pour changer un citoyen ou une mention et aussi pour ajouter un nouveau citoyen ou une nouvelle mention
 */
public class CommitActionListener implements ActionListener {


    private CitoyenEditorModel citModel;
    private MentionEditorModel mentModel;

    private JToggleButton divorceRadioButton;
    private boolean add_mode;
    private Trigger citTrigger;
    private Trigger mentTrigger;

    //private JComponent[] listComp = new JComponent[0];

    /**
     * constructeur mené par les models citoyen, mention et aussi le radioButton divorce
     * @param citModel
     * @param mentModel
     * @param divorceRadioButton
     * @param add_mode
     */
    public CommitActionListener(CitoyenEditorModel citModel, MentionEditorModel mentModel,
                                JToggleButton divorceRadioButton, boolean add_mode){

        this.citModel = citModel;
        this.mentModel = mentModel;
        this.divorceRadioButton = divorceRadioButton;
        this.add_mode = add_mode;
        this.citTrigger = (Trigger) citModel.getTriggerChannel();
        this.mentTrigger = (Trigger) mentModel.getTriggerChannel();
        //this.listComp = listComp;

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String textMode = "updated"; // default
        String message = "";
        boolean correct = true;

        citTrigger.triggerCommit();
        mentTrigger.triggerCommit();

        if (citModel.isChanged()) {

            IsmPrintStream.logging("citModel.isChanged");
            CitoyenValidator citoyenValidator = new CitoyenValidator(citModel);
            ValidationResult resultCit =
                    citoyenValidator.validate(citModel.getBean());
            if (resultCit.hasErrors()) {
                correct = false;
                if (resultCit.getMessages().size() > 2) {
                    message = "أدخل بيانات المواطن"  + "\n";
                } else {
                    message += resultCit.getMessagesText();
                }

            } else {

                try {
                    if (add_mode) {
                        textMode = "added";
                        MyDaosCitoyen.insertCitoyen((Citoyen) citModel.getBean());
                        int i = MyDaos.ism_lastGeneratedId("citoyen", "id_cit");
                                ((Mention) mentModel.getBean()).setId_cit(i);
                    } else {
                        // we are in update mode
                        MyDaosCitoyen.updateCitoyen((Citoyen) citModel.getBean());
                    }
                    IsmPrintStream.logging("citoyen " + textMode + " !");
                } catch (PersistenceException e1) {
                    e1.printStackTrace();
                    IsmPrintStream.logging("error in db table citoyen... ism message!");

                }

            }
        }

        if (mentModel.isChanged()) {
            IsmPrintStream.logging("mentModel.isChanged");
            MentionValidator mentionValidator = new MentionValidator(mentModel);
            ValidationResult resultMent =
                    mentionValidator.validate(mentModel.getBean(), divorceRadioButton.isSelected());

            if (resultMent.hasErrors()) {
                correct = false;
                if (resultMent.getMessages().size() > 3) {
                    message = message + " " + "أدخل البيانات الهامشية"  ;
                } else {
                    message += resultMent.getMessagesText();
                }
            } else {
                try {
                    Citoyen ccit= ((Citoyen) citModel.getBean());
                    if (add_mode) {
                        MyDaosMention.insertMention((Mention) mentModel.getBean());
                    } else {
                        System.out.println("zazouuuuu");
                        Mention ment = (Mention) mentModel.getBean();
                        if (ment.getId_ment()<=0) {
                            // we aadd new mention
                            System.out.println("zaza");
                            MyDaosMention.insertMention(ment);
                            ment.setId_cit(ccit.getId_cit());
                            textMode = "added";

                        } else {
                            // we update the mention
                            MyDaosMention.updateMention(ment);
                        }

                    }
                    IsmPrintStream.logging("mention is " + textMode + " ! in DB !");

                } catch (PersistenceException e1) {
                    e1.printStackTrace();
                    IsmPrintStream.logging("error in db table mention... ism message!");
                }

            }
        }



        if (!correct) {
            JOptionPane.showMessageDialog(null, message);
        }
        IsmPrintStream.logging(message);
    }
}
