package com.ljr.server.frame;

import com.ljr.server.config.MybatisConfig;
import com.ljr.server.entity.Users;
import com.ljr.server.mapper.IUserMapper;

import java.awt.HeadlessException;
import java.sql.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;



public class UserTableCahnged implements TableModelListener {
	JTable table;
	public UserTableCahnged(JTable table){
		this.table = table;
	}
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int userId = Integer.parseInt(table.getValueAt(row, 0).toString());
		String userName = table.getValueAt(row, 1).toString();
		String pwdString = table.getValueAt(row, 2).toString();
		//String IP = table.getValueAt(row, 3).toString();
		//String state = table.getValueAt(row, 4).toString();
		String userGender = table.getValueAt(row, 5).toString();
		String userEmail = table.getValueAt(row, 6).toString();
		//String userSignature = table.getValueAt(row, 5).toString();
		Date userBirthday = Date.valueOf(table.getValueAt(row, 9).toString());
		Users user = new Users(userId,userName,pwdString, userGender, userEmail,userBirthday);
		//UserDao userDao = new UserDao();
		IUserMapper userMapper = MybatisConfig.openSession().getMapper(IUserMapper.class);
		try {
			if(userMapper.update(user)){
				JOptionPane.showMessageDialog(null, "修改成功！");
			}
			else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
