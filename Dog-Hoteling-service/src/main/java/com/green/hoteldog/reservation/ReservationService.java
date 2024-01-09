package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.ResDogInfoVo;
import com.green.hoteldog.reservation.model.ResInfoDto;
import com.green.hoteldog.reservation.model.ResInfoProcDto;
import com.green.hoteldog.reservation.model.ResInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper mapper;
    public List<ResInfoVo> getUserReservation(ResInfoDto dto){
        /*
        {[
            resPk : int
            , hotelNm : String
            , hotelCallnum : String
            , roadAddress : String
            , fromDate : String
            , toDate : String
            , cancle : int
            , dogInfo : [
                    resDogPk : int
                    , sizePk : int
                    , age : int
                    , information : String
                    ]
        ]}
         */
        //1.유저 예약 pk 가져옴.
        //2. 그걸 바탕으로 예약pk 에 맞는 개새끼 정보를 매핑.
        List<ResInfoVo> resInfoVos=mapper.getUserReservation(dto);

        Set<Integer> getRestPk= resInfoVos.stream()
                .map(ResInfoVo::getResPk)
                .collect(Collectors.toCollection(TreeSet::new));

        int userPk=dto.getUserPk();

        //select 여러번 안하는 방법 찾기.
        for (ResInfoVo res : resInfoVos) {
            List<ResDogInfoVo> resDogInfo=mapper.getDogInfoReservation(userPk,res.getResPk());
            //개새끼 수 있는지 없는지 체크.
            res.setResDogInfoVoList(resDogInfo);
        }


        return resInfoVos;
    }
}
