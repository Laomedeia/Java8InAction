package juejin.netty.netty.protocol;

public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;

    Byte MESSAGE_TO_USER_REQUEST = 5;
    Byte MESSAGE_FROM_USER_RESPONSE = 6;

}
