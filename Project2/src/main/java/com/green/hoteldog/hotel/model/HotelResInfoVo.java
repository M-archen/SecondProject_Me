package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelResInfoVo {
    private int resPk;
    private int userPk;
    private String fromDate;
    private String toDate;
    private int dogCnt;
    private int resStatus;
}
