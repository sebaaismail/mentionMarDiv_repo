package com.sebaainf.mentionMarDiv.mentionPack;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.common.MyTableAdapter;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class ChooseMentFrame extends JFrame {

    private static ChooseMentFrame uniqueInstance;
    // dont make it singleton
    //private static ChooseMentFrame uniqueInstance;
    private Citoyen cit;
    private static Dimension screenSize, dimWin, dimPannel;

    private ChooseMentFrame(Citoyen cit) {


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

      // not Singleton but a special behavior of it, close last windows why call to new
    public static ChooseMentFrame getInstance(Citoyen cit) {

        if (uniqueInstance != null) {
            uniqueInstance.dispatchEvent(new WindowEvent(uniqueInstance, WindowEvent.WINDOW_CLOSING));
        }
        uniqueInstance = new ChooseMentFrame(cit);
        return uniqueInstance;
    }

    public JComponent createPanel() {

        List listMent = MyDaosMention.getListMentions(this.cit);



        //BeanAdapter beanAdapter = new BeanAdapter(selectionInList);

        MyTableAdapter tableAdapter = new MyTableAdapter(
                listMent,
                new String[] {Mention.PROPERTY_NUMACT_MAR, Mention.PROPERTY_DATE_MAR,
                Mention.PROPERTY_NP_CONJ_FR, Mention.PROPERTY_TRIBUNAL_DIV,Mention.PROPERTY_NP_CONJ_AR},
                new String[]{"Num acte mariage","Date mariage",
                        "NomPren Epouse", "المحكمة", "إسم ولقب الزوجة"});

        JTable table = new JTable(tableAdapter);

        tableAdapter.settingTable(table);
        //tableAdapter.setHeaders(table, new String[]{"إسم ولقب الزوجة", "المحكمة"});

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
