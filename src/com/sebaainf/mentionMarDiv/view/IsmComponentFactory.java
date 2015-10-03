package com.sebaainf.mentionMarDiv.view;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.ComponentValueModel;
import com.sebaainf.mentionMarDiv.citoyen.IPerson;

import com.sebaainf.mentionMarDiv.presentation.CitoyenEditorModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * Created by ${sebaainf.com} on 14/03/2015.
 */

public class IsmComponentFactory extends BasicComponentFactory {

    public static IsmDateFormatter formatter = new IsmDateFormatter();


    /**
     * method to create
     *
     * @param model PresentationModel contain model wraping IPerson Bean
     *              that have property Date date_naiss and its setter and getter method
     * @return
     */
    public static JDatePickerImpl createDatePickerImpl(final CitoyenEditorModel model){

        // with default datePattern "dd/MM/yyyy"

        final UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(2000, 01, 01);

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, new Properties());



        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);

        datePicker.setShowYearButtons(true);
        datePicker.setButtonFocusable(true);
        datePicker.setTextEditable(true);
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.RIGHT);

        //initialize datePicker

        Date date = ((IPerson) model.getBean()).getDate_naiss();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getJDateInstantPanel().getModel().setSelected(true);

        try {
            datePicker.getJFormattedTextField().setText(formatter.valueToString(calendar));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datePicker.getModel().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                JOptionPane.showMessageDialog(null, evt.getPropertyName() + " --> : "
                        + evt.getNewValue());
                ((IPerson) model.getBean()).
                        setDate_naiss(new Date(((java.util.Date)
                                datePicker.getModel().getValue()).getTime()));
            }
        });

        return datePicker;
    }

    public static JDatePickerImpl createDatePickerDecesImpl(final CitoyenEditorModel model){

        // with default datePattern "dd/MM/yyyy"

        final UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(2000, 01, 01);

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, new Properties());



        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);

        datePicker.setShowYearButtons(true);
        datePicker.setButtonFocusable(true);
        datePicker.setTextEditable(true);
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.RIGHT);


/* TODO deleted
  //initialize datePicker

        if(dec != null) {

            Date date = dec.getDate_dec();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePicker.getJDateInstantPanel().getModel().setSelected(true);

            try {
                datePicker.getJFormattedTextField().setText(formatter.valueToString(calendar));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/



/*
TODO deleted
        datePicker.getModel().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                ((IPerson) model.getBean()).getDeces().setDate_dec(new Date(((java.util.Date) datePicker.getModel().getValue()).getTime()));
            }
        });
*/

        return datePicker;
    }

    // with datePattern "yyyy/MM/dd" for arabic alignement for exple
    public static JDatePickerImpl createDatePickerImpl(final CitoyenEditorModel model,
                                                       String datePattern) {

        IsmComponentFactory.formatter = new IsmDateFormatter(datePattern);
        return IsmComponentFactory.createDatePickerImpl(model);

    }

    public static JDatePickerImpl createDatePickerDecesImpl(final CitoyenEditorModel model,
                                                            String datePattern) {

        IsmComponentFactory.formatter = new IsmDateFormatter(datePattern);
        return IsmComponentFactory.createDatePickerDecesImpl(model);
    }


}
