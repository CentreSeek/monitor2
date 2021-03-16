package com.yjjk.monitor.test;

import com.yjjk.monitor.entity.pojo.ZsEcgInfo;
import com.yjjk.monitor.mapper.ZsEcgInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-11 09:07:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    ZsEcgInfoMapper mapper;
    @Test
    public void test1() throws Exception {
        // 1.加载配置文件
//        InputStream inputStream = Resources.getResourceAsStream("application.yml");
//        // 2. 创建SqlSessionFactory对象实际创建的是DefaultSqlSessionFactory对象
//        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(inputStream);
//        // 3. 创建SqlSession对象实际创建的是DefaultSqlSession对象
//        SqlSession sqlSession = builder.openSession();
//        // 4. 创建代理对象
//        ZsEcgInfoMapper mapper = sqlSession.getMapper(ZsEcgInfoMapper.class);
        // 5. 执行查询语句
        List<ZsEcgInfo> zsEcgInfos = mapper.selectAll();
        for (ZsEcgInfo zsEcgInfo : zsEcgInfos) {
            Random random = new Random();
            Integer[] arr = new Integer[128];
            for (int i = 0; i < 128; i++) {
                arr[i] = random.nextInt(10);
            }
            zsEcgInfo.setEcg(Arrays.toString(arr));
            mapper.updateByPrimaryKeySelective(zsEcgInfo);
        }
    }
}
