package com.felix.mybatismapperpage.mapper;


import com.felix.mybatismapperpage.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/*
 * @author felix
 * @date 2019/6/13
*/
@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {

    int saveUser(@Param("user") User user);
}
