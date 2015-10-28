package com.sebaainf.mentionMarDiv.common;

import com.jenkov.db.PersistenceManager;
import com.jenkov.db.itf.IDaos;
import com.jenkov.db.itf.PersistenceException;
import com.jenkov.db.jdbc.SimpleDataSource;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;

import javax.sql.DataSource;
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
     * @param id_cit
     * @return
     * @should return mention du citoyen ayant id_cit
     */
    public static Mention getMention(int id_cit, int numact_mar) {


        Mention mention = null;
        try {

            String sql = "select * from mention where id_cit =" + id_cit
                    + " and numact_mar =" + numact_mar;
            IDaos daos = MyDaos.persistenceManager.createDaos();
            mention = daos.getObjectDao().read(Mention.class, sql);
            //mention.setCitoyen(MyDaosCitoyen.getCitoyen(id_cit));


        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return mention;
        }

    }

    /**
     * @param cit
     * @return
     * @should return mentions of the citoyen cit
     */
    public static TreeSet<Mention> getMentions(Citoyen cit) {

        List<Mention> listMents;
        TreeSet<Mention> mentions = new TreeSet<Mention>();
        if (cit.getEst_masculin()) {
            try {
                String sql = "select * from mention where id_cit="
                        + cit.getId_cit();
                IDaos daos = MyDaos.persistenceManager.createDaos();
                listMents = daos.getObjectDao().readList(Mention.class, sql);

                if (listMents == null) {
                    mentions = new TreeSet<Mention>();
                } else {
                    mentions = new TreeSet<Mention>(listMents);
                    for (Mention ment : mentions) {
                        //ment.setCitoyen(MyDaosCitoyen.getCitoyen(cit.getId_cit()));
                    }
                }


            } catch (PersistenceException e) {
                e.printStackTrace();
            }

        }

        return mentions;
    }

}
