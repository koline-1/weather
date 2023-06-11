package com.practice.weather.midTerm.temperature.entity;

import com.practice.weather.baseEntity.BaseEntity;
import com.practice.weather.midTerm.temperature.dto.MidTermTemperatureDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mid_term_temperature")
@Entity(name = "MidTermTemperatureEntity")
@SequenceGenerator(
        name = "MID_TERM_TEMPERATURE_ID_GENERATOR",
        sequenceName = "MID_TERM_TEMPERATURE_ID",
        initialValue = 1,
        allocationSize = 1)
public class MidTermTemperatureEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MID_TERM_TEMPERATURE_ID_GENERATOR")
    private long id;

    @Column(name = "REG_ID")
    private String regId;

    @Column
    private String taMin3;

    @Column
    private String taMin3Low;

    @Column
    private String taMin3High;

    @Column
    private String taMax3;

    @Column
    private String taMax3Low;

    @Column
    private String taMax3High;

    @Column
    private String taMin4;

    @Column
    private String taMin4Low;

    @Column
    private String taMin4High;

    @Column
    private String taMax4;

    @Column
    private String taMax4Low;

    @Column
    private String taMax4High;

    @Column
    private String taMin5;

    @Column
    private String taMin5Low;

    @Column
    private String taMin5High;

    @Column
    private String taMax5;

    @Column
    private String taMax5Low;

    @Column
    private String taMax5High;

    @Column
    private String taMin6;

    @Column
    private String taMin6Low;

    @Column
    private String taMin6High;

    @Column
    private String taMax6;

    @Column
    private String taMax6Low;

    @Column
    private String taMax6High;

    @Column
    private String taMin7;

    @Column
    private String taMin7Low;

    @Column
    private String taMin7High;

    @Column
    private String taMax7;

    @Column
    private String taMax7Low;

    @Column
    private String taMax7High;

    @Column
    private String taMin8;

    @Column
    private String taMin8Low;

    @Column
    private String taMin8High;

    @Column
    private String taMax8;

    @Column
    private String taMax8Low;

    @Column
    private String taMax8High;

    @Column
    private String taMin9;

    @Column
    private String taMin9Low;

    @Column
    private String taMin9High;

    @Column
    private String taMax9;

    @Column
    private String taMax9Low;

    @Column
    private String taMax9High;

    @Column
    private String taMin10;

    @Column
    private String taMin10Low;

    @Column
    private String taMin10High;

    @Column
    private String taMax10;

    @Column
    private String taMax10Low;

    @Column
    private String taMax10High;


    public MidTermTemperatureDto toDto() {
        return MidTermTemperatureDto.builder().id(id).regId(regId).taMin3(taMin3).taMin3Low(taMin3Low).taMin3High(taMin3High)
                .taMax3(taMax3).taMax3Low(taMax3Low).taMax3High(taMax3High).taMin4(taMin4).taMin4Low(taMin4Low).taMin4High(taMin4High)
                .taMax4(taMax4).taMax4Low(taMax4Low).taMax4High(taMax4High).taMin5(taMin5).taMin5Low(taMin5Low).taMin5High(taMin5High)
                .taMax5(taMax5).taMax5Low(taMax5Low).taMax5High(taMax5High).taMin6(taMin6).taMin6Low(taMin6Low).taMin6High(taMin6High)
                .taMax6(taMax6).taMax6Low(taMax6Low).taMax6High(taMax6High).taMin7(taMin7).taMin7Low(taMin7Low).taMin7High(taMin7High)
                .taMax7(taMax7).taMax7Low(taMax7Low).taMax7High(taMax7High).taMin8(taMin8).taMin8Low(taMin8Low).taMin8High(taMin8High)
                .taMax8(taMax8).taMax8Low(taMax8Low).taMax8High(taMax8High).taMin9(taMin9).taMin9Low(taMin9Low).taMin9High(taMin9High)
                .taMax9(taMax9).taMax9Low(taMax9Low).taMax9High(taMax9High).taMin10(taMin10).taMin10Low(taMin10Low).taMin10High(taMin10High)
                .taMax10(taMax10).taMax10Low(taMax10Low).taMax10High(taMax10High).created(getCreated()).updated(getUpdated()).build();
    }

    public void updateFromDto(MidTermTemperatureDto dto) {
        this.regId = dto.getRegId();
        this.taMin3 = dto.getTaMin3();
        this.taMin3Low = dto.getTaMin3Low();
        this.taMin3High = dto.getTaMin3High();
        this.taMax3 = dto.getTaMax3();
        this.taMax3Low = dto.getTaMax3Low();
        this.taMax3High = dto.getTaMax3High();
        this.taMin4 = dto.getTaMin4();
        this.taMin4Low = dto.getTaMin4Low();
        this.taMin4High = dto.getTaMin4High();
        this.taMax4 = dto.getTaMax4();
        this.taMax4Low = dto.getTaMax4Low();
        this.taMax4High = dto.getTaMax4High();
        this.taMin5 = dto.getTaMin5();
        this.taMin5Low = dto.getTaMin5Low();
        this.taMin5High = dto.getTaMin5High();
        this.taMax5 = dto.getTaMax5();
        this.taMax5Low = dto.getTaMax5Low();
        this.taMax5High = dto.getTaMax5High();
        this.taMin6 = dto.getTaMin6();
        this.taMin6Low = dto.getTaMin6Low();
        this.taMin6High = dto.getTaMin6High();
        this.taMax6 = dto.getTaMax6();
        this.taMax6Low = dto.getTaMax6Low();
        this.taMax6High = dto.getTaMax6High();
        this.taMin7 = dto.getTaMin7();
        this.taMin7Low = dto.getTaMin7Low();
        this.taMin7High = dto.getTaMin7High();
        this.taMax7 = dto.getTaMax7();
        this.taMax7Low = dto.getTaMax7Low();
        this.taMax7High = dto.getTaMax7High();
        this.taMin8 = dto.getTaMin8();
        this.taMin8Low = dto.getTaMin8Low();
        this.taMin8High = dto.getTaMin8High();
        this.taMax8 = dto.getTaMax8();
        this.taMax8Low = dto.getTaMax8Low();
        this.taMax8High = dto.getTaMax8High();
        this.taMin9 = dto.getTaMin9();
        this.taMin9Low = dto.getTaMin9Low();
        this.taMin9High = dto.getTaMin9High();
        this.taMax9 = dto.getTaMax9();
        this.taMax9Low = dto.getTaMax9Low();
        this.taMax9High = dto.getTaMax9High();
        this.taMin10 = dto.getTaMin10();
        this.taMin10Low = dto.getTaMin10Low();
        this.taMin10High = dto.getTaMin10High();
        this.taMax10 = dto.getTaMax10();
        this.taMax10Low = dto.getTaMax10Low();
        this.taMax10High = dto.getTaMax10High();
    }
}
