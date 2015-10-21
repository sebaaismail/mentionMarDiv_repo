package com.sebaainf.mentionMarDiv.mentionPack;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.ResultaRechJFrame;
import com.sebaainf.mentionMarDiv.common.MyTableAdapter;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.List;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class ChooseMentFrame extends JFrame {

    private static ChooseMentFrame uniqueInstance;
    private Citoyen cit;
    private static Dimension screenSize, dimWin, dimPannel;

    private ChooseMentFrame(Citoyen cit) {


        UIManager.put("Table.background", new ColorUIResource(Color.decode("#D7EAF5")));
        UIManager.put("Table.alternateRowColor", Color.decode("#F5F5D7"));
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        this.cit = cit;
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
    public static ChooseMentFrame getInstance(Citoyen cit) {

        if (uniqueInstance == null) {
            uniqueInstance = new ChooseMentFrame(cit);
        }
        return uniqueInstance;
    }

    public JComponent createPanel() {

        List listMent = MyDaosMention.getListMentions(this.cit);

        ListModel listMentions = new ArrayListModel(listMent);

        SelectionInList selectionInList = new SelectionInList(listMent);

        //BeanAdapter beanAdapter = new BeanAdapter(selectionInList);

        JTable table = new JTable(
                new MyTableAdapter(
                        selectionInList,
                        new String[] {Mention.PROPERTY_NP_CONJ_FR, Mention.PROPERTY_TRIBUNAL_DIV}));

        ResultaRechJFrame.settingTable(table);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dimPannel);


        // TODO ajouter panel pour afficher infos du citoyen : nom prenom etc
        // devant le tableau de choix des mentions a manipuler
        return FormBuilder.create()
                .columns("p:g")
                .rows("p:g")
                .padding(Paddings.DIALOG)
                .add(scrollPane).xy(1, 1)
                .build();

    }


}
