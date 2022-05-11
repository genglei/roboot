package com.hehe.soketjar.common_bean.RobotStatus;

/* loaded from: classes.dex */
public class StatusResultBean {
    private boolean charge_state;
    private int current_floor;
    private CurrentPoseBean current_pose;
    private String error_code;
    private boolean estop_state;
    private boolean hard_estop_state;
    private int move_retry_times;
    private String move_status;
    private String move_target;
    private int power_percent;
    private boolean soft_estop_state;

    public String getMove_target() {
        return this.move_target;
    }

    public void setMove_target(String str) {
        this.move_target = str;
    }

    public String getMove_status() {
        return this.move_status;
    }

    public void setMove_status(String str) {
        this.move_status = str;
    }

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(String str) {
        this.error_code = str;
    }

    public int getPower_percent() {
        return this.power_percent;
    }

    public void setPower_percent(int i) {
        this.power_percent = i;
    }

    public int getMove_retry_times() {
        return this.move_retry_times;
    }

    public void setMove_retry_times(int i) {
        this.move_retry_times = i;
    }

    public int getCurrent_floor() {
        return this.current_floor;
    }

    public void setSoft_estop_state(boolean z) {
        this.soft_estop_state = z;
    }

    public void setHard_estop_state(boolean z) {
        this.hard_estop_state = z;
    }

    public void setEstop_state(boolean z) {
        this.estop_state = z;
    }

    public void setCurrent_floor(int i) {
        this.current_floor = i;
    }

    public void setCharge_state(boolean z) {
        this.charge_state = z;
    }

    public boolean isSoft_estop_state() {
        return this.soft_estop_state;
    }

    public boolean isHard_estop_state() {
        return this.hard_estop_state;
    }

    public boolean isEstop_state() {
        return this.estop_state;
    }

    public boolean isCharge_state() {
        return this.charge_state;
    }

    public void setCurrent_pose(CurrentPoseBean currentPoseBean) {
        this.current_pose = currentPoseBean;
    }

    public CurrentPoseBean getCurrent_pose() {
        return this.current_pose;
    }

    public String toString() {
        return "{move_target='" + this.move_target + "', move_status='" + this.move_status + "', move_retry_times='" + this.move_retry_times + "', charge_state='" + this.charge_state + "', soft_estop_state='" + this.soft_estop_state + "', hard_estop_state='" + this.hard_estop_state + "', estop_state='" + this.estop_state + "', power_percent='" + this.power_percent + "', current_pose='" + this.current_pose + "', current_floor='" + this.current_floor + "', error_code='" + this.error_code + "'}";
    }
}
