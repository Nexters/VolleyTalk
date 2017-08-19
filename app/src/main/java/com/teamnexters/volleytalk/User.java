package com.teamnexters.volleytalk;

import java.io.Serializable;

/**
 * Created by MIN on 2017. 8. 19..
 */

public class User implements Serializable {
    private int seq;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
