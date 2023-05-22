package com.kbstar.mapper;

import com.kbstar.dto.Chart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

//어차피 CRUD를 위함이 아니기 대문에 KBMapper 상속은 지움
public interface ChartMapper {
    List<Chart> getMonthlyTotal();

}
