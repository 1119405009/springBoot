package com.felix.websocket.computerInfo.controller;

import cn.hutool.core.lang.Dict;

import com.felix.websocket.computerInfo.model.Server;
import com.felix.websocket.computerInfo.payload.ServerVO;
import com.felix.websocket.computerInfo.util.ServerUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping
    public Dict serverInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(serverVO);
    }

}
