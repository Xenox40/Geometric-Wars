package com.geometric.wars.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.geometric.wars.utils.Direction2D;

public class MoveRequest {
    public int cubeId;
    public Direction2D direction2D;

    /**
     * has to have empty constructor for sending
     */
    public MoveRequest(){}

    public MoveRequest(int cubeId, Direction2D direction2D) {
        this.cubeId = cubeId;
        this.direction2D = direction2D;
    }

    public static void register(EndPoint endPoint){
        Kryo kryo = endPoint.getKryo();
        kryo.register(Direction2D.class);
        kryo.register(MoveRequest.class);
    }

}
