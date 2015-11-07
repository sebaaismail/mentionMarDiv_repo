package com.sebaainf.mentionMarDiv.common;

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
import java.util.ArrayList;

/**
 * Created by ${sebaainf.com} on 06/11/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 *
 * cette class pour valider les entrees de user dans form EditorWindow
 * est appelé quand on click sur le bouton Valider
 * pour changer un citoyen ou une mention et aussi pour ajouter un nouveau citoyen ou une nouvelle mention
 */
public class IsmActionListener implements ActionListener {

    private CitoyenEditorModel citModel;
    private MentionEditorModel mentModel;

    private JToggleButton divorceRadioButton;

    //private JComponent[] listComp = new JComponent[0];

    /**
     * constructeur mené par les models citoyen, mention et aussi le radioButton divorce
     * @param citModel
     * @param mentModel
     * @param divorceRadioButton
     */
    public IsmActionListener(CitoyenEditorModel citModel, MentionEditorModel mentModel,
                             JToggleButton divorceRadioButton){

        this.citModel = citModel;
        this.mentModel = mentModel;
        this.divorceRadioButton = divorceRadioButton;
        //this.listComp = listComp;

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String message = "";
        boolean correct = true;

        if (citModel.isChanged()) {

            IsmPrintStream.println_with_space("citModel.isChanged");
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
                MyDaosCitoyen.updateCitoyen((Citoyen) citModel.getBean());
            }
        }

        if (mentModel.isChanged()) {
            IsmPrintStream.println_with_space("mentModel.isChanged");
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
                if (MyDaosMention.updateMention((Mention) mentModel.getBean())!=null){
                    IsmPrintStream.println_with_space("mention updated in DB !");
                } else {
                    IsmPrintStream.println_with_space("error in DB !");
                }

            }
        }



        if (!correct) {
            JOptionPane.showMessageDialog(null, message);
        } else {
            //TODO  update database
            Citoyen cit = MyDaosCitoyen.updateCitoyen((Citoyen) citModel.getBean());

        }

        IsmPrintStream.println_with_space("Editor_window.actionPerformed ... validate finished!");
        IsmPrintStream.println_with_space(message);
    }
}
