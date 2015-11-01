package com.sebaainf.mentionMarDiv.myTests;

import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.citoyenPackage.MyDaosCitoyen;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by ${sebaainf.com} on 20/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class MyDaosCitoyenTest {


    /**
     * @verifies return citoyen with id_cit
     * @see MyDaosCitoyen#getCitoyen(int)
     */
    @Test
    public void testGetCitoyen_shouldReturnCitoyenWithId_cit() throws Exception {

        //TODO auto-generated
        Citoyen cit = MyDaosCitoyen.getCitoyen(1);
        assertThat(cit.getPrenom_fr(), equalTo("ismail"));
    }

    /**
     * @verifies return list citoyen with nom prenom
     * @see MyDaosCitoyen#getListCitoyen(String, String)
     */
    @Test
    public void testGetListCitoyen_shouldReturnListCitoyenWithNomPrenom() throws Exception {


        List<Citoyen> list = MyDaosCitoyen.getListCit("seb", "ism", true);
        assertThat(list.get(0).getPrenom_fr(), equalTo("ismail"));
    }
}
