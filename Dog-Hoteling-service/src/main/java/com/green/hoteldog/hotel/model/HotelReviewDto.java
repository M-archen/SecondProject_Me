package com.green.hoteldog.hotel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HotelReviewDto {
    private int uid;
    private int hotelPk;
}
