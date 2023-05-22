package com.kbstar.mapper;

import com.kbstar.dto.Cust;
import com.kbstar.frame.KBMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
// 스프링 프레임워크에서 사용되는 애노테이션으로, 해당 클래스를 레포지토리(데이터베이스 연동을 담당하는 객체)로 사용하겠다는 것을 명시하는 역할
@Mapper
//해당 인터페이스가 MyBatis의 Mapper로 사용되겠다는 것을 명시하는 역할
//CustMapper 인터페이스가 MyBatis의 Mapper로 사용되어 SQL 매핑을 수행하겠다는 것을 선언
// insert, delete, update, select, selectall 다 사용하겠다 이렇게 써주면 끝! 간단하게 데이터베이스의 CRUD 작업을 수행. mybatis가 대신 해즘
public interface CustMapper extends KBMapper<String, Cust> {
}
//어노테이션을 통해 이렇게 선언으로 종료. KBMapper의 메서드 사용