package com.hehe.bean;

/* loaded from: classes.dex */
public class GetMapResponse extends RobotResponseBean {
    public ResultBean results;

    /* loaded from: classes.dex */
    public static class ResultBean {
        public int floor;
        public String hotel_id;
        public String map_name;

        public String toString() {
            return "{hotel_id='" + this.hotel_id + "', map_name='" + this.map_name + "', floor='" + this.floor + "'}";
        }
    }

    @Override // com.higgs.deliveryrobot.bean.RobotResponseBean
    public String toString() {
        return "RobotStatusResponse{command='" + this.command + "', error_message='" + this.error_message + "', status='" + this.status + "', type='" + this.type + "', uuid='" + this.uuid + "'results=" + this.results + '}';
    }
}
