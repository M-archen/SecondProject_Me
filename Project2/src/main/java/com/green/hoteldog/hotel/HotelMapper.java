package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.HotelInfoVo;
import com.green.hoteldog.hotel.model.HotelResInfoVo;
import com.green.hoteldog.hotel.model.HotelReviewDto;
import com.green.hoteldog.hotel.model.HotelReviewVo;
import com.green.hoteldog.user.model.UserHotelFavDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelResInfoVo> getHotelResInfo(int hotelPk, String startDate, String endDate);
    List<HotelReviewDto> getUidByReview(int hotelPk);
    HotelReviewVo getHotelReview(HotelReviewDto dto);
    HotelInfoVo getHotelDetail(int hotelPk);
    Integer getDogResInfo(int resPk);
    Integer getReviewFavCnt(HotelReviewDto dto);
    List<String> getHotelPics(int hotelPk);
    List<String> getReviewPics(HotelReviewDto dto);
    int delHotelBookMark(UserHotelFavDto dto);
    int insHotelBookMark(UserHotelFavDto dto);


    //

    List<Integer> getHotelResPk(int hotelPk);
    List<Integer> getUserByFavCnt();
}
