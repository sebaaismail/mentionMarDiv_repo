package com.sebaainf.mentionMarDiv.common;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class MyTableAdapter extends AbstractTableAdapter {

    private  String[] columnNames;
    public MyTableAdapter(ListModel listModel, String[] columnNames){
        super(listModel, columnNames);
        this.columnNames = columnNames;
    }
    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {


        Object obj = getRow(rowIndex);

        for (int i=0; i < columnNames.length; i++ ){
            if (columnIndex == i) {
                try {
                    return PropertyUtils.getProperty(obj, columnNames[i]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * @should test MyTableAdapter
     */
    void tester(){

    }
}
