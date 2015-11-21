package com.sebaainf.mentionMarDiv.common;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.sebaainf.mentionMarDiv.citoyenPackage.SearchCit_window;
import com.sebaainf.mentionMarDiv.ismUtils.IsmAbstractJFrame;
import com.sebaainf.mentionMarDiv.ismUtils.IsmPrintStream;
import com.sebaainf.mentionMarDiv.view.themes.BlackTheme;
import com.sebaainf.mentionMarDiv.view.themes.MyTheme;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 24/01/2015.
 */
public class MyApp {

    public static int default_id_c = 3114; //todo maybe get from general variable or file config
    public static MyTheme theme = new BlackTheme();
    public static Color tableBackColor = Color.white;
    public static Color alternateRowColor = Color.lightGray;
    public static Date defaultDate;


    //public static Color tableBackgColor = Color.decode("#D7EAF5");

    //public static MyTheme theme = new GreyTheme(); // put in config
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {

        java.util.Enumeration keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    public static void main(String[] args) {

        /**
         * set jgoodies Look And Feel
         */

        try {
            defaultDate =new SimpleDateFormat("yyyy/MM/dd").parse("1900/01/01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        IsmPrintStream.prepareLogFile();
        IsmPrintStream.logging("Welcome to Mentions app");

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
                    UIManager.put("TextField.inactiveBackground", new ColorUIResource(Color.blue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setUIFont(new javax.swing.plaf.FontUIResource("Times New Roman", Font.PLAIN, 18));

        //*****************************************************



        Toolkit toolkit = Toolkit.getDefaultToolkit();
        IsmAbstractJFrame.screenSize = toolkit.getScreenSize();

        JFrame frame = new SearchCit_window();

        frame.setVisible(true);



    }

}
