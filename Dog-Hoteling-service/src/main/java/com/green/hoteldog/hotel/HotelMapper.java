package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.HotelInfoVo;
import com.green.hoteldog.hotel.model.HotelReviewDto;
import com.green.hoteldog.hotel.model.HotelReviewVo;
import com.green.hoteldog.user.model.UserHotelFavDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelReviewDto> getUidByReview(int hotelPk);
    HotelReviewVo getHotelReview(HotelReviewDto dto);
    HotelInfoVo getHotelDetail(int hotelPk);
    List<String> getReviewPics(HotelReviewDto dto);
    int delHotelBookMark(UserHotelFavDto dto);
    int insHotelBookMark(UserHotelFavDto dto);
}
