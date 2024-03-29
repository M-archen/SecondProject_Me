package com.green.hoteldog.hotel;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.HotelListSelDto;
import com.green.hoteldog.hotel.model.HotelListSelVo;
import com.green.hoteldog.hotel.model.HotelMainPage;
import com.green.hoteldog.hotel.model.HotelMainPageDto;
import com.green.hoteldog.user.models.UserHotelFavDto;
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

    // 메인 페이지
    // 1. 강아지 정보와 주소를 기반으로 첫 기본 화면 셀렉트
    // 2. 필터링을 처리 후 화면 셀렉트
    // 3. 검색 기반으로 화면 셀렉트
    @GetMapping("/{page}")
    public List<HotelListSelVo> getHotelList(@RequestParam int page, HotelListSelDto dto){
        dto.setRowCount(Const.HOTEL_LIST_COUNT_PER_PAGE);
        dto.setPage(page);
        return service.getHotelList(dto);
    }
    //영웅


    @GetMapping()
    public HotelMainPage getHotelDetail(@RequestBody HotelMainPageDto dto){
        if(dto.getHotelPk()==0){
            //예외
        }
        HotelMainPage mainPage=service.getHotelDetail(dto);
        return mainPage;
    }

    @GetMapping("/hotel/{page}/mark")
    @Operation(summary = "좋아요 toggle", description = "toggle로 처리함<br>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리: result(1), 좋아요 취소: result(2)")
    })
    public ResVo toggleHotelBookMark(@RequestBody UserHotelFavDto dto){
        return service.toggleHotelBookMark(dto);
    }
    //승준





    // 브랜치 작업 하는법
    /*
    깃허브에서 브랜치 생성, 그 브랜치에서 작업.
    좌상단 Remote >브랜치 이름 확인하고 커밋&푸쉬
    좌상단 브랜치 Remote > master > Merge "master" into "자기이름" 클릭.


        깃허브 Comparing changes 접속. base: master , compare : "자기 브랜치 이름" 으로 설정.

         오른쪽 created pull request 클릭

         변경사항 작성 후 pull

      ..

      받을려는 사람은 Merge pull request 클릭해야 받아짐ㅋ.

     */
    //수정사항

}
