package com.sebaainf.mentionMarDiv.ismUtils;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.ValueModel;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.CitoyenEditorModel;
import com.sebaainf.mentionMarDiv.citoyenPackage.IPerson;
import com.sebaainf.mentionMarDiv.common.MyApp;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MentionEditorModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * Created by ${sebaainf.com} on 14/03/2015.
 */

public class IsmComponentFactory extends BasicComponentFactory {

    public static IsmDateFormatter formatter = new IsmDateFormatter();
    static Calendar calendar = new GregorianCalendar();

    /**
     *
     * method to create
     * important note :
     * Becarefull if you use two times the datePickerImpl object then it will hide
     * for the first use
     * @param model PresentationModel contain model wraping IPerson Bean
     *              that have property Date date_naiss and its setter and getter method
     * @param datePropertyName
     * @return
     */
    public static JDatePickerImpl createDatePickerImpl(final PresentationModel model,
                                                       String datePropertyName) {

        // with default datePattern "dd/MM/yyyy"

        UtilDateModel dateModel = new UtilDateModel();

        //dateModel.setDate(2000, 01, 01);

        Properties p = new Properties();
        /*p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");*/

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);


        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);

        datePicker.setShowYearButtons(true);
        datePicker.setButtonFocusable(true);
        datePicker.setTextEditable(true);
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.RIGHT);
        datePicker = initializeDatePicker(datePicker, model, datePropertyName);



        return datePicker;
    }

    private static JDatePickerImpl initializeDatePicker(final JDatePickerImpl datePicker,
                                                        final PresentationModel model,
                                                        final String datePropertyName) {

        //initialize datePicker


        String modelgetclass = model.getClass().getSimpleName();


        if ((model instanceof CitoyenEditorModel)) {

            Date date = ((IPerson) model.getBean()).getDate_naiss();
            if(date != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);

                datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePicker.getJDateInstantPanel().getModel().setSelected(true);

                try {
                    datePicker.getJFormattedTextField().setText(formatter.valueToString(date));
                    System.out.println("date is .." + formatter.valueToString(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } else if ((model instanceof MentionEditorModel)&&(datePropertyName
                                        .equals(Mention.PROPERTY_DATE_MAR))) {

            java.util.Date date = ((Mention)model.getBean()).getDate_mar();

            if (date != null) {

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);

                datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

            /*datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));*/
                datePicker.getJDateInstantPanel().getModel().setSelected(true);

                try {
                    datePicker.getJFormattedTextField().setText(formatter.valueToString(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } else if ((model instanceof MentionEditorModel)&&(datePropertyName
                .equals(Mention.PROPERTY_DATE_ACTE_MAR))) {

            java.util.Date date = ((Mention)model.getBean()).getDate_acte_mar();

            if (date != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);

                datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

            /*datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));*/
                datePicker.getJDateInstantPanel().getModel().setSelected(true);

                try {
                    datePicker.getJFormattedTextField().setText(formatter.valueToString(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else if ((model instanceof MentionEditorModel)&&(datePropertyName
                .equals(Mention.PROPERTY_DATE_DIV))) {

            java.util.Date date = ((Mention) model.getBean()).getDate_div();

            if (date != null) {

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            /*datePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));*/
            datePicker.getJDateInstantPanel().getModel().setSelected(true);

            try {
                datePicker.getJFormattedTextField().setText(formatter.valueToString(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        }
        datePicker.getModel().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                Object newVal = datePicker.getModel().getValue();
                // to change state of model and enable buttons valid and annule
                model.setBufferedValue(datePropertyName, newVal);
                //resetDatePicker(datePicker);
                    /*
                    JOptionPane.showMessageDialog(null, evt.getPropertyName() + " --> : "
                            + evt.getNewValue());
                    System.out.println("date changed .." + newVal);
                    //*/

            }
        }); // waw

        PropertyChangeListener l = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String text = evt.getNewValue() != null ? evt.getNewValue().toString() : "";
                System.out.println("jtextField ketbeted");

            }
        };
        datePicker.getJFormattedTextField().addPropertyChangeListener("value", l);

        resetDatePicker(datePicker);


        return datePicker;

    }


    /**
     * method to reset the default date and clear the getJFormattedTextField
     * @param datePicker
     */
    public static void resetDatePicker(JDatePickerImpl datePicker) {

        calendar.setTime(MyApp.defaultDate);
        if (datePicker.getModel().getYear()==(calendar.get(Calendar.YEAR))
                && datePicker.getModel().getMonth()==(calendar.get(Calendar.MONTH))
                && datePicker.getModel().getDay()==(calendar.get(Calendar.DAY_OF_MONTH))) {
            datePicker.getJFormattedTextField().setText("");
        }
    }

/*    public static JDatePickerImpl createDatePickerDecesImpl(final PresentationModel model) {

        // with default datePattern "dd/MM/yyyy"

        final UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(2000, 01, 01);

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, new Properties());


        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);

        datePicker.setShowYearButtons(true);
        datePicker.setButtonFocusable(true);
        datePicker.setTextEditable(true);
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.RIGHT);


        return datePicker;
    }*/

    // with datePattern "yyyy/MM/dd" for arabic alignement for exple
    public static JDatePickerImpl createDatePickerImpl(final PresentationModel model,
                                                       String datePropertyName,
                                                       String datePattern) {

        IsmComponentFactory.formatter = new IsmDateFormatter(datePattern);
        return IsmComponentFactory.createDatePickerImpl(model, datePropertyName);

    }


    public static JTextField createArabTextField(ValueModel valueModel) {

        JTextField arabiTextField = createTextField(valueModel, true);
        arabiTextField.setHorizontalAlignment(JTextField.RIGHT);
        return arabiTextField;
    }

}
