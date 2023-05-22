package com.kbstar.frame;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KBService <K, V>{

    /*
    * 등록 및 가입 진행
    * argument: Object
    * return: null
    */

//  @Transactional  :: 해당 메소드가 실행될 때 트랜잭션을 시작하고, 메소드가 정상적으로 수행되었다면 트랜잭션을 커밋(commit), 예외가 발생하면 트랜잭션을 롤백(rollback)
//  @Transactional(readOnly = true) :: 읽기전용으로 처리. 조회 시 커밋이 실행되지 않음.

    @Transactional   //데이터의 조작(수정입력삭제 등)이 일어났을 때, 문제 발생 시 롤백 발생.  //실행되는 test.java가 아닌 여기 넣어두면 상속받아 널리 사용됨
    public void register(V v) throws Exception;
    @Transactional
    public void remove(K k) throws Exception;
    @Transactional
    public void modify(V v) throws Exception;
    @Transactional(readOnly = true)
    public V get(K k) throws Exception;
    @Transactional(readOnly = true)
    public List<V> get() throws Exception;

}
