package com.sebaainf.mentionMarDiv.common;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.common.collect.ArrayListModel;
import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import com.sebaainf.mentionMarDiv.mentionPack.Mention;
import com.sebaainf.mentionMarDiv.mentionPack.MyDaosMention;
import junit.framework.Assert;
import junit.framework.TestCase;

import javax.swing.*;
import java.util.List;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class MyTableAdapterTest extends TestCase {

    /**
     * @verifies test MyTableAdapter
     * @see MyTableAdapter#tester()
     */
    public void testTester_shouldTestMyTableAdapter() throws Exception {

        Citoyen cit = new Citoyen();
        cit.setId_cit(1);
        List listMent = MyDaosMention.getListMentions(cit);



        //BeanAdapter beanAdapter = new BeanAdapter(selectionInList);

        MyTableAdapter tableAdapter = new MyTableAdapter(
                listMent,
                new String[] {Mention.PROPERTY_NP_CONJ_FR, Mention.PROPERTY_TRIBUNAL_DIV});
        JTable table = new JTable();


        tableAdapter.settingTable(table);

        String hoho = (String) table.getValueAt(2, 1);
        String hoho2 = (String) table.getValueAt(1, 1);
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
