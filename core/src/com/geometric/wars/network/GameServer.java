package com.geometric.wars.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.geometric.wars.utils.Values;

import java.io.IOException;

public class GameServer {
    private Server server;
    public GameServer() {
        try {
            server = new Server();
            MoveRequest.register(server);
            server.start();
            server.bind(Values.tcpPort, Values.udpPort);

            server.addListener(new Listener() {
                public void received(Connection connection, Object object) {
                    if (object instanceof MoveRequest) {
                        MoveRequest request = (MoveRequest) object;
                        System.out.println("move requested by" + request.cubeId + " in " + request.direction2D);
                    }
                }
            });
        }catch (IOException e){
            throw new NetworkException("server binding ports failed");
        }
    }

}
