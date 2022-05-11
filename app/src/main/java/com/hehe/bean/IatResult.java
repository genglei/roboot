package com.hehe.bean;

import java.util.List;

/* loaded from: classes.dex */
public class IatResult {
    private int bg;
    private int ed;
    private boolean ls;
    private int sn;
    private List<WsBean> ws;

    public int getSn() {
        return this.sn;
    }

    public void setSn(int i) {
        this.sn = i;
    }

    public boolean isLs() {
        return this.ls;
    }

    public void setLs(boolean z) {
        this.ls = z;
    }

    public int getBg() {
        return this.bg;
    }

    public void setBg(int i) {
        this.bg = i;
    }

    public int getEd() {
        return this.ed;
    }

    public void setEd(int i) {
        this.ed = i;
    }

    public List<WsBean> getWs() {
        return this.ws;
    }

    public void setWs(List<WsBean> list) {
        this.ws = list;
    }

    /* loaded from: classes.dex */
    public static class WsBean {
        private int bg;
        private List<CwBean> cw;

        public int getBg() {
            return this.bg;
        }

        public void setBg(int i) {
            this.bg = i;
        }

        public List<CwBean> getCw() {
            return this.cw;
        }

        public void setCw(List<CwBean> list) {
            this.cw = list;
        }

        /* loaded from: classes.dex */
        public static class CwBean {
            private double sc;
            private String w;

            public double getSc() {
                return this.sc;
            }

            public void setSc(double d) {
                this.sc = d;
            }

            public String getW() {
                return this.w;
            }

            public void setW(String str) {
                this.w = str;
            }
        }
    }
}
