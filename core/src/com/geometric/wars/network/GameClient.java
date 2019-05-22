package com.geometric.wars.network;

import com.esotericsoftware.kryonet.Client;
import com.geometric.wars.utils.Values;

import java.io.IOException;

public class GameClient {
    public Client client;

    public GameClient()  {
        client = new Client();
        MoveRequest.register(client);
        client.start();

    }

    public String discoverHosts() {
        return client.discoverHost(Values.udpPort,10000).getCanonicalHostName();
    }

    public void connect(String host) {
        try{
            client.connect(10000, host, Values.tcpPort, Values.udpPort);
        }catch (IOException e){
            throw new NetworkException("client connecting exception");
        }
    }

    public void send(MoveRequest moveRequest) {
        client.sendTCP(moveRequest);
    }



}
