package com.ljr.server.frame;

import com.ljr.common.constants.Constant;
import com.ljr.server.QQServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 服务器事件监听器
 */
public class ServerActionListen implements ActionListener{

	QQServer qqServer = null;
	ServerMana serverMana = null;
	public ServerActionListen(ServerMana serverMana)
	{
		this.serverMana = serverMana;
	}
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("启动MyQQ服务器")){

            try {
                qqServer = new QQServer(Constant.QQServerPort);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            serverMana.isRun = true;
			serverMana.lblState.setText("服务器正在运行中...");
			this.serverMana.btnStart.setActionCommand("停止MyQQ服务器");
			
		}
		else if(cmd.equals("停止MyQQ服务器")){
			if(qqServer != null){
				qqServer.closeServer();
				serverMana.isRun = false;
				serverMana.lblState.setText("服务器已关闭。");
				this.serverMana.btnStart.setActionCommand("启动服务器");
			}
		}
	}
	
}
