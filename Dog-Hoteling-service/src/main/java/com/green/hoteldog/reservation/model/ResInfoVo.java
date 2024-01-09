package com.green.hoteldog.reservation.model;

import lombok.Data;

import java.util.List;

@Data
public class ResInfoVo {

    private int resPk;
    private String hotelNm;
    private String hotelCallnum;
    private String fromDate;
    private String toDate;
    private int cancle;

    private List<ResDogInfoVo> resDogInfoVoList;
}
