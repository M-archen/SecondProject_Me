package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.ResDogInfoVo;
import com.green.hoteldog.reservation.model.ResInfoDto;
import com.green.hoteldog.reservation.model.ResInfoProcDto;
import com.green.hoteldog.reservation.model.ResInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper mapper;
    public List<ResInfoVo> getUserReservation(ResInfoDto dto){
        //유저 예약 정보 가져옴
        List<ResInfoVo> resInfoVos=mapper.getUserReservation(dto);
        List<Integer> resPkList=new ArrayList<>();

        Map<Integer,ResInfoVo> resInfoList=new HashMap<>();
        for (ResInfoVo vo:resInfoVos) {
            resInfoList.put(vo.getResPk(),vo);
            resPkList.add(vo.getResPk());
        }
        List<ResDogInfoVo> resDogInfo=mapper.getDogInfoReservation(resPkList);

        return resInfoVos;
    }
}
