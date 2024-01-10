package com.green.hoteldog.hotel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "호텔 메인페이지 총집합",description = "호텔 정보  + 리뷰" +
        "예약한 날짜<br>" +
        "날짜별 예약 가능여부(1마리라도 가능하면 resAble = 1)<br>" +
        "날짜별 예약 가능한 강아지 마릿수")
public class HotelMainPage {
    @Schema(name = "호텔 정보 + 리뷰")
    private HotelInfoVo hotelInfoVo;
    @Schema(name = "최근 2달간의 날짜별 예약 가능여부," +
            "<br>예약가능한 강아지 마릿수")
    private List<HotelResAbleVo> monthByRes;
}
