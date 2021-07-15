package com.bupt.proj.dao;

import org.apache.ibatis.annotations.*;
import com.bupt.proj.model.Contact;

import java.util.List;

@Mapper
public interface ContactDAO {
    String table_name="contact";
    String insert_field="name,relationShip,phoneNumber";
    String select_field="id, status,"+insert_field;

    @Select({"select",select_field,"from",table_name})
    List<Contact> selectAll();

    @Insert({"insert into", table_name ,"(",insert_field,") values (#{name},#{relationShip},#{phoneNumber})"})
    int addContact(Contact contact);

    @Update({"update ", table_name, " set status=#{status} where id=#{id}"})
    void updateContactStatus(@Param("id") int id, @Param("status") int status);
}

