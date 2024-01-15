package com.green.hoteldog.search;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.search.model.SearchHotelList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class SearchController implements Const{
    private final SearchService searchService;

    //구현해야 할 것.
    //2. 기본 호텔 리스트(평점순) or 지역순 with 무한 스크롤
    //3. 호텔 이름 검색 리스트
    public SearchHotelList getHotelList(HttpServletRequest servletRequest, HttpSession httpSession){
        int pageNum=1;
        String pageNumber=servletRequest.getParameter("pageNum");
        if(pageNumber!=null){
            pageNum=Integer.parseInt(pageNumber);
        }
        int startPageNum=(pageNum-1)*PAGE_ROW_COUNT;
        int endPageNUm=pageNum*PAGE_ROW_COUNT;
        String hotelNmKeyWord=servletRequest.getParameter("HotelNmKeyword");
        if(hotelNmKeyWord==null){
            //호텔이름 안넘어옴.
        }
        String encodeKeyword= URLEncoder.encode(hotelNmKeyWord);


        return null;

    }
}
