package com.sfarc.monitor.service;

import com.sfarc.monitor.config.InMemoryHashTypes;
import com.sfarc.monitor.entity.Sensor;
import com.sfarc.monitor.entity.User;
import com.sfarc.monitor.repository.SensorRepository;
import com.sfarc.monitor.repository.UserRepository;
import com.sfarc.monitor.web.dto.SensorDto;
import com.sfarc.monitor.web.dto.UserDto;
import com.sfarc.monitor.web.mappers.SensorMapper;
import com.sfarc.monitor.web.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    CacheService<List<String>> cacheService;

    @Autowired
    UserMapper userMapper;

    public UserDto addSensor(String userId, String sensorId){
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.getUserSensors().add(sensorId);
        User saved = userRepository.save(user);
        return userMapper.userToUserDto(saved);
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }


    public UserDto save(UserDto userDto){
        String userId = userDto.getUserId();
        if (userRepository.findById(userId).isEmpty()){
            return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
        }
        return userDto;
    }

    private User getUser(String userId){
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    public List<SensorDto> getOwnSensors(String userId)
            throws EntityNotFoundException
    {
        User user = getUser(userId);

        return user.getUserSensors()
                .stream()
                .parallel()
                .map(sensorId -> sensorRepository.findById(sensorId).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toList())
                .stream()
                .parallel()
                .map(sensorMapper::sensorToSensorDto)
                .collect(Collectors.toList());
    }

    public void selectSensor(String userId, String sensorId){
        User user = getUser(userId);
        boolean userHasAccess = user.getUserSensors()
                .stream()
                .parallel()
                .anyMatch(s -> s.equals(sensorId));

        if (userHasAccess){
            List<String> currentSensorLiters = null;
            try {
                currentSensorLiters = cacheService.get(InMemoryHashTypes.SENSOR_LISTENERS, sensorId);
            }catch (Exception e){
                currentSensorLiters = new ArrayList<>();
            }

            currentSensorLiters.add(userId);
            cacheService.put(InMemoryHashTypes.SENSOR_LISTENERS, sensorId, currentSensorLiters);
        }

    }
}
