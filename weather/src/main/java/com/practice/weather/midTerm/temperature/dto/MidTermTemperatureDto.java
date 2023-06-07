package com.practice.weather.midTerm.temperature.dto;

import com.practice.weather.midTerm.temperature.entity.MidTermTemperatureEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MidTermTemperatureDto {

    private long id;

    private String regId;

    private String taMin3;

    private String taMin3Low;

    private String taMin3High;

    private String taMax3;

    private String taMax3Low;

    private String taMax3High;

    private String taMin4;

    private String taMin4Low;

    private String taMin4High;

    private String taMax4;

    private String taMax4Low;

    private String taMax4High;

    private String taMin5;

    private String taMin5Low;

    private String taMin5High;

    private String taMax5;

    private String taMax5Low;

    private String taMax5High;

    private String taMin6;

    private String taMin6Low;

    private String taMin6High;

    private String taMax6;

    private String taMax6Low;

    private String taMax6High;

    private String taMin7;

    private String taMin7Low;

    private String taMin7High;

    private String taMax7;

    private String taMax7Low;

    private String taMax7High;

    private String taMin8;

    private String taMin8Low;

    private String taMin8High;

    private String taMax8;

    private String taMax8Low;

    private String taMax8High;

    private String taMin9;

    private String taMin9Low;

    private String taMin9High;

    private String taMax9;

    private String taMax9Low;

    private String taMax9High;

    private String taMin10;

    private String taMin10Low;

    private String taMin10High;

    private String taMax10;

    private String taMax10Low;

    private String taMax10High;

    private LocalDateTime created;
    
    private LocalDateTime updated;

    public MidTermTemperatureEntity toEntity() {
        return MidTermTemperatureEntity.builder().id(id).regId(regId).taMin3(taMin3).taMin3Low(taMin3Low).taMin3High(taMin3High)
                .taMax3(taMax3).taMax3Low(taMax3Low).taMax3High(taMax3High).taMin4(taMin4).taMin4Low(taMin4Low).taMin4High(taMin4High)
                .taMax4(taMax4).taMax4Low(taMax4Low).taMax4High(taMax4High).taMin5(taMin5).taMin5Low(taMin5Low).taMin5High(taMin5High)
                .taMax5(taMax5).taMax5Low(taMax5Low).taMax5High(taMax5High).taMin6(taMin6).taMin6Low(taMin6Low).taMin6High(taMin6High)
                .taMax6(taMax6).taMax6Low(taMax6Low).taMax6High(taMax6High).taMin7(taMin7).taMin7Low(taMin7Low).taMin7High(taMin7High)
                .taMax7(taMax7).taMax7Low(taMax7Low).taMax7High(taMax7High).taMin8(taMin8).taMin8Low(taMin8Low).taMin8High(taMin8High)
                .taMax8(taMax8).taMax8Low(taMax8Low).taMax8High(taMax8High).taMin9(taMin9).taMin9Low(taMin9Low).taMin9High(taMin9High)
                .taMax9(taMax9).taMax9Low(taMax9Low).taMax9High(taMax9High).taMin10(taMin10).taMin10Low(taMin10Low)
                .taMin10High(taMin10High).taMax10(taMax10).taMax10Low(taMax10Low).taMax10High(taMax10High).build();
    }

}
