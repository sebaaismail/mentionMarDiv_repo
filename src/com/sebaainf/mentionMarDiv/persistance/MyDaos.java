package com.sebaainf.mentionMarDiv.persistance;

import com.jenkov.db.PersistenceManager;
import com.jenkov.db.itf.IDaos;
import com.jenkov.db.itf.PersistenceException;
import com.jenkov.db.jdbc.SimpleDataSource;
import com.sebaainf.mentionMarDiv.citoyen.Citoyen;
import com.sebaainf.mentionMarDiv.common.Mariage;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by admin on 10/01/2015.
 */
public class MyDaos {

    /**
     * ses infos sont ici pour developelent
     * dans la phase de production (apres depoloyment de laplication)
     * ses infos serons dans un fichier de configuration caché
     */

    static String driver = "com.mysql.jdbc.Driver";
    static String host = "localhost";
    static String database = "dbmention";
    static String user = "root";
    static String password = "admin";
    //    String url = "jdbc:mysql"://127.0.0.1/dbfichfamil";
    static String url = "jdbc:mysql://" + host + "/" + database;

    protected final static DataSource datasource = new SimpleDataSource(driver, url, user, password);

    public static final PersistenceManager persistenceManager = new PersistenceManager(datasource);


    /**
     * @param num_actmar
     * @param date_mar
     * @param lieu_mar
     * @return
     * @should return family that match the parameters
     */

    public static Mariage getMariage(int num_actmar, Date date_mar, int lieu_mar) {

        Mariage mar = null;
        try {

            String sql = "select * from mariage where numact_mar =" + num_actmar
                    + " and date_mar='" + date_mar + "' and lieu_mar =" + lieu_mar;
            IDaos daos = MyDaos.persistenceManager.createDaos();
            mar = daos.getObjectDao().read(Mariage.class, sql);
            mar.setEpoux(MyDaosCitoyen.getCitoyen(mar.getId_epoux()));
            mar.setEpouse(MyDaosCitoyen.getCitoyen(mar.getId_epouse()));


        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return mar;
        }

    }

    /**
     * @param cit
     * @return
     * @should return families of the citoyen cit
     */
    public static TreeSet<Mariage> getFamilies(Citoyen cit) {

        List<Mariage> listfams;
        TreeSet<Mariage> fams = new TreeSet<Mariage>();
        if (cit.getEst_masculin()) {
            try {
                String sql = "select * from mariage where id_epoux=" + cit.getId_cit();
                IDaos daos = MyDaos.persistenceManager.createDaos();
                listfams = daos.getObjectDao().readList(Mariage.class, sql);

                fams = new TreeSet<Mariage>(listfams);
                for (Mariage mar : fams) {
                    mar.setEpouse(MyDaosCitoyen.getCitoyen(mar.getId_epouse()));
                }

            } catch (PersistenceException e) {
                e.printStackTrace();
            }

        }

        return fams;
    }


    public static void main(String[] args) throws SQLException {

        IDaos daos = null;
        Citoyen citoyen;

        /*
         Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn =
                    DriverManager.getConnection(url, user, password);
            System.out.println( "1 :)");
            daos= MyDaos.persistenceManager.createDaos();
        }catch(Exception ex){

        }
        //*/
        try {

            daos = MyDaos.persistenceManager.createDaos();
            try {
                System.out.println("inserting :)");
                Citoyen cit = new Citoyen();
                cit.setNum_actnaiss(76);
                cit.setAnnee_actnaiss(1985);
                cit.setCode_lieunaiss(31014);
                cit.setNom_ar("سبع");
                cit.setPrenom_ar("إسماعيل");
                cit.setNom_fr("sebaa");
                cit.setPrenom_fr("ismail");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                cit.setDate_naiss(new Date(sdf.parse("20/08/1985").getTime()));
                cit.setP_pere("mohamed");
                cit.setNp_mere("frihat halima");
                if (MyDaosCitoyen.getCitoyen(cit.getNum_actnaiss(), cit.getAnnee_actnaiss(), cit.getCode_lieunaiss()) == null) {
                    daos.getObjectDao().insert(cit);
                } else {
                    //message erreur
                }

                //********************************

                cit = new Citoyen();

                cit.setNum_actnaiss(88);
                cit.setAnnee_actnaiss(1970);
                cit.setCode_lieunaiss(31001);
                cit.setNom_ar("عربي");
                cit.setPrenom_ar("أحمد");
                cit.setNom_fr("arabi");
                cit.setPrenom_fr("ahmed");

                cit.setDate_naiss(new Date(sdf.parse("01/01/1970").getTime()));
                cit.setP_pere("ali");
                cit.setNp_mere("saad aicha");

                if (MyDaosCitoyen.getCitoyen(cit.getNum_actnaiss(), cit.getAnnee_actnaiss(), cit.getCode_lieunaiss()) == null) {
                    daos.getObjectDao().insert(cit);
                } else {
                    //message erreur
                }

                System.out.println("3 :)");
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            String pr;

            citoyen = MyDaosCitoyen.getCitoyen(76, 1985, 31014);
            //citoyen = MyDaos.getCitoyen(88,1970,31001);
            String nom = citoyen.getNom_ar();
            pr = citoyen.getPrenom_ar();
            System.out.println(nom + " " + pr);
            //********
            String sql2 = "select * from citoyen where id_cit>1";
            citoyen = daos.getObjectDao().read(Citoyen.class, sql2);
            pr = citoyen.getPrenom_ar();
            System.out.println(pr);
            //**********************************


            String sql = "select * from citoyen where prenom_fr=?";
            System.out.println("4 :)");
            Long aLong = daos.getJdbcDao().readLong(sql, "ismail");
            System.out.println("l'id de smail est  " + aLong.toString());
        } catch (Exception ex) {

        } finally {
            try {
                daos.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("la fin :)");
        }

    }

}
