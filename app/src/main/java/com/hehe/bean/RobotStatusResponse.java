package com.hehe.bean;

/* loaded from: classes.dex */
public class RobotStatusResponse extends RobotResponseBean {
    public ResultBean results;

    /* loaded from: classes.dex */
    public static class ResultBean {
        public boolean charge_state;
        public int current_floor;
        public CurrentPoseBean current_pose;
        public boolean estop_state;
        public boolean hard_estop_state;
        public int move_retry_times;
        public String move_status;
        public String move_target;
        public int power_percent;
        public boolean soft_estop_state;

        /* loaded from: classes.dex */
        public static class CurrentPoseBean {
            public float theta;
            public float x;
            public float y;

            public String toString() {
                return "{x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + '}';
            }
        }

        public String toString() {
            return "{move_target='" + this.move_target + "', move_status='" + this.move_status + "', move_retry_times='" + this.move_retry_times + "', charge_state='" + this.charge_state + "', soft_estop_state='" + this.soft_estop_state + "', hard_estop_state='" + this.hard_estop_state + "', estop_state='" + this.estop_state + "', power_percent='" + this.power_percent + "', current_pose='" + this.current_pose + "', current_floor='" + this.current_floor + "'}";
        }
    }

    @Override // com.higgs.deliveryrobot.bean.RobotResponseBean
    public String toString() {
        return "RobotStatusResponse{command='" + this.command + "', error_message='" + this.error_message + "', status='" + this.status + "', type='" + this.type + "', uuid='" + this.uuid + "'results=" + this.results + '}';
    }
}
