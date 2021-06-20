package com.chenval.tool.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenval 2021-03-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarningMessage {
    /**
     * ip地址
     */
    String ip;
    /**
     * 告警信息
     */
    String message;
    /**
     * 发生时间时间
     * */
    private String createDate;
    /**
     * 最后一次预警时间
     */
    private String lastUpdateTime;
    /**
     * 预警次数
     */
    private int warningCount;
    /**
     * 是否处理
     */
    private boolean isHandle;
}
