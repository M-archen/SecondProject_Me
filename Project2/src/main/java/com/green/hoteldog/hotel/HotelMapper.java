package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.model.UserHotelFavDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelResInfoVo> getHotelResInfo(int hotelPk, String startDate, String endDate);
    List<HotelReviewVo> getHotelReviewThree(int hotelPk);
    HotelInfoVo getHotelDetail(int hotelPk);
    Integer getDogResInfo(int resPk);
    List<String> getHotelPics(int hotelPk);
    HotelOption hotelOptionInfo(int hotelPk);
    List<String> getReviewPics(HotelReviewDto dto);
    int delHotelBookMark(UserHotelFavDto dto);
    int insHotelBookMark(UserHotelFavDto dto);
    Integer isMoreHotelReview(int hotelPk);

    //

    List<Integer> getHotelResPk(int hotelPk);
    List<Integer> getUserByFavCnt();
}
