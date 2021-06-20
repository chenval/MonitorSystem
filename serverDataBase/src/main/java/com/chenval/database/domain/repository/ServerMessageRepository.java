package com.chenval.database.domain.repository;

import com.chenval.tool.domain.entity.ServerMessage;

import java.util.List;

/**
 * @author chenval 2021/3/22
 */

public interface ServerMessageRepository {
    /**
     * 获得所有服务器
     * @return
     */
    List<ServerMessage> getAllServer();

    /**
     * 获得制定服务器
     * @param ip IP地址
     * @return 该服务器的基本信息
     */
    ServerMessage getOneServer(String ip);

    /**
     * 更新服务器信息
     */
    int updateServerMessage(ServerMessage serverMessage);

    /**
     *
     */
    int deleteServer(String ip);

    /**
     * 添加服务器信息
     * @param serverMessage 插入数据
     * @return 成功数量
     */
    int addServer(List<ServerMessage> serverMessage);

}
