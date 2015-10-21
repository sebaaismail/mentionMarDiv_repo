package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.sebaainf.mentionMarDiv.common.MyApp;
import com.sebaainf.mentionMarDiv.common.MyTableAdapter;
import com.sebaainf.mentionMarDiv.mentionPack.ChooseMentFrame;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by ${sebaainf.com} on 20/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class ResultaRechJFrame extends JFrame {

    private List listCit;
    private static Dimension screenSize, dimWin, dimPannel;

    private static ResultaRechJFrame uniqueFrame;

    private class CitoyenTableAdapter extends AbstractTableAdapter {

        public CitoyenTableAdapter(ListModel listModel, String[] columnNames){
            super(listModel, columnNames);
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

            Citoyen cit = (Citoyen) getRow(rowIndex);
            if (columnIndex == 0){
                return cit.getNom_ar();
            } else if (columnIndex == 1){
                return cit.getPrenom_ar();
            } else if (columnIndex == 2){
                return cit.getDate_naiss();
            } else if (columnIndex == 3){
                return cit.getP_pere();
            } else if (columnIndex == 4){
                return cit.getNp_mere();
            } else if (columnIndex == 5){
                return cit.getNom_fr();
            } else {
                return cit.getPrenom_fr();
            }
        }
    }

    private ResultaRechJFrame(List listCit) {

        UIManager.put("Table.background", new ColorUIResource(MyApp.tableBackColor));
        UIManager.put("Table.alternateRowColor", MyApp.alternateRowColor);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        this.listCit = listCit;
        dimWin = new Dimension(6 * (int) screenSize.getWidth() / 10, 4 * (int) screenSize.getHeight() / 10);
        dimPannel = new Dimension(5 * (int) screenSize.getWidth() / 10, 3 * (int) screenSize.getHeight() / 10);

        this.setTitle("Resultat de recherche !");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.createPanel());

        this.pack();

        this.setSize(dimWin);

        this.setLocationRelativeTo(null); //to center the frame in the middle of screen
    }

    // Singleton

    public static ResultaRechJFrame getInstance(List listCit) {

        if (uniqueFrame == null) {
            uniqueFrame = new ResultaRechJFrame(listCit);
        }
        return uniqueFrame;

    }





    public JComponent createPanel() {


        //final List listCitoyens = new ArrayListModel(this.listCit);

        //final SelectionInList selectionInList = new SelectionInList(listCit);
        MyTableAdapter tableAdapter = new MyTableAdapter(
                listCit,new String[] {Citoyen.PROPERTY_NOM_AR, Citoyen.PROPERTY_PRENOM_AR,
                Citoyen.PROPERTY_DATE_NAISS, Citoyen.PROPERTY_P_PERE, Citoyen.PROPERTY_NP_MERE,
                Citoyen.PROPERTY_NOM_FR, Citoyen.PROPERTY_PRENOM_FR}
                , new String[] {"الإسم", "اللقب", "تاريخ الإزدياد",
                        "إسم الأب", "إسم الأم", "Nom", "Prenom"});
        JTable table = new JTable(tableAdapter);

        tableAdapter.settingTable(table);

        // on double Click Action  ******************

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    // your valueChanged overridden method

                    Citoyen selectedCit = (Citoyen) new ArrayListModel(listCit).getElementAt(table.getSelectedRow());
                    ChooseMentFrame mentFrame = ChooseMentFrame.getInstance(selectedCit);
                    mentFrame.setVisible(true);

                }
            }
        });

        //*********************************


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dimPannel);


        return FormBuilder.create()
                .columns("p:g")
                .rows("p:g")
                .padding(Paddings.DIALOG)
                .add(scrollPane).xy(1, 1)
                .build();

    }


}
