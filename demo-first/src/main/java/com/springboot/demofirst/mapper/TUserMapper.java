package com.springboot.demofirst.mapper;

import com.springboot.demofirst.entity.TUser;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByName(@Param("username") String username);

    int updateBalance(@Param("id") Integer id, @Param("oldBalance") Integer oldBalance, @Param("addBalance") int addBalance);
}