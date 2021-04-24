package com.sfarc.monitor.web.mappers;

import com.sfarc.monitor.entity.User;
import com.sfarc.monitor.web.dto.UserDto;
import org.mapstruct.Mapper;

/**
 * @author madhuwantha
 * created on 4/24/2021
 */

@Mapper(uses = {DateMapper.class})
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
