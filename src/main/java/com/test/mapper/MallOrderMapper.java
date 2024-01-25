package com.test.mapper;

import com.test.dao.MallOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MallOrderMapper {

    @Insert("INSERT INTO MALL_ORDER(order_id,user_id,good_src,delete_fg,insert_id,insert_time) values(#{orderId},#{userId},NULL,NULL,'TEST','TEST')")
    int createOrder(@Param("orderId") String orderId,@Param("userId") String userId);

    @Select("SELECT MO.order_id,MO.user_id,MO.good_src,MO.delete_fg,MO.insert_id,MO.insert_time FROM MALL_ORDER MO WHERE" +
            " MO.order_id = #{orderId} AND MO.delete_fg = '0'")
    MallOrder getOrder(String orderId);
}
