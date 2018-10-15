package com.originaldreams.usermanager.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper {

    /**
     * 通过name返回id
     * @param name 权限名
     * @return id
     */
    Long getIdByName(String name);
}
