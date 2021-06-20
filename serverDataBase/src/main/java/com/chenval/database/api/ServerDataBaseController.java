package com.chenval.database.api;


import com.chenval.database.infra.feign.SnmpFeign;
import com.chenval.tool.domain.entity.ServerMessage;
import com.chenval.tool.domain.entity.ServerMonitorData;
import com.chenval.tool.domain.entity.WarningMessage;
import com.chenval.database.domain.repository.MonitorMessageRepository;
import com.chenval.database.domain.repository.ServerMessageRepository;
import com.chenval.database.domain.repository.WarningMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/database")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServerDataBaseController {


    @Autowired
    ServerMessageRepository serverMessageRepository;
    @Autowired
    WarningMessageRepository warningMessageRepository;
    @Autowired
    MonitorMessageRepository monitorMessageRepository;
    @Autowired
    SnmpFeign snmpFeign;
    @GetMapping("/getIpList")
    public Set<String> getIpList() {
        List<ServerMessage> list = serverMessageRepository.getAllServer();
        Set<String> result = new HashSet<>();
        for (ServerMessage temp : list) {
            result.add(temp.getIp());
        }
        return result;
    }

    @GetMapping("/getOneServer")
    public ServerMessage getOneServer(String ip) {
        return serverMessageRepository.getOneServer(ip);
    }

    @GetMapping("/getAllServer")
    public List<ServerMessage> getAllServer() {
        return serverMessageRepository.getAllServer();
    }

    @PostMapping("addServer")
    public int addServer(@RequestBody List<ServerMessage> serverMessage) {
        return serverMessageRepository.addServer(serverMessage);
    }

    @GetMapping("/getWarningMessage")
    public List<WarningMessage> getWarn() {
        return warningMessageRepository.getAllNoHandleMessage();
    }

    @GetMapping("/getServerMessageByIp")
    public List<ServerMonitorData> getMessageInPastHalfHour(@RequestParam String ip) {
        return monitorMessageRepository.getMonitorMessage(ip);
    }

    @GetMapping("/deleteServerMessage")
    public int delete(@RequestParam String ip) {
        return serverMessageRepository.deleteServer(ip);
    }

    @PostMapping("/updateServerMessage")
    public int updateServerMessage(@RequestBody ServerMessage serverMessage) {
        return serverMessageRepository.updateServerMessage(serverMessage);
    }

    @GetMapping("/testFeign")
    public Object test() {
        return snmpFeign.getAllServerMessageNow();
    }
}
