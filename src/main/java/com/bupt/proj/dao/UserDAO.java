package com.bupt.proj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.bupt.proj.model.User;

@Mapper
public interface UserDAO {
    String table_name ="user";
    String insert_field ="name,phone,password";
    String select_field ="id, "+insert_field;

    @Insert({"insert into",table_name,"(",insert_field,
    ") values (#{name},#{phone},#{password})"})
    int addUser(User user);

    @Select({"select", select_field,"from", table_name, "where id=#{id}" })
    User selectById(int id);

    @Select({"select", select_field,"from", table_name, "where name=#{name}" })
    User selectByName(String name);

    @Select({"select", select_field,"from", table_name, "where phone=#{phone}" })
    User selectByPhone(String phone);

    @Update({"update", table_name,"set password=#{password} where id=#{id)"})
    void updatepassword (User user);



}
