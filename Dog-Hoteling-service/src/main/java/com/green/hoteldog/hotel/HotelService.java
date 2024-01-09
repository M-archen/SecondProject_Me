package com.green.hoteldog.hotel;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.model.UserHotelFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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
    public HotelMainPage getHotelDetail(int hotelPk){
        //호텔 별점 높은순 3개. ( 랜덤 )
        /*
        구현해야할 것.
        <!-- /*
        상세페이지 출력, 리뷰 셀렉트 시 객체 3중첩 ex)
        vo안에 리뷰리스트 안에 사진 여러장
        (추가사항) 상세페이지에 호텔 당 맥시멈 강아지
        수와 예약 날짜 기준으로 수용하고 있는
        강아지 수를 비교해서 예약 다찬 날짜 보내주어야
        켈린더에 표시함 // 승준


        예약 정보 출력, 취소나 옛날 꺼 상관 없이 모두

        호텔 즐겨찾기
     */
        /*
        해야 할 것. 최근 2달 별 날짜(예약가능여부,가능한 강아지 마릿수) ,
        호텔 최대 수용가능 마릿수.
        ..정보 , 리뷰(3개)
         */

        /*
        int dogMax
        String hotelNm
        List<String> pics
        String contents
        String buisinessNum
        String hotelCallnum
        String roadAddress
        List<HotelReviewVo> reviewList
         */
        //1.리뷰를 제외한 호텔정보 채우기. HotelInfoVo
        //2.HotelInfoVo 에 리뷰 3개 채우기.(HotelReviewVo) {별점순}
        //3.2달 동안의 어쩌구 채우기.
        HotelMainPage hotelMainPage=new HotelMainPage();

        HotelInfoVo hotelInfoVo=mapper.getHotelDetail(hotelPk);
        //  완.
        List<HotelReviewDto> getUidByReview=mapper.getUidByReview(hotelPk);
        // 리뷰가 있는지 갯수 ? MIN=0 , MAX = 3
        int reviewExist=getUidByReview.size();
        // reviewExist =0 리뷰없음.
        List<HotelReviewVo> reviewVos=new ArrayList<>();

        for (HotelReviewDto dto : getUidByReview) {
            HotelReviewVo hotelReviewVo = mapper.getHotelReview(dto);
            hotelReviewVo.setPics(mapper.getReviewPics(dto));

            reviewVos.add(hotelReviewVo);
        }
        hotelInfoVo.setReviewList(reviewVos);
        hotelMainPage.setHotelInfoVo(hotelInfoVo);

        List<HotelResAbleVo> voList=new ArrayList<>();

        Year todayYear= Year.of(LocalDate.now().getYear());
        Month todayMonth=LocalDate.now().getMonth();

        Year nxtYearByMonth=Year.of(LocalDate.now().getYear()+1);
        Month nxtMonth= Month.of(LocalDate.now().getDayOfMonth()+1);
        //for 문 select 안쓰는 방향으로 ...



        /*
        예약 날짜 보내기...
         */
        // 예약 확정될까지 건들지않기.

        return null;
    }
}
