package com.chenval.database.domain.repository;

import com.chenval.tool.domain.entity.WarningMessage;

import java.util.List;

/**
 * @author chenval 2021-03-26
 */
public interface WarningMessageRepository {
    int updateWarningMessage(WarningMessage warningMessage);
    List<WarningMessage> selectWarningMessageList(int num,String ip);
    int insertWarningMessage(WarningMessage warningMessage);
    List<WarningMessage> getAllNoHandleMessage();

}
