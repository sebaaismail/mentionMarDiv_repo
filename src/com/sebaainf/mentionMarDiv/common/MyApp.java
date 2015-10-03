package com.sebaainf.mentionMarDiv.common;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.sebaainf.mentionMarDiv.view.SearchJFrame;
import com.sebaainf.mentionMarDiv.view.themes.BlackTheme;
import com.sebaainf.mentionMarDiv.view.themes.MyTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 24/01/2015.
 */
public class MyApp {

    public static int default_id_c = 3114; //todo get from general variable or file config
    public static MyTheme theme = new BlackTheme(); //todo put in config

    //public static MyTheme theme = new GreyTheme(); //todo put in config
    public static void main(String[] args) {

        /**
         * set jgoodies Look And Feel
         */

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //*****************************************************

        System.out.println("Welcome to Mentions app");

        JFrame frame = new SearchJFrame();

        frame.setVisible(true);


        System.out.println("end app");


    }

}
