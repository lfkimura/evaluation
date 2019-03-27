package com.lfkimura.iot.evaluation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    public Device(String name, String manufacturer, String type, String measureUnit) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.measureUnit = measureUnit;
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String manufacturer;
    /*
     Kind of Device being used like heartbeater or pressure measurer
     */
    @Column
    private String type;
    @Column
    private String measureUnit;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
