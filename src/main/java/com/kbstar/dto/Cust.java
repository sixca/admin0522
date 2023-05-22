package com.kbstar.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// 롬복
// 생성자, 겟터/세터, 투스트링 선언 생략.
// ANNOTATION방식으로 Cust를 활용하도록 해줌.
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Cust {

    @Size(min=4, max=10, message="ID는 최소4개 최대10개 입니다.")    //validation 라이브러리 활용. 의존성 추가 필수
    @NotEmpty(message = "ID는 필수 항목 입니다")
    private String id;

    @Size(min=5, max=10, message="PWD는 최소5개 최대10개 입니다.")    //제약조건을 거는 것.
    @NotEmpty(message = "PWD는 필수 항목 입니다")
    private String pwd;

    @NotEmpty(message = "NAME은 필수 항목 입니다")
    private String name;

}

