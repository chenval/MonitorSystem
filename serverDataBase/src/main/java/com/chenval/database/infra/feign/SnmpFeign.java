package com.chenval.database.infra.feign;


import com.chenval.tool.domain.entity.ServerMonitorData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("snmpApplication")
@RequestMapping("/sysMonitor")
public interface SnmpFeign {

    @GetMapping("/getAllServerMessageNow")
     List<ServerMonitorData> getAllServerMessageNow();

    @GetMapping("/getServerMessageNow")
    ServerMonitorData getServerMessageNow(@RequestParam("ip") String ip);
}
