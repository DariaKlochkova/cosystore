package com.store.cosystore.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    NEW("В обработке", 0),
    COMPLETING("Комплектуется", 1),
    TRANSIT("В пути", 2),
    DELIVERED("Доставлено", 3),
    COMPLETED("Выполнен", 4),
    CANCELED("Отменён", 5),
    DENIED("Отказано", 6);

    private final String name;
    private final int code;

    Status(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Status getByCode(int code){
        for (Status status : Status.values()){
            if(status.code == code)
                return status;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}
