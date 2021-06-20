package com.chenval.database.infra.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.chenval.tool.domain.entity.ServerMessage;
import com.chenval.database.domain.repository.ServerMessageRepository;
import com.chenval.database.infra.mapper.ServerMapper;
import com.chenval.tool.constant.ConstantCode;
import com.chenval.tool.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author chenval 2021/3/22
 */
@Repository
public class ServerMessageRepositoryImpl implements ServerMessageRepository {

    @Autowired
    ServerMapper serverMapper;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public List<ServerMessage> getAllServer() {
        return serverMapper.getAllServer();
    }

    @Override
    public ServerMessage getOneServer(String ip) {
        return serverMapper.getOneServer(ip);
    }

    @Override
    public int updateServerMessage(ServerMessage serverMessage) {
        int res = serverMapper.updateServerMessage(serverMessage);
//        StaticVar.flag = 0;
        redisUtils.delete(serverMessage.getIp());
        redisUtils.set(serverMessage.getIp(), JSONObject.toJSONString(serverMessage));
        return res;
    }

    @Override
    public int deleteServer(String ip) {
//        StaticVar.flag = 0;
        redisUtils.delete(ip);
        redisUtils.deleteSetMember(ConstantCode.IP_LIST,ip);
        return serverMapper.deleteServer(ip);
    }

    @Override
    public int addServer(List<ServerMessage> serverMessageList) {
        Set<String> result = new HashSet<>();
        for (ServerMessage temp : serverMessageList) {
            result.add(temp.getIp());
            redisUtils.set(temp.getIp(), JSONObject.toJSONString(temp));
        }
        redisUtils.addSetMember(ConstantCode.IP_LIST,result.toArray(new String[result.size()]));
        return serverMapper.addServer(serverMessageList);
    }
}
