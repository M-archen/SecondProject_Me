package com.green.hoteldog.reservation.model;

import lombok.Data;

@Data
public class ResDogInfoVo {
    private int resPk;
    private int resDogPk;
    private int sizePk;
    private int age;
    private String information;
}
