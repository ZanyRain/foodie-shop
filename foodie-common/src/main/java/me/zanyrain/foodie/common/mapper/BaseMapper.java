package me.zanyrain.foodie.common.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<T,PK> extends Mapper<T>, IdListMapper<T,PK>{

}
