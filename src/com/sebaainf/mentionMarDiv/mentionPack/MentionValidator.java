package com.sebaainf.mentionMarDiv.mentionPack;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.sebaainf.mentionMarDiv.ismUtils.IsmValidator;

/**
 * Created by ${sebaainf.com} on 05/11/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class MentionValidator extends IsmValidator implements Validator<Object> {


    public MentionValidator(PresentationModel preModel){

        this.preModel = preModel;

    }

    public ValidationResult validate(Object mention, boolean is_divorce){

        System.out.println("validating mention ...");
        addBlankError(Mention.PROPERTY_NP_CONJ_AR, "إسم ولقب الزوجة");
        addBlankError(Mention.PROPERTY_ACTE_ECRIT_PAR, "nom prenom de l'épouse");
        addBlankError(Mention.PROPERTY_ACTE_ECRIT_PAR, " حرر من طرف");
        addBlankError(Mention.PROPERTY_DATE_MAR, "تاريخ الزواج");
        addBlankError(Mention.PROPERTY_ANNEE_MAR, "سنة الزواج");
        addBlankError(Mention.PROPERTY_DATE_ACTE_MAR, "قيد في سجلات الزواج بتاريخ");

        if (is_divorce) {
            addBlankError(Mention.PROPERTY_TRIBUNAL_DIV, "المحكمة التي أصدرت حكم الطلاق");
            addBlankError(Mention.PROPERTY_DATE_DIV, "تاريخ الطلاق");
            addBlankError(Mention.PROPERTY_NUMACT_MAR, "رقم عقد الطلاق");

        } else {
            addBlankError(Mention.PROPERTY_NUMACT_MAR, "رقم عقد الزواج");

        }

        return validationResult;

    }

    @Override
    public ValidationResult validate(Object o) {

        return null;
    }
}
