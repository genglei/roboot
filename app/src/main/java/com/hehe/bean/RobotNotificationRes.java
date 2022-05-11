package com.hehe.bean;

/* loaded from: classes.dex */
public class RobotNotificationRes extends RobotResponseBean {
    public String code;
    private DataBean data;
    public String description;
    public String level;

    @Override // com.higgs.deliveryrobot.bean.RobotResponseBean
    public String toString() {
        return "RobotNotification{command='" + this.command + "', error_message='" + this.error_message + "', code='" + this.code + "', description='" + this.description + "', status='" + this.status + "', level='" + this.level + "', type='" + this.type + "', uuid='" + this.uuid + "'}";
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    /* loaded from: classes.dex */
    public static class DataBean {
        private double distance;
        private String target;

        public double getDistance() {
            return this.distance;
        }

        public void setDistance(double d) {
            this.distance = d;
        }

        public String getTarget() {
            return this.target;
        }

        public void setTarget(String str) {
            this.target = str;
        }
    }
}
