package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "호텔 옵션들")
public class HotelOption {
    @Schema(name = "수영장 여부")
    @JsonProperty(value = "swimming_pool")
    private int swimmingPool=0;
    @Schema(name = "")
    @JsonProperty(value = "play_ground")
    private int playGround=0;
    @JsonProperty(value = "hand_made_food")
    private int handMadeFood=0;
    @JsonProperty(value = "pick_up")
    private int pickUp=0;
    @JsonProperty(value = "dog_beauty")
    private int dogBeautiy=0;
    @JsonProperty(value = "dog_program")
    private int dogprogram=0;
    @JsonProperty(value = "dog_walking")
    private int dogWalking=0;
}
