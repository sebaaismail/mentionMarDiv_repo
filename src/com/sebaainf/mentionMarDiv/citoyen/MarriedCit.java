package com.sebaainf.mentionMarDiv.citoyen;

import com.sebaainf.mentionMarDiv.common.Mariage;

import java.util.Date;
import java.util.TreeSet;

/**
 * Created by admin on 10/01/2015.
 */
public class MarriedCit extends Citoyen implements IMarried {

    private TreeSet<Mariage> families = new TreeSet<Mariage>();


    public MarriedCit() {

        this.setSit_famil("m");
    }

    @Override
    public TreeSet<Mariage> getFamilies() {

        return this.families;
    }

    @Override
    public void setFamilies(TreeSet<Mariage> families) {

        this.families = families;

    }

    @Override
    public Mariage getFamily(Date date_mar) {

        TreeSet<Mariage> fams = this.getFamilies();
        Mariage fam = null;
        for (Mariage f : fams) {
            if (f.getDate_mar() == date_mar) {
                fam = f;
                break;
            }
        }
        return fam;

        //Family fm = fms.iterator().i;

    }
}
