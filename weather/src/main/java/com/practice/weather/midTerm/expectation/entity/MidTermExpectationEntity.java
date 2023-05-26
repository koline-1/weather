package com.practice.weather.midTerm.expectation.entity;

import com.practice.weather.main.entity.BaseEntity;
import com.practice.weather.midTerm.expectation.dto.MidTermExpectationDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mid_term_expectation")
@Entity(name = "MidTermExpectationEntity")
@SequenceGenerator(
        name = "MID_TERM_EXPECTATION_ID_GENERATOR",
        sequenceName = "MID_TERM_EXPECTATION_ID",
        initialValue = 1,
        allocationSize = 1)
public class MidTermExpectationEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MID_TERM_EXPECTATION_ID_GENERATOR")
    private long id;

    @Column(name = "STN_ID")
    private String stnId;

    @Column
    private String wfSv;

    public MidTermExpectationDto toDto() {
        return MidTermExpectationDto.builder().id(id).stnId(stnId).wfSv(wfSv).build();
    }

}
