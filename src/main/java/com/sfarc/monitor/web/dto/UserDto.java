package com.sfarc.monitor.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sfarc.monitor.component.notifiers.NotifierType;
import com.sun.istack.Nullable;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author madhuwantha
 * created on 4/24/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    @Nullable
    private String userId;

    @Nullable
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String telephone;

    @Nullable
    private List<String> userSensors;

    @Nullable
    private List<NotifierType> userNotifiers;

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
