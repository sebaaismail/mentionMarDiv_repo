package com.sebaainf.mentionMarDiv.citoyenPackage;

import com.jenkov.db.itf.IDaos;
import com.jenkov.db.itf.PersistenceException;
import com.sebaainf.mentionMarDiv.common.MyDaos;
import com.sebaainf.mentionMarDiv.ismUtils.IsmPrintStream;

import java.util.*;

//import com.sebaainf.fichfamil.common.Deces;

/**
 * Created by admin on 25/01/2015.
 * Class to create new, update or delete Citoyens
 */
public class MyDaosCitoyen {


    /**
     * @param nom
     * @param prenom
     * @param inFrench
     * @return
     * @throws PersistenceException
     * @should return list of citoyens that match nom, prenom parameters
     */
    public static List<Citoyen> getListCit(String nom, String prenom, boolean inFrench) throws PersistenceException {

        List<Citoyen> list = new ArrayList<Citoyen>();

        try {
            if (!nom.equals("") && !prenom.equals("")) {

                String sql = "";
                if (inFrench) {
                    sql = "select * from citoyen where nom_fr like '"
                            + nom + "%' and prenom_fr like '" + prenom + "%'";
                } else {
                    sql = "select * from citoyen where nom_ar like '"
                            + nom + "%' and prenom_ar like '" + prenom + "%'";
                }
                IDaos daos = MyDaos.persistenceManager.createDaos();
                list = daos.getObjectDao().readList(Citoyen.class, sql);
            }

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return list;
        }

    }

    /**
     * @param id_cit
     * @return
     * @throws com.jenkov.db.itf.PersistenceException
     * @should return citoyen with id_cit
     */
    public static Citoyen getCitoyen(int id_cit) throws PersistenceException {

        Citoyen citoyen = null;


        try {

            String sql = "select * from citoyen where id_cit=?";
            IDaos daos = MyDaos.persistenceManager.createDaos();
            citoyen = daos.getObjectDao().read(Citoyen.class, sql, id_cit);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return citoyen;
        }
    }


    /**
     * @param nom_fr
     * @param prenom_fr
     * @return
     * @should return list citoyen with nom prenom
     */
    public static TreeSet<Citoyen> getListCitoyen(String nom_fr, String prenom_fr) {


        List<Citoyen> listCitoyens;
        TreeSet<Citoyen> setCitoyens = new TreeSet<Citoyen>();
        try {
            String sql = "select * from citoyen where nom_fr='" + nom_fr +
                    "' and prenom_fr='" + prenom_fr;
            IDaos daos = MyDaos.persistenceManager.createDaos();
            listCitoyens = daos.getObjectDao().readList(Citoyen.class, sql);
            if (listCitoyens != null) {
                setCitoyens = new TreeSet<Citoyen>(listCitoyens);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return setCitoyens;
        }

    }

    /**
     * method to add new citoyen
     *
     * @param cit
     * @return
     * @should insert citoyen cit into Data base
     */
    public static Citoyen insertCitoyen(Citoyen cit) throws PersistenceException{

            IDaos daos = MyDaos.persistenceManager.createDaos();
            daos.getObjectDao().insert(cit);
        return cit;
    }


    /**
     * method to update
     *
     * @param cit
     * @return
     * @should update citoyen cit
     */
    public static Citoyen updateCitoyen(Citoyen cit) throws PersistenceException {


            IDaos daos = MyDaos.persistenceManager.createDaos();
            daos.getObjectDao().update(cit);

        return cit;

    }

    /**
     * method to delete citoyen cit
     *
     * @param cit
     * @return
     * @should delete citoyen cit
     */
    public static boolean deleteCitoyen(Citoyen cit) {

        boolean flag = false;

        try {
            IDaos daos = MyDaos.persistenceManager.createDaos();
            daos.getObjectDao().delete(cit);
            flag = true;
            IsmPrintStream.logging("citoyen deleted");

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            IsmPrintStream.logging("flag :" + flag);
            return flag;
        }


    }

    /**
     * method to delete citoyen cit
     *
     * @param id_cit
     * @return
     * @should delete citoyen cit by id_cit
     */
    public static boolean deleteCitoyen(int id_cit) {

        boolean flag = false;
        try {
            if (getCitoyen(id_cit) != null) {

                IDaos daos = MyDaos.persistenceManager.createDaos();
                daos.getObjectDao().delete(Citoyen.class, id_cit);
                flag = true;
                IsmPrintStream.logging("citoyen deleted");
            } else {
                //todo not tested method
                IsmPrintStream.logging("citoyen not found in data base !!");

            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            IsmPrintStream.logging("flag :" + flag);
            return flag;
        }

    }

    public static Collection<Citoyen> getCitoyens() throws PersistenceException {

        //createBeanCollection

        Vector citoyens = new Vector();
        Citoyen citoyen = null;

        try {
            /*String sql    = "select * from citoyen where num_actnaiss="+new Integer(num_actnaiss).toString()+" and annee_actnaiss="
                    +new Integer(annee_naiss).toString()+" and code_lieunaiss="+new Integer(lieu_naiss).toString();
          */
            String sql = "select * from citoyen where id_cit>0";
            IDaos daos = MyDaos.persistenceManager.createDaos();
            citoyens = (Vector) daos.getObjectDao().readList(Citoyen.class, sql);

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return citoyens;
        }
    }

}
