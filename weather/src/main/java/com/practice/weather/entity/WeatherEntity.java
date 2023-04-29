package com.practice.weather.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test")
@Entity(name = "test")
public class WeatherEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DATA")
    private String data;

}
