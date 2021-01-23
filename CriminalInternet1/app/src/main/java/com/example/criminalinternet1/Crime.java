package com.example.criminalinternet1;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String Title) {
        this.mTitle = Title;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date Date) {
        this.mDate = Date;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean Solved) {
        this.mSolved = Solved;
    }

}
