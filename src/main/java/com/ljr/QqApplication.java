package com.ljr;

 
  
import com.ljr.client.control.Login;
import com.ljr.client.control.Main;
import com.ljr.server.frame.MainWindow;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
public class QqApplication {

    public static void main(String[] args) throws IOException {
        new Login();
        new Login();
        new MainWindow();
    }

}
