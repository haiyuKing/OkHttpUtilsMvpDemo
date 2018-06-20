package com.why.project.okhttputilsmvpdemo.logic;

import com.why.project.okhttputilsmvpdemo.MyApplication;
import com.why.project.okhttputilsmvpdemo.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by HaiyuKing
 * Used
 */

public class LoginLogic extends BaseLogic {

	private static LoginLogic _Instance = null;

	public static LoginLogic Instance() {
		if (_Instance == null)
			_Instance = new LoginLogic();
		return _Instance;
	}
	private LoginLogic() {
		this.context = MyApplication.getAppContext();//防止了内存泄漏
	}

	/**
	 * 发现新版本接口
	 */
	public String updateVersionApi(String tag, StringCallback callback)
			throws Exception {
		String result = "";
		OkHttpUtils
				.get()
				.url(getSpcyUrl(context.getString(R.string.update_version_url)))
				.id(100)
				.tag(tag)
				.build()
				.execute(callback);
		return result;
	}
}
