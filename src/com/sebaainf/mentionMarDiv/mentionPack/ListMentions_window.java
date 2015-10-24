package com.sebaainf.mentionMarDiv.mentionPack;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.CitoyenEditorModel;
import com.sebaainf.mentionMarDiv.common.Editor_window;
import com.sebaainf.mentionMarDiv.common.MyTableAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class ListMentions_window extends JFrame {

    private static ListMentions_window uniqueInstance;
    private static Dimension screenSize, dimWin, dimPannel;
    // dont make it singleton
    //private static ChooseMentFrame uniqueInstance;
    private Citoyen cit;

    private ListMentions_window(Citoyen cit) {


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        this.cit = cit;
        dimWin = new Dimension(6 * (int) screenSize.getWidth() / 10, 4 * (int) screenSize.getHeight() / 10);
        dimPannel = new Dimension(5 * (int) screenSize.getWidth() / 10, 3 * (int) screenSize.getHeight() / 10);

        this.setTitle("Liste des mentions");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.createPanel());

        this.pack();

        this.setSize(dimWin);

        this.setLocationRelativeTo(null); //to center the frame in the middle of screen

    }

    // not Singleton but a special behavior of it, close last windows why call to new
    public static ListMentions_window getInstance(Citoyen cit) {

        if (uniqueInstance != null) {
            uniqueInstance.dispatchEvent(new WindowEvent(uniqueInstance, WindowEvent.WINDOW_CLOSING));
        }
        uniqueInstance = new ListMentions_window(cit);
        return uniqueInstance;
    }

    public JComponent createPanel() {

        //BeanAdapter beanAdapter = new BeanAdapter(selectionInList);

        MyTableAdapter tableAdapter = new MyTableAdapter(
                this.cit.getListMentions(),
                new String[]{Mention.PROPERTY_NUMACT_MAR, Mention.PROPERTY_DATE_MAR,
                        Mention.PROPERTY_NP_CONJ_FR, Mention.PROPERTY_TRIBUNAL_DIV, Mention.PROPERTY_NP_CONJ_AR},
                new String[]{"Num acte mariage", "Date mariage",
                        "NomPren Epouse", "المحكمة", "إسم ولقب الزوجة"});

        JTable table = new JTable(tableAdapter);

        tableAdapter.settingTable(table);
        //tableAdapter.setHeaders(table, new String[]{"إسم ولقب الزوجة", "المحكمة"});

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dimPannel);

        // on double Click Action  ******************
        table.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param me
             */
            @Override
            public void mousePressed(MouseEvent me) {

                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    // TODO call editor cit/ment window
                    Mention selectedMent = (Mention) new ArrayListModel(cit.getListMentions())
                            .getElementAt(table.getSelectedRow());

                    //FicheMentionCit fiche = new FicheMentionCit(cit, selectedMent);
                    //EditorView editorWin = EditorView.getInstance(fiche);
                    //editorWin.setVisible(true);
                    CitoyenEditorModel citModel = new CitoyenEditorModel(cit);
                    MentionEditorModel mentModel = new MentionEditorModel(selectedMent);

                    Editor_window view = new Editor_window(citModel, mentModel);
                    view.setVisible(true);

                    System.out.println("i am here, building the center of Editor ... citoyen name is :");
                    System.out.println(view.nom_ar.getText() + " " + view.prenom_ar.getText());
                    System.out.println("sahitah");

                }
            }
        });


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
