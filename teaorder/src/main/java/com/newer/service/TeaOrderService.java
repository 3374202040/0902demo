package com.newer.service;

import com.newer.domain.TeaOrder;
import com.newer.mapper.TeaOrderMapper;
import com.newer.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class TeaOrderService {
    private TeaOrderMapper teaOrderMapper;
    private SqlSession sqlSession;

    public void init(){
        sqlSession= SqlSessionUtil.getSqlSession();
        teaOrderMapper=sqlSession.getMapper(TeaOrderMapper.class);
    }
    public int addOrder(TeaOrder teaOrder){
        init();
        int rows=teaOrderMapper.addOrder(teaOrder);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return rows;
    }
}
