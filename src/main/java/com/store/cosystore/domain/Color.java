package com.store.cosystore.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Color {
    BLACK("Чёрный", "#000000"),
    WHITE("Белый", "#ffffff"),
    BROWN("Коричневый", "#814820"),
    BEIGE("Бежевый", "#f4f3d7"),
    GRAY("Серый", "#959595"),
    BLUE("Синий", "#0058a3"),
    GREEN("Зелёный", "#3b7d22"),
    PINK("Розовый", "#ffb8c3"),
    YELLOW("Жёлтый", "#ffdb00"),
    RED("Красный", "#ff0000"),
    ORANGE("Оранжевый", "#ff9a02"),
    TURQUOISE("Бирюзовый", "#77ddd0"),
    LILAC("Сиреневый", "#a96ecc"),
    LIGHTBLUE("Голубой", "#9ce4ff"),
    MULTICOLOR("Разноцветный", "");

    private final String name;
    private final String code;

    public static Color getByName(String name){
        for (Color color : Color.values()){
            if(color.name.equals(name))
                return color;
        }
        return null;
    }

    Color(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
