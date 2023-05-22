package com.kbstar.frame;

import java.util.List;

public interface KBMapper <K, V>{
    public void insert(V v) throws Exception;
    public void delete(K k) throws Exception;
    public void update(V v) throws Exception;
    public V select(K k) throws Exception;
    public List<V> selectall() throws Exception;
}
//frame을 만드는 이유는, 우리의 개발 표준을 만들기 위함. 누구나 이 표준을 따르시오!