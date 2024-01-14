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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        //호텔 리뷰 pk, 유저 pk, 호텔 pk 받음.(최대 3개)
        List<HotelReviewDto> getInfoByReview=mapper.getUidByReview(dto.getHotelPk());

        // reviewExist =0 리뷰없음.
        int reviewExist=getInfoByReview.size();
        //리뷰를 받기위한 리스트 만들어줌.
        List<HotelReviewVo> reviewVos=new ArrayList<>();

        for (HotelReviewDto reviewDto : getInfoByReview) {
            //호텔 리뷰 객체 생성 후 호텔 리뷰 넣어줌
            HotelReviewVo hotelReviewVo = mapper.getHotelReview(reviewDto);
            //방금 받은 객체에 사진 삽입.
            hotelReviewVo.setPics(mapper.getReviewPics(reviewDto));
            //리뷰의 좋아요 갯수 삽입.
            hotelReviewVo.setReviewFavCount(mapper.getReviewFavCnt(reviewDto));
            // 새로 만든 리뷰 리스트에 리뷰 삽입.
            reviewVos.add(hotelReviewVo);
        }
        //호텔 정보에 리뷰 넣어줌
        hotelInfoVo.setReviewList(reviewVos);
        //최종적으로 보여줄 객체에 호텔 정보 삽입.
        hotelMainPage.setHotelInfoVo(hotelInfoVo);
        // 방 정보 넣어줘야함. 1월 10일 회의.
        //정보 끝.--------
        LocalDate today=LocalDate.now();
        //for 문 select 안쓰는 방향으로 ...

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
