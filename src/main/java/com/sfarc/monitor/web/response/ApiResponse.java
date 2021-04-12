package com.sfarc.monitor.web.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

/**
 * @author = madhuwantha
 * created on 4/11/2021
 */
@Data
@Builder
public class ApiResponse {
    public boolean status;
    public String message;

    @CreatedDate
    public Date timestamp;

    public Object body;
}
