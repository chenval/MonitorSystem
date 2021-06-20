package com.chenval.database.app.job;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.chenval.tool.domain.entity.ServerMessage;
import com.chenval.tool.domain.entity.ServerMonitorData;
import com.chenval.tool.domain.entity.WarningMessage;
import com.chenval.database.domain.repository.MonitorMessageRepository;
import com.chenval.database.domain.repository.ServerMessageRepository;
import com.chenval.database.domain.repository.WarningMessageRepository;

import com.chenval.database.infra.feign.SnmpFeign;
import com.chenval.tool.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author yuanyang.chen@hand-china.com 2021-03-23
 */
@Configuration
@EnableScheduling
public class SaveMonitorMessage {
    @Autowired
    MonitorMessageRepository monitorMessageRepository;
    @Autowired
    ServerMessageRepository serverMessageRepository;
    @Autowired
    WarningMessageRepository warningMessageRepository;
    @Autowired
    SnmpFeign snmpFeign;
    @Autowired
    RedisUtils redisUtils;

    static int count = 0;
    @Scheduled(cron = "0/5 * * * * ? ")
    private void configureTasks() {

        count++;
        List<ServerMonitorData> result;
        result = snmpFeign.getAllServerMessageNow();
        for (ServerMonitorData serverMonitorData : result) {
            judgeWarning(serverMonitorData, JSONObject.parseObject(redisUtils.get(serverMonitorData.getIp()),ServerMessage.class));
        }
        if (count == 4){
            monitorMessageRepository.insertAllData(result);
            count = 0;
        }

    }

    public void judgeWarning(ServerMonitorData serverMonitorData, ServerMessage serverMessage) {
        if (serverMonitorData.getCpuUtilization() >= serverMessage.getCpuThreshold() || serverMonitorData.getMemUtilization() >= serverMessage.getMemThreshold()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (serverMonitorData.getMemUtilization() == -1) {
                stringBuilder.append("服务器连接异常");
            } else {
                if (serverMonitorData.getCpuUtilization() >= serverMessage.getCpuThreshold()) {
                    stringBuilder.append("cpu占用超过阈值。");
                }
                if (serverMonitorData.getMemUtilization() >= serverMessage.getMemThreshold()) {
                    stringBuilder.append("内存占用超过阈值");
                }
            }

            WarningMessage warningMessage = WarningMessage.builder().message(stringBuilder.toString()).ip(serverMessage.getIp()).build();
            List<WarningMessage> res = warningMessageRepository.selectWarningMessageList(1, warningMessage.getIp());
            if (res.size() == 0 || res.get(0).isHandle()) {
                warningMessageRepository.insertWarningMessage(warningMessage);
            } else {
                warningMessageRepository.updateWarningMessage(warningMessage);
            }
        }
    }
}
