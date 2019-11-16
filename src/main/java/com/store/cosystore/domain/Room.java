package com.store.cosystore.domain;

public enum Room {
    lounge("Гостиная"),
    bedroom("Спальня"),
    kitchen("Кухня"),
    bathroom("Ванная"),
    cabinet("Кабинет"),
    nursery("Детская"),
    diningroom("Столовая"),
    hallway("Прихожая");

    private final String name;

    Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
