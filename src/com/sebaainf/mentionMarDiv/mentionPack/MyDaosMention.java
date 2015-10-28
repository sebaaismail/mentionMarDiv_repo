package com.sebaainf.mentionMarDiv.mentionPack;

import com.jenkov.db.itf.IDaos;
import com.jenkov.db.itf.PersistenceException;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.common.MyDaos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class MyDaosMention {

    /**
     * @param cit
     * @return list mentions of cit
     * @should return list mentions of cit
     */

    public static List<Mention> getListMentions(Citoyen cit) {

        List<Mention> list = new ArrayList<Mention>();

        try {

            String sql = "select * from mention where id_cit =?";
            IDaos daos = MyDaos.persistenceManager.createDaos();
            list = daos.getObjectDao().readList(Mention.class, sql, cit.getId_cit());

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    /**
     * @param id_ment
     * @return
     * @should return mention where id_ment is found
     */
    public static Mention getMention(int id_ment) {

        Mention mention = null;
        try {

            String sql = "select * from mention where id_ment =?";
            IDaos daos = MyDaos.persistenceManager.createDaos();
            mention = daos.getObjectDao().read(Mention.class, sql, id_ment);

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            return mention;
        }
    }

}
