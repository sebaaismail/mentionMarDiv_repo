package com.sebaainf.mentionMarDiv.mentionPack;

import com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by ${sebaainf.com} on 21/10/2015.
 * https://bitbucket.org/sebaa_ismail
 * https://github.com/sebaaismail
 */
public class MyDaosMentionTest extends TestCase {

    /**
     * @verifies return list mentions of cit
     * @see MyDaosMention#getListMentions(com.sebaainf.mentionMarDiv.citoyenPackage.Citoyen)
     */
    public void testGetListMentions_shouldReturnListMentionsOfCit() throws Exception {

        Citoyen ismail = new Citoyen();
        ismail.setId_cit(1);
        List<Mention> list = MyDaosMention.getListMentions(ismail);
        assertThat(list.get(0).getNp_conj_fr(), equalTo("sihem"));
    }

    /**
     * @verifies return mention where id_ment is found
     * @see MyDaosMention#getMention(int)
     */
    public void testGetMention_shouldReturnMentionWhereId_mentIsFound() throws Exception {

        Mention ment = null;
        ment = MyDaosMention.getMention(1);
        assertThat(ment.getNp_conj_fr() , equalTo("sihem"));
    }
}
