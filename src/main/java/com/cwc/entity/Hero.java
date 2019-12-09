package com.cwc.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_hero")
public class Hero {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private String profession;
    private String phone;
    private String email;
    private Date datatime;
}
