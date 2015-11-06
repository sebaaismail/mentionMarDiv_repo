package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.itf.PersistenceException;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.MyDaosCitoyen;
import com.sebaainf.mentionMarDiv.mentionPack.MyDaosMention;
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
public class ReportView {

    public static void report(ReportedBean bigBean) {


        System.out.println("reporting began ...");

        // Preparing data

        //ficheFam.prepareTexts();
        Collection<ReportedBean> data = new Vector<ReportedBean>();
        data.add(bigBean);


        try {
            JasperReport jasperReport1 = null;
            JasperDesign jasperDesign1 = null;
            JasperPrint jasperPrint1 = null;



            JRBeanCollectionDataSource beanDs1;

            Map parameters = new HashMap();

            ReportedBean myBigBean = null;

            beanDs1 = new JRBeanCollectionDataSource(data);

            //parameters.put("COUNT_ROWS", beanDs2.getData().size());
            //parameters.put("myText", " سنـة ");

            jasperDesign1 = JRXmlLoader.load("files/reportMention.jrxml");

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

        System.out.println("end reporting ...");

    }

    // for connect the ireport
    /**
     *
     * @return
     * @should return a citoyen with a mention
     */
    public static Collection<ReportedBean> getMyMention() {

        ReportedBean myBean = null;
        Vector data = new Vector<ReportedBean>();

        try {
            Citoyen cit = MyDaosCitoyen.getCitoyen(1);
            myBean = new ReportedBean(cit , MyDaosMention.getMention(1));
            //maFiche = new FicheFam();

            data.add(myBean);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return data;
        }

    }

}
