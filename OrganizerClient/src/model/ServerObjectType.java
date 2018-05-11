package model;

import java.util.HashMap;

public enum ServerObjectType {
    REGISTER(1), LOGIN(2), GET_PROJECT(3), SET_PROJECT(4), SET_CATEGORY(5), DELETE_CATEGORY(6), SET_TASK(7),
    DELETE_TASK(8), ADD_USER(9), DELETE_USER(10), EXIT_PROJECT(11), LOGOUT(12), SET_TAG(13), DELETE_TAG(14),
    SET_ENCARREGAT(15), DELETE_ENCARREGAT(16), AUTH(17), SWAP_CATEGORY(18), SWAP_TASK(19), DELETE_PROJECT(20),
    GET_PROJECT_LIST(21);

    private int value;
    private static HashMap map = new HashMap<>();

    ServerObjectType(int value) {
        this.value = value;
    }

    static {
        for (ServerObjectType serverObjectType : ServerObjectType.values()) {
            map.put(serverObjectType.value, serverObjectType);
        }
    }

    public static ServerObjectType valueOf(int i) {
        return (ServerObjectType) map.get(i);
    }

    public int getValue() {
        return value;
    }
}
