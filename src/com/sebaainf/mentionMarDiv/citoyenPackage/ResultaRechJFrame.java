package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.mentionPack.ChooseMentFrame;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
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

        UIManager.put("Table.background", new ColorUIResource(Color.decode("#D7EAF5")));
        UIManager.put("Table.alternateRowColor", Color.decode("#F5F5D7"));
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


        final ListModel listCitoyens = new ArrayListModel(this.listCit);

        final SelectionInList selectionInList = new SelectionInList(listCit);

        //BeanAdapter beanAdapter = new BeanAdapter(selectionInList);

        JTable table = new JTable(
                new CitoyenTableAdapter(
                        selectionInList,
                        new String[] {"الإسم", "اللقب", "تاريخ الإزدياد",
                                "إسم الأب", "إسم الأم", "Nom", "Prenom"}));

        settingTable(table);

        // on double Click Action  ******************

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    // your valueChanged overridden method

                    Citoyen selectedCit = (Citoyen) listCitoyens.getElementAt(table.getSelectedRow());
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

    public static void settingTable(JTable table) {

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#919AFA"));
        header.setForeground(Color.white);

        FontMetrics metrics = table.getFontMetrics(table.getFont());
        int fontHeight = metrics.getHeight();
        table.setRowHeight( fontHeight + 4 );
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

    }


}
