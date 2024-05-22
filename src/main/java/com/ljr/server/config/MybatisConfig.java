package com.ljr.server.config;

 
  
import com.ljr.server.mapper.IMsgMapper;
import com.ljr.server.mapper.IUserMapper;
import lombok.Data;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
public class MybatisConfig {
    static String resource = "mybatis-config.xml";

    public SqlSession getSqlSession() throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        System.out.println(sqlSession.getConnection());
        return sqlSession;
        //return sqlSessionFactory.openSession(true);
    }

    public static IUserMapper getUserMapper() throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3. 执行sql
        return sqlSession.getMapper(IUserMapper.class);
    }

    public static IMsgMapper getMsgMapper() throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3. 执行sql
        return sqlSession.getMapper(IMsgMapper.class);
    }

    private static SqlSessionFactory sessionFactory;

    static {
        try {
            //加载配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //创建sessionFactory对象
            sessionFactory = new SqlSessionFactoryBuilder().build(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取session对象
    public static SqlSession openSession() {
        return sessionFactory.openSession();
    }
}
