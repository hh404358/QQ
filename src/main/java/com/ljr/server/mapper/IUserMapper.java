package com.ljr.server.mapper;

import com.ljr.server.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper {
    /**
     * 根据用户编号查询单条用户数据
     * @param userId
     * @return
     */
    public Users queryById(int userId) ;
    /**
     * 查询所有用户信息
     * @return
     */
    public List<Users> queryAll() ;
    /**
     * 根据用户姓名查询单条用户数据
     */
    public Users queryByName(String name);
    /**
     * 检测用户名或密码是否正确
     */
    public Users checkNameAndPwd(String userName,String password);

    public boolean add(Users user);

    public boolean update(Users user);

    public boolean delete(int userId);


}
