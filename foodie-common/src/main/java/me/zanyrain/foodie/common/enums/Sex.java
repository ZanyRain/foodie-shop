package me.zanyrain.foodie.common.enums;

public enum Sex {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    public Integer type;
    public String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
