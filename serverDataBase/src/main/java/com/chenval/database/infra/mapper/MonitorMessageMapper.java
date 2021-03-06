package com.chenval.database.infra.mapper;

import java.util.List;

import com.chenval.tool.domain.entity.ServerMonitorData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenval 2021/3/22
 */
@Mapper
public interface MonitorMessageMapper {
    /**
     * 存储监控信息
     * @param serverMonitorData 存储实体
     * @return 成功数量
     */
    int saveMonitorMessageAllServerNow(@Param("datas") List<ServerMonitorData> serverMonitorData);

    /**
     * 存储监控信息
     * @param ip 存储实体
     * @return 成功数量
     */
    List<ServerMonitorData> selectMonitorMessageByIp(String ip);
}