package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRoomResInfoVo {
    @NotNull
    @JsonProperty(value = "hotel_room_nm")
    private String hotelRoomNm;

    @NotNull
    @JsonProperty(value = "hotel_room_left")
    private int hotelRoomLeft;

    @NotNull
    @JsonProperty(value = "hotel_room_cost")
    private String hotelRoomCost;

    @NotNull
    private int maximum;
}
