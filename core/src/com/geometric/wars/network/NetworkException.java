package com.geometric.wars.network;

public class NetworkException extends RuntimeException {
    NetworkException() {}
    NetworkException(String msg){
        super(msg);
    }
}
