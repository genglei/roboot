package com.hehe.bean;

/* loaded from: classes.dex */
public class RobotInfo {
    private String command;
    private String error_message;
    private ResultsBean results;
    private String status;
    private String type;
    private String uuid;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String str) {
        this.error_message = str;
    }

    public ResultsBean getResults() {
        return this.results;
    }

    public void setResults(ResultsBean resultsBean) {
        this.results = resultsBean;
    }

    /* loaded from: classes.dex */
    public static class ResultsBean {
        private String product_id;

        public String getProduct_id() {
            return this.product_id;
        }

        public void setProduct_id(String str) {
            this.product_id = str;
        }

        public String toString() {
            return "ResultsBean{product_id='" + this.product_id + "'}";
        }
    }

    public String toString() {
        return "RobotInfo{type='" + this.type + "', command='" + this.command + "', uuid='" + this.uuid + "', status='" + this.status + "', error_message='" + this.error_message + "', results=" + this.results + '}';
    }
}
