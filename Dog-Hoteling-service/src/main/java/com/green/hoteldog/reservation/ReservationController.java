package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.ResInfoDto;
import com.green.hoteldog.reservation.model.ResInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class ReservationController {
    private final ReservationService service;
    @GetMapping
    public List<ResInfoVo> getUserReservation(@RequestBody ResInfoDto dto){

        return service.getUserReservation(dto);
    }
}
