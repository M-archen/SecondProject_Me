package com.green.hoteldog.hotel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Schema(name = "유저별 호텔 리뷰")
public class HotelReviewVo {
    private int hotelPk;
    private String nickName;
    private String userPic;
    private String comment;
    private String updatedAt;
    private int score;
    private List<String> pics;
    @Schema(name = "작성한 리뷰의 좋아요 갯수")
    private int reviewFavCount;
}
