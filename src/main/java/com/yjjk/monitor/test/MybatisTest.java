package com.yjjk.monitor.test;

import com.yjjk.monitor.entity.pojo.RecordBase;
import com.yjjk.monitor.mapper.RecordBaseMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-11 09:07:28
 **/
public class MybatisTest {

    @Test
    public void test1() throws Exception {
        // 1.加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 创建SqlSessionFactory对象实际创建的是DefaultSqlSessionFactory对象
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. 创建SqlSession对象实际创建的是DefaultSqlSession对象
        SqlSession sqlSession = builder.openSession();
        // 4. 创建代理对象
        RecordBaseMapper mapper = sqlSession.getMapper(RecordBaseMapper.class);
        // 5. 执行查询语句
        List<RecordBase> records = mapper.selectAll();
        for (RecordBase record : records) {
            System.out.println(record);
        }
    }
}
