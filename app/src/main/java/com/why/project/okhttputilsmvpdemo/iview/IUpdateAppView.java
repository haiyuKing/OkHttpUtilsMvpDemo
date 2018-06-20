package com.why.project.okhttputilsmvpdemo.iview;


import com.why.project.okhttputilsmvpdemo.bean.UpdateVersionBean;

/**
 * Created by HaiyuKing
 * Used 更新APP的view
 */

public interface IUpdateAppView {
	/** 显示正在加载进度框【通用】*/
	void showLoading(String msg);

	/** 隐藏正在加载进度框【通用】*/
	void hideLoading();

	/**显示提示信息【通用】*/
	void showToast(String msg);

	/** 获取OkHttpUtil用到的tag，一般使用当前类的类名即可。比如：this.getClass().getSimpleName()【通用】*/
	String getOkHttpUtilTag();

	/** 处理失败的情况【应该每一个接口对应一个，不过此处省略了】【主要用于第一次请求的时候，显示提示区域，一般情况下可以直接showToast即可】*/
	void showFail(String msg);

	/**
	 * 处理无网络的情况【应该每一个接口对应一个，不过此处省略了】【主要用于第一次请求的时候，显示提示区域，一般情况下可以直接showToast即可】*/
	void showNotNetWork(String msg);

	/** 打开发现新版本对话框*/
	void showUpdateDialog(UpdateVersionBean updateVersionBean);

}
