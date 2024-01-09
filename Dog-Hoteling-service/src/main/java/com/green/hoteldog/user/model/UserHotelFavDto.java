package com.green.hoteldog.user.model;
import lombok.Data;

@Data
public class UserHotelFavDto {
    private int userPk;
    private int hotelPk;
    private String createdAt;
}
