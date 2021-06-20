package com.chenval.database.infra.repository;

import com.chenval.tool.domain.entity.ServerMonitorData;
import com.chenval.database.domain.repository.MonitorMessageRepository;
import com.chenval.database.infra.mapper.MonitorMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/17 10:08
 */
@Repository
public class MonitorMessageRepositoryImp implements MonitorMessageRepository {

    @Autowired
    MonitorMessageMapper monitorMessageMapper;
    @Override
    public ServerMonitorData queryDataById(String id) {
        return null;
    }

    @Override
    public List<ServerMonitorData> queryAllData() {
        return null;
    }

    @Override
    public void
    insertData( ServerMonitorData serverMonitorData) {
       return ;
    }

    @Override
    public int insertAllData(List<ServerMonitorData> list) {
        if(list.isEmpty()){
            return 0;
        }
        return monitorMessageMapper.saveMonitorMessageAllServerNow(list);
    }

    @Override
    public List<ServerMonitorData> getMonitorMessage(String ip) {
        return monitorMessageMapper.selectMonitorMessageByIp(ip);
    }

}
