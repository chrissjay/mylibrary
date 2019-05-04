package com.example.mylibrary.dao;

import com.example.mylibrary.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {

    String table_name = " user ";
    String insert_field = " name, email, password";
    String select_field = " id " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field, ") values (#{name},#{email},#{password})"})
    int addUser(User user);

    @Select({"select", select_field, "from", table_name, "where email=#{email}"})
    User selectUserByEmail(@Param("email") String email);

    @Select({"select", select_field, "from", table_name, "where id=#{id}"})
    User selectUserById(@Param("id") int id);

}
