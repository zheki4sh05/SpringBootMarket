package com.example.bam.mapper;

import com.example.bam.dto.UserDto;
import com.example.bam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

}
