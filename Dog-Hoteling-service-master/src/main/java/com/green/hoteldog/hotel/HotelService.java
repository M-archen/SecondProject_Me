package com.green.hoteldog.hotel;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.*;
import com.green.hoteldog.user.models.UserHotelFavDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper mapper;

    List<HotelListSelVo> getHotelList(HotelListSelDto dto){
        // 1. 등록된 반려견 정보와 주소로 사용자화
        // 1-1. 비회원 첫화면 - 최신순
        if(dto.getUserPk() == 0 && dto.getAddressPk() == 0 && dto.getFromDate() == null
            && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getSearch() == null
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0
                && dto.getDogWalking() == 0){
            return mapper.selHotelListToNonMember(dto);
        // 1-2. 회원 첫화면 - 주소, 반려견 강아지 사이즈
        } else if (dto.getUserPk() > 0 && dto.getAddressPk() == 0 && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getSearch() == null
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0
                && dto.getDogWalking() == 0){
            // 1-2-1. 회원 pk로 강아지 사이즈, 주소 pk 셀렉
            HotelListSelProcDto pDto = mapper.selUserInfoToUserPk(dto);
            return mapper.selHotelListToMember(pDto);
        }
        // 2. 호텔 이름 검색 시 리스트
        if(dto.getSearch() != null && dto.getAddressPk() == 0 && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getDogWalking() == 0
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0){
            return mapper.selHotelListToSearch(dto);
        }
        // 3. 필터링 시 리스트
        if((dto.getAddressPk() > 0 || dto.getFromDate() != null || dto.getToDate() != null
                || dto.getDogCount() > 0 || dto.getSwimmingPool() > 0 || dto.getPlayGround() > 0
                || dto.getHandMadeFood() > 0 || dto.getPickUp() > 0 || dto.getDogBeauty() > 0
                || dto.getDogProgram() > 0 || dto.getDogWalking() > 0) && dto.getSearch() == null){
            return mapper.selHotelListToFilter(dto);
        }
        return null;
    }
    //영웅


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
        List<String> option=mapper.hotelOptionInfo(dto.getHotelPk());
        hotelInfoVo.setHotelOption(option);

        //좋아요 많은 갯수대로 호텔에 적힌 리뷰 최대 3개까지 가져옴.
        List<HotelReviewVo> reviewThree=mapper.getHotelReviewThree(dto.getHotelPk());
        if(reviewThree.size()==0){
            // 리뷰 없음.
        }
        int countReview=mapper.isMoreHotelReview(dto.getHotelPk());
        if(countReview>3){
            hotelInfoVo.setIsMoreReview(1);//리뷰 더있니 => 0 to 1
        }
        hotelInfoVo.setReviewList(reviewThree);
        hotelMainPage.setHotelInfoVo(hotelInfoVo);
        //유저 있으면 유저 강아지 정보 삽입.
        if(dto.getUserPk()>=1){
            List<MyDog> myDogList=mapper.getMyDogs(dto.getUserPk());
            hotelMainPage.setMyDogList(myDogList);

            if(myDogList.size()==0){
                //유저는 있는데 등록된 강아지는 없음.
            }
        }
        List<LocalDate> twoMonthDate=getTwoMonth();
        //twoMonthDate : 두달동안 날짜 리스트(LocalDate 타입)

        //twoMonth : String 타입 리스트.
        List<String> twoMonth=twoMonthDate
                .stream()
                .map(localDate -> localDate.toString())
                .collect(Collectors.toList());

        //호텔 예약정보 최근 두달 다가져오기.
        //시작날짜 : 요번달 첫날
        String startDate=twoMonthDate.get(0).toString();
        //끝나는날짜 : 다다음달 첫날 (BETWEEN 쿼리 사용 위함)
        String endDate=twoMonthDate.get(twoMonthDate.size()).plusDays(1).toString();
        //t_hotel_room_info_date 테이블에서 2달동안의 모든 방 정보 가져옴.

        HotelRoomAbleListDto listDto = null;
        listDto.setHotelPk(dto.getHotelPk());
        //메인페이지 첫화면은 호텔pk만 보내고 정리해서 줌.
        List<HotelRoomResInfoByMonth> hotelResInfoVos=getTwoMonthRoomAble(listDto);

        //박스갈이 & 데이터빼내기.
        List<HotelRoomEaByDate> eaByDates=new ArrayList<>();
        for (String date:twoMonth) {
            HotelRoomEaByDate eaByDate=new HotelRoomEaByDate();
            eaByDate.setDate(date);
            eaByDates.add(eaByDate);
        }
        //들어간 데이터 중 날짜정보만 빼옴.
        List<String> checkDate=eaByDates
                .stream()
                .map(HotelRoomEaByDate::getDate)
                .toList();
        if(!checkDate.containsAll(twoMonth)){
            //모든날짜 들어가지 않음. >> 에러.
        }
        //같은날이면 넣어줌.
        for (HotelRoomEaByDate date:eaByDates) {
            List<HotelRoomEa> roomEas=new ArrayList<>();

            for (HotelRoomResInfoByMonth resInfoByMonth:hotelResInfoVos) {
                if(date.getDate().equals(resInfoByMonth.getRoomDate())){
                    HotelRoomEa ea=new HotelRoomEa();
                    ea.setHotelRoomNm(resInfoByMonth.getHotelRoomNm());
                    ea.setRoomLeftEa(resInfoByMonth.getRoomLeftEa());
                    roomEas.add(ea);
                }
            }
            date.setRoomEas(roomEas);
        }

        hotelMainPage.setRoomEaByDates(eaByDates);
        return hotelMainPage;
    }
    //현재 날짜 기준으로 2달 세팅한거 삽입,hotelPk 삽입해서 방 받아오는거
    //시작날짜, 끝날짜 삽입, 호텔Pk 삽입 그날 가능한 날 방 리스트만 가져오기.
    //시작날짜, 끝날짜 삽입, 호텔Pk 삽입, 강아지들 사이즈 Pk 삽입, 방 리스트 가져오기.

    public List<HotelRoomResInfoByMonth> getTwoMonthRoomAble(HotelRoomAbleListDto dto){
        if(dto.getHotelPk()==0){
            //예외처리

        }
        List<LocalDate> twoMonthDate=getTwoMonth();
        List<String> twoMonth=twoMonthDate
                .stream()
                .map(localDate -> localDate.toString())
                .collect(Collectors.toList());
        //시작날짜 : 요번달 첫날
        String startDate=twoMonthDate.get(0).toString();
        //끝나는날짜 : 다다음달 첫날 (BETWEEN 쿼리 사용 위함)
        String endDate=twoMonthDate.get(twoMonthDate.size()).plusDays(1).toString();

        if(!(dto.getStartDate()==null&&dto.getEndDate()==null&&dto.getDogPks().size()==0)){
            //시작 끝 날짜 입력 되었고 개새끼 정보도 입력된 상태.(예약 마지막단계)
            try {


            }catch (Exception e){

            }
        }else if(!(dto.getEndDate()==null&&dto.getStartDate()==null)){
            //시작 끝 날짜만 입력 되어있는 상태.(예약 1단계)
            try{


            }catch (Exception e){

            }
        }else{// 시작 , 끝 날짜 설정X, (예약 1단계 or 상세페이지)
            try{
                List<HotelRoomResInfoByMonth> hotelResInfoVos=mapper.getHotelRoomResInfo(dto.getHotelPk(),startDate,endDate);
                return hotelResInfoVos;
            }catch(Exception e){

            }
        }
        return null;
    }
    public List<LocalDate> getTwoMonth(){
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
        return twoMonthDate;
    }

    //승준
}
