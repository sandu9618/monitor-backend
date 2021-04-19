package com.sfarc.monitor.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorDto {
    private String sensorId;
    private String sensorName;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private Timestamp createdDate;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private Timestamp lastModifiedDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;
}
