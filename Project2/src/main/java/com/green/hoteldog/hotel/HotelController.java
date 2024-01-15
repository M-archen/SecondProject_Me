package com.green.hoteldog.hotel;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.exception.ExceptionMsg;
import com.green.hoteldog.hotel.model.*;
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

    @GetMapping()
    public HotelMainPage getHotelDetail(@RequestBody HotelMainPageDto dto){


        return null;
    }

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
