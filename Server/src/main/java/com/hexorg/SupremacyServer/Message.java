package com.hexorg.SupremacyServer;

public class Message {
    public enum MessageType {NEW_TURN, GRAIN_BOUGHT}
    public MessageType type;
    public int intData;
}
