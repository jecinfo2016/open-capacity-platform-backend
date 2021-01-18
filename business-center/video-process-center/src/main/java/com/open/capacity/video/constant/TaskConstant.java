package com.open.capacity.video.constant;

/**
 * 任务常量
 *
 * @author donglh
 * @date 2020/7/14
 */
public class TaskConstant {
    /**
     * 任务状态 采集
     */
    public static final String TASK_STATUS_COLLECTOR = "collector";
    /**
     * 任务状态 处理
     */
    public static final String TASK_STATUS_PROCESS = "process";
    /**
     * 任务状态 结束
     */
    public static final String TASK_STATUS_FINISHED = "finished";
    /**
     * 任务状态 失败
     */
    public static final String TASK_STATUS_FAIL = "fail";
    /**
     * 任务类型 视频数据
     */
    public static final String TASK_TYPE_VIDEO = "video";
    /**
     * 任务类型 摄像头数据
     */
    public static final String TASK_TYPE_CAMERA = "camera";

    private TaskConstant() {

    }
}
