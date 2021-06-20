package com.chenval.database.infra.repository;

import java.util.List;

import com.chenval.tool.domain.entity.WarningMessage;
import com.chenval.database.domain.repository.WarningMessageRepository;
import com.chenval.database.infra.mapper.WarningMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author chenval 2021-03-26
 */
@Repository
public class WarningMessageRepositoryImpl implements WarningMessageRepository {
    @Autowired
    WarningMessageMapper warningMessageMapper;

    @Override
    public int updateWarningMessage(WarningMessage warningMessage) {
        return warningMessageMapper.updateWarningMessage(warningMessage);
    }

    @Override
    public List<WarningMessage> selectWarningMessageList(int num,String ip) {
        return warningMessageMapper.selectWarningMessageList(num, ip);
    }

    @Override
    public int insertWarningMessage(WarningMessage warningMessage) {
        return warningMessageMapper.insertWarningMessage(warningMessage);
    }

    @Override
    public List<WarningMessage> getAllNoHandleMessage() {
        return warningMessageMapper.getAllNoHandleMessage();
    }
}
