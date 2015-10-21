package com.sebaainf.mentionMarDiv.view;

import com.jgoodies.forms.layout.FormLayout;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.common.MyApp;
import com.sebaainf.mentionMarDiv.presentation.CitoyenPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ${sebaainf.com} on 23/02/2015.
 */
public final class MentionJFrame extends JFrame {

    private static Dimension screenSize;
    private final Mention mention;




    public MentionJFrame(Mention mention) {
        // TODO

        this.mention = mention;
        this.setTitle("Fiche Familiale");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.createPanel());

        this.pack();
        this.setSize(new Dimension(8 * (int) screenSize.getWidth() / 10, 9 * (int) screenSize.getHeight() / 10));

        this.setLocationRelativeTo(null); //to center the frame in the middle of screen
    }

    private JComponent createPanel() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();


        LayoutManager layout = new BorderLayout();

        JComponent panel = new JPanel(layout);

        panel.add(buildButtonBarPanel(), BorderLayout.WEST);
        panel.add(buildEpouxInfoPanel() , BorderLayout.CENTER);
        panel.add(buildEpouxDecesInfoPanel(), BorderLayout.EAST);
        panel.add(buildMariageInfoPanel(), BorderLayout.SOUTH);

        return panel;
    }

    private JComponent buildMariageInfoPanel() {

        //todo
        return new JPanel();
    }

    private JComponent buildEpouxDecesInfoPanel() {

        //todo
        return new JPanel();
    }

    private JComponent buildEpouxInfoPanel() {

        // TODO implement and return CitoyenHomeView


        //todo Testing JComboBox binding
        //ficheFam.getCitoyen().setSit_famil("d");
       // CitoyenPresentation presenter = new CitoyenPresentation(mention.getCitoyen());
        CitoyenPresentation presenter = new CitoyenPresentation(new Citoyen());
        // TODO set CitoyenManagerUI in place CitoyenManagerUI_Test
        //CitoyenManagerUI_Test app = new CitoyenManagerUI_Test(presenter);

        //return app.getPanel();
        //CitoyenEditorModel model = new CitoyenEditorModel(mention.getCitoyen());
        //CitoyenEditorModel model = new CitoyenEditorModel(new Citoyen());
        //CitoyenEditorView view = new CitoyenEditorView(model);
        //CitoyenHomeView view = new CitoyenHomeView(model);

        return null;
    }

    private JComponent buildButtonBarPanel() {

        LayoutManager layout = new FormLayout("center:pref", "center:pref");
        JComponent panel = new JPanel(layout);


        JButton buttonApercu = new JButton("Aperçu");
        JButton buttonModifier = new JButton("Modifier Citoyen");
        JButton buttonQuitter = new JButton("Quitter");
        JButton buttonEnfants = new JButton("Enfants");
        JButton buttonModifierMariage = new JButton("Modifier Mariage");

        IsmButtonStackBuilder builder = new IsmButtonStackBuilder((JPanel) panel, screenSize);

        builder.setBackground(MyApp.theme.buttonBarColor); //todo color


        //builder.getPanel().setLayout(layout);
        //todo for testing
        buttonApercu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*JOptionPane.showMessageDialog(null, "citoyen " + mention.getCitoyen().getNom_fr()
                        + " " + mention.getCitoyen().getPrenom_fr() + " est comme situation familiale : " +
                        mention.getCitoyen().getSit_famil());*/
            }
        });
        buttonModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO modify and return CitoyenEditorView

                //todo Testing JComboBox binding
                //ficheFam.getCitoyen().setSit_famil("d");
                // * todo CitoyenPresentation presenter = new CitoyenPresentation(mention.getCitoyen());
                // TODO set CitoyenManagerUI in place CitoyenManagerUI_Test
                //CitoyenManagerUI_Test app = new CitoyenManagerUI_Test(presenter);

                //return app.getPanel();
                //   *todo CitoyenEditorModel model = new CitoyenEditorModel(mention.getCitoyen());
                //CitoyenEditorModel model = new CitoyenEditorModel(new Citoyen());
                //CitoyenEditorView view = new CitoyenEditorView(model);
                //CitoyenEditorView view = new CitoyenEditorView(model);

                JFrame editCitPanel = new JFrame();
                //editCitPanel.add(view.showDialog(null));
                editCitPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                editCitPanel.setTitle("Modifier Citoyen");

                editCitPanel.pack();
                editCitPanel.setSize(new Dimension(8 * (int) screenSize.getWidth() / 10, 9 * (int) screenSize.getHeight() / 10));

                editCitPanel.setLocationRelativeTo(null);
                editCitPanel.setVisible(true);


                  }
        });

        builder.addGlue();
        builder.addButton(buttonApercu);
        builder.addUnrelatedGap();

        builder.addButton(buttonModifier);
        builder.addUnrelatedGap();

        builder.addButton(buttonQuitter);

        builder.addGlue();

        builder.addButton(buttonEnfants);
        builder.addUnrelatedGap();

        builder.addButton(buttonModifierMariage);
        builder.addUnrelatedGap();
        builder.addUnrelatedGap();

        // setting sizes of buttons

        return builder.getPanel();
    }

}
