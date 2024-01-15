package com.green.hoteldog.hotel;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.model.UserHotelFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper mapper;
    public ResVo toggleHotelBookMark(UserHotelFavDto dto){
        int result=mapper.delHotelBookMark(dto);
        if(result==1){
            return new ResVo(2);
        }
        int result2= mapper.insHotelBookMark(dto);
        return new ResVo(result2);
    }
    public HotelMainPage getHotelDetail(HotelMainPageDto dto){
        //메인페이지 객체 생성
        HotelMainPage hotelMainPage=new HotelMainPage();
        // 호텔 기본적인 정보.
        HotelInfoVo hotelInfoVo=mapper.getHotelDetail(dto.getHotelPk());
        //호텔 사진 넣어줌.
        hotelInfoVo.setPics(mapper.getHotelPics(dto.getHotelPk()));
        HotelOption option=mapper.hotelOptionInfo(dto.getHotelPk());
        hotelInfoVo.setHotelOption(option);

        //좋아요 많은 갯수대로 호텔에 적힌 리뷰 최대 3개까지 가져옴.
        List<HotelReviewVo> reviewThree=mapper.getHotelReviewThree(dto.getHotelPk());
        if(reviewThree.size()==0){
            // 리뷰 없음.
        }
        HotelReviewVo vo;
        List pics;



        int countreview=mapper.isMoreHotelReview(dto.getHotelPk());
        if(countreview>3){
            hotelInfoVo.setIsMoreReview(1);//리뷰 더있니 => 0 to 1
        }

        hotelInfoVo.setReviewList(reviewThree);
        hotelMainPage.setHotelInfoVo(hotelInfoVo);

        LocalDate today=LocalDate.now();
        List<LocalDate> twoMonthDate=new ArrayList<>();
        //요번달 날짜,
        for (int i = 1; i < today.lengthOfMonth() ; i++) {
            LocalDate localDate=LocalDate.now().plusDays(i- today.getDayOfMonth());
            twoMonthDate.add(localDate);
        }
        //다음달 날짜,
        for (int i = 0; i <= today.plusMonths(1).lengthOfMonth(); i++) {
            LocalDate localDate=LocalDate.now().plusMonths(1).plusDays(i- today.getDayOfMonth());
            twoMonthDate.add(localDate);
        }
        //twoMonthDate : 두달동안 날짜 리스트(LocalDate 타입)
        //twoMonth : String 타입 리스트.
        List<String> twoMonth=twoMonthDate
                .stream()
                .map(localDate -> localDate.toString())
                .collect(Collectors.toList());

        //호텔 예약정보 최근 두달 다가져오기.
        //줘야할 것 호텔 pk,시작,끝날짜.
        //해야할 것 : 날짜별로 예약정보 받아와서 예약된 강아지 수 카운팅, 비교


        //시작날짜 : 요번달 첫날
        String startDate=twoMonthDate.get(0).toString();
        //끝나는날짜 : 다다음달 첫날 (BETWEEN 쿼리 사용 위함)
        String endDate=twoMonthDate.get(twoMonthDate.size()).plusDays(1).toString();
        // 추가사항 1월 10일
        // 2달동안의 날짜 보내는 객체에
        // 호텔의 방 타입,방 타입에 대한 예약 여부
        // 방 타입은 추후 추가
        //일정 기간동안의(2달간) 호텔의 예약정보 가져옴.
        List<HotelResInfoVo> hotelResInfoVos=mapper.getHotelResInfo(dto.getHotelPk(),startDate,endDate);
        for (HotelResInfoVo vo:hotelResInfoVos) {
            int dogCnt= mapper.getDogResInfo(vo.getResPk());

        }
        List<HotelResAbleVo> twoMonthHotelInfo=new ArrayList<>();



        /*
        예약 날짜 보내기...
         */
        // 예약 확정될까지 건들지않기.

        return null;
    }
}
