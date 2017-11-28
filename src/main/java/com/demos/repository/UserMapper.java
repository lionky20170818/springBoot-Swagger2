package com.demos.repository;

import com.demos.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User findById(@Param("id") long id);
}