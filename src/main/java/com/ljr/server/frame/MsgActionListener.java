package com.ljr.server.frame;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;


import com.ljr.common.Utils.PageService;
import com.ljr.server.config.MybatisConfig;

import com.ljr.server.entity.Msg;
import com.ljr.server.mapper.IMsgMapper;


public class MsgActionListener implements ActionListener
{

	static PageService pageService;
	//MsgDao msgDao;
	MsgMana msgMana;
	IMsgMapper msgMapper;
	public MsgActionListener(MsgMana msgMana) throws IOException {
		msgMapper = MybatisConfig.getMsgMapper();
		List<Msg> list = msgMapper.selectMsgs();
		pageService = new PageService(list);
		this.msgMana = msgMana;
	}

	public void actionPerformed(ActionEvent e) {
		String strBtn = e.getActionCommand();
		if ("删除".equals(strBtn)) {
			int row = msgMana.table.getSelectedRow();
			if (row != -1) {
				int msgId = Integer.parseInt(msgMana.table.getValueAt(row, 0).toString());
				try {
					if (msgMapper.deleteMsg(msgId)) {
						
						JOptionPane.showMessageDialog(null, "删除成功！");

					}
					else {
						JOptionPane.showMessageDialog(null, "删除失败！");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                pageService = new PageService(msgMana.refresh());
			}
		}
		else if ("刷新".equals(strBtn)) {
			msgMana.refresh();
		}
		else {
			List<Msg> listPerPage = null;
			if ("首页".equals(strBtn)) {
				listPerPage = pageService.gotoFirst();
			}
			else if ("下一页".equals(strBtn)) {
				listPerPage = pageService.gotoNext();
			}
			else if ("上一页".equals(strBtn)) {
				listPerPage = pageService.gotoPre();
			}
			else if ("尾页".equals(strBtn)) {
				listPerPage = pageService.gotoLast();
			}

			int rowCount = msgMana.model.getRowCount();// 获取表格中共有几行

			for (int i = 0; i < rowCount; i++) {
				msgMana.model.removeRow(0);

			}
			msgMana.lblCurrent.setText("当前在第"
					+ (pageService.getCurrentPage() + 1) + "页");
			for (int i = 0; i < listPerPage.size(); i++)
			{
				Msg msg = (Msg) listPerPage.get(i);
				Object[] rowData = { msg.getMsgId(),
						msg.getMsgContent(), msg.getSendFrom(), msg.getSendTo(),
						msg.getSendTime(), msg.getRemark(), msg.getMsgTye()};
				msgMana.model.addRow(rowData);
			}
		}
	}

}
