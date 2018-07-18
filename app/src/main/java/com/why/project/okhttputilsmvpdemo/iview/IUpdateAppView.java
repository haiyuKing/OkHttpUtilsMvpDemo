package com.why.project.okhttputilsmvpdemo.iview;


import com.why.project.okhttputilsmvpdemo.bean.UpdateVersionBean;

/**
 * Created by HaiyuKing
 * Used 更新APP的view
 */

public interface IUpdateAppView extends IBaseView{

	/** 打开发现新版本对话框*/
	void showUpdateDialog(UpdateVersionBean updateVersionBean);

}
