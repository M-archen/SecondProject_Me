package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.util.List;

@Data
public class HotelInfoWithMyDog {
    private HotelInfoVo hotelInfoVo;
    private List<HotelDetailToDogInfo> dogInfos;

}
