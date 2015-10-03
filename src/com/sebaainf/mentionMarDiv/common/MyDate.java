package com.sebaainf.mentionMarDiv.common;

import java.sql.Date;

public class MyDate extends Date {

    private Date value;

    /**
     * Constructs a <code>Date</code> object using the given milliseconds
     * time value.  If the given milliseconds value contains time
     * information, the driver will set the time components to the
     * time in the default time zone (the time zone of the Java virtual
     * machine running the application) that corresponds to zero GMT.
     *
     * @param date milliseconds since January 1, 1970, 00:00:00 GMT not
     *             to exceed the milliseconds representation for the year 8099.
     *             A negative number indicates the number of milliseconds
     *             before January 1, 1970, 00:00:00 GMT.
     */
    public MyDate(long date) {

        super(date);
        this.value = new Date(this.getTime());
    }

    public static MyDate create(Date date_naiss) {

        MyDate date = new MyDate(date_naiss.getTime());
        date.setValue(date_naiss);
        return date;
    }

    public Date getValue() {

        return value;
    }

    public void setValue(Date value) {

        this.value = value;
    }


}
