package com.sebaainf.mentionMarDiv.view;

import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by admin on 07/02/2015.
 */
public class ReportMention {

    public static void report(Mention mention) {


        System.out.println("Welcome to Fiche familiale app... testing nested beans jasper report");

        // Preparing data

        //mention.prepareTexts();
        Collection<Mention> data = new Vector<Mention>();
        data.add(mention);


        try {
            JasperReport jasperReport1 = null;

            JasperDesign jasperDesign1 = null;

            JasperPrint jasperPrint1 = null;



            JRBeanCollectionDataSource beanDs1, beanDs2;

            Map parameters = new HashMap();

            Mention maMention = null;

            beanDs1 = new JRBeanCollectionDataSource(data);


            parameters.put("myText", " سنـة ");

            jasperDesign1 = JRXmlLoader.load("reports/tryNestedReport1.jrxml");


            jasperReport1 = JasperCompileManager.compileReport(jasperDesign1);



            jasperPrint1 = JasperFillManager.fillReport(jasperReport1, parameters, beanDs1);


            /* if we went to merge the two pages in one

            jasperPrint1.addPage(jasperPrint2.getPages().get(0));
            //*/

            JasperViewer.viewReport(jasperPrint1);
            int x = JasperViewer.getFrames()[1].getLocation().x;
            int y = JasperViewer.getFrames()[1].getLocation().y;
            JasperViewer.getFrames()[1].setLocation(x + 100, y + 100);


        } catch (JRException e) {
            e.printStackTrace();
        }

        System.out.println("end test");

    }

}
