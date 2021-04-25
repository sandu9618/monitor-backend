package com.sfarc.monitor.web.payoad;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;

/**
 * @author madhuwantha
 * created on 4/25/2021
 */

@Getter
@Setter
public class SelectSensorRequest {
    private String userId;
    private String sensorId;

    @Nullable
    private String previousSensorId;
}
