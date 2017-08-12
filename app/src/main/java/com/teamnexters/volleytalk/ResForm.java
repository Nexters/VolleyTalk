package com.teamnexters.volleytalk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2017. 8. 13..
 */

public class ResForm {
    private String status;
    private ResData resData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResData getResData() {
        return resData;
    }

    public void setResData(ResData resData) {
        this.resData = resData;
    }

    public class ResData<T> {
        private int start;
        private int display;
        private List<T> items;

        public ResData(int start, int display, List<T> items) {
            this.start = start;
            this.display = display;
            this.items = items;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }
    }
}
