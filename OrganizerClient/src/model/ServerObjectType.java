package model;

import java.util.HashMap;

/**
 * Representacio de tots els tipus de comunicacio entre client i servidor
 */
public enum ServerObjectType {

    REGISTER(1), LOGIN(2), GET_PROJECT(3), SET_PROJECT(4), SET_CATEGORY(5), DELETE_CATEGORY(6), SET_TASK(7),
    DELETE_TASK(8), INVITE_USER(9), DELETE_USER(10), EXIT_PROJECT(11), LOGOUT(12), SET_TAG(13), DELETE_TAG(14),
    SET_MEMBER(15), DELETE_MEMBER(16), AUTH(17), SWAP_CATEGORY(18), SWAP_TASK(19), DELETE_PROJECT(20),
    TASK_DONE(21), TASK_NOT_DONE(22), GET_PROJECT_LIST(23), JOIN_PROJECT(24), CHANGE_TASK_CATEGORY(25), ADD_PROJECT(26),
    EDIT_TAG(27);

    private int value;
    private static HashMap map = new HashMap<>();

    /**
     * Constructor
     * @param value Valor
     */
    ServerObjectType(int value) {
        this.value = value;
    }

    static {
        for (ServerObjectType serverObjectType : ServerObjectType.values()) {
            map.put(serverObjectType.value, serverObjectType);
        }
    }

    /**
     * Getter del valor a partir d'un valor enter
     * @param i Enter
     * @return Valor de l'Enum
     */
    public static ServerObjectType valueOf(int i) {
        return (ServerObjectType) map.get(i);
    }

    /**
     * Getter del valor
     * @return Valor enter
     */
    public int getValue() {
        return value;
    }

}
