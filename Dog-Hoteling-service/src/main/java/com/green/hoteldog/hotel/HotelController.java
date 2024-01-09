package com.green.hoteldog.hotel;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.HotelInfoVo;
import com.green.hoteldog.hotel.model.HotelMainPage;
import com.green.hoteldog.hotel.model.HotelResAbleVo;
import com.green.hoteldog.user.model.UserHotelFavDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService service;

    //호텔 상세페이지 - 캘린더 : 예약 다 찬 날짜 뿌리기. 2개월치 날짜 뿌리기.
    /*
    {
        pic : [String]
        , hotelNm : String
        , contents : String
        , businessNum : String
        , hotelCallnum : String
        , roadAddress : String
        , reveiwList : [
           nickName : String
          , userPic : String
          , comment : String
         , score : int
         , updatedAt : String
         , pics : [String]
  , reviewFavCount : int
]}
     */
    //호텔 메인페이지 ( 2달치 정보 한번에 보냄)
    @GetMapping()
    public HotelMainPage getHotelDetail(){
        return null;
    }
    // 호텔 예약 2달치 정보 뿌림.
    @GetMapping()
    public List<HotelResAbleVo> getTwoMonthRes(){
        return null;
    }
    @GetMapping("/hotel/{page}/mark")
    @Operation(summary = "좋아요 toggle", description = "toggle로 처리함<br>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리: result(1), 좋아요 취소: result(2)")
    })
    public ResVo toggleHotelBookMark(@RequestBody UserHotelFavDto dto){
        return service.toggleHotelBookMark(dto);
    }
}
