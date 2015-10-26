package com.sebaainf.mentionMarDiv.common;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ${sebaainf.com} on 23/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public abstract class IsmAbstractJFrame extends JFrame{

    public static Dimension screenSize;

    protected abstract JComponent buildContent();
    protected abstract void initComponents();


    protected void configContent() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(buildContent());
        this.pack();
        this.setSize(new Dimension(8 * (int) screenSize.getWidth() / 10,
                9 * (int) screenSize.getHeight() / 10));
        this.setLocationRelativeTo(null);
    }


    // you have to add all your JComponent in that list
    abstract ArrayList<JComponent> getListComponents();
}
