package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.ResDogInfoVo;
import com.green.hoteldog.reservation.model.ResInfoDto;
import com.green.hoteldog.reservation.model.ResInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ResInfoVo> getUserReservation(ResInfoDto dto);
    List<ResDogInfoVo> getDogInfoReservation(List<Integer> resPk);
}
