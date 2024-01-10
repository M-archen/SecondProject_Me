package com.green.hoteldog.hotel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "호텔 정보 + 리뷰")
public class HotelInfoVo {
    @Schema(name = "개새끼 최대 수용가능 마릿수")
    private int dogMax;
    @Schema(name = "호텔 이름")
    private String hotelNm;
    @Schema(name = "호텔 사진")
    private List<String> pics;
    @Schema(name = "사장님의 정성어린 글")
    private String contents;
    @Schema(name = "사장님의 비밀스런 사업자번호")
    private String buisinessNum;
    @Schema(name = "호텔 전화번호")
    private String hotelCallnum;
    @Schema(name = "호텔 도로명주소")
    private String roadAddress;
    @Schema(name = "유저들이 정성스럽게 쓴 호텔 리뷰")
    private List<HotelReviewVo> reviewList;

}


