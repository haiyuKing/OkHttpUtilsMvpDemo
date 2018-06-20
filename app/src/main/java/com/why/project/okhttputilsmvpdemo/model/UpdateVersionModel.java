package com.why.project.okhttputilsmvpdemo.model;

import android.content.Context;

import com.why.project.okhttputilsmvpdemo.MyApplication;
import com.why.project.okhttputilsmvpdemo.R;
import com.why.project.okhttputilsmvpdemo.bean.UpdateVersionBean;
import com.why.project.okhttputilsmvpdemo.logic.LoginLogic;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by HaiyuKing
 * Used 发现新版本的请求类
 */

public class UpdateVersionModel {

	/**获取新版本的信息*/
	public void updateVersionInfo(String tag,
							   final LoadingDataCallBack<UpdateVersionBean> callBack) {
		final Context mContext = MyApplication.getAppContext();
		//执行请求任务
		try {
			LoginLogic.Instance().updateVersionApi(tag,new StringCallback() {
				@Override
				public void onBefore(Request request, int id)
				{
					callBack.onBefore("");//回调
				}
				@Override
				public void onAfter(int id)
				{
					callBack.onAfter();//回调
				}
				@Override
				public void onError(Call call, Exception e, int id)
				{
					callBack.onError("");//回调
				}
				@Override
				public void onResponse(String response, int id)
				{
					switch (id)
					{
						case 100://http
							try {
								if (response != null && !"".equals(response)){
									JSONObject versionInfoObj = new JSONObject(response);

									UpdateVersionBean updateVersionBean = new UpdateVersionBean();
									updateVersionBean.setVersionName(versionInfoObj.getString("versionName"));
									updateVersionBean.setForceUpdate(versionInfoObj.getString("forceUpdate"));
									updateVersionBean.setTitle(versionInfoObj.getString("title"));
									updateVersionBean.setContent(versionInfoObj.getString("content"));
									updateVersionBean.setDownloadurl(versionInfoObj.getString("downloadurl"));
									updateVersionBean.setFileSize(versionInfoObj.getString("fileSize"));

									callBack.onSuccess(updateVersionBean);
								}else {
									//提示失败
									callBack.onFailure("");//回调
								}
							}catch (JSONException e) {
								callBack.onError(mContext.getResources().getString(R.string.login_json_exception));//回调
							}catch (Exception e) {
								callBack.onError(mContext.getResources().getString(R.string.login_json_exception));//回调
							}
							break;
						case 101://https
							break;
					}
				}
				@Override
				public void inProgress(float progress, long total, int id)
				{
					//callBack.onProgress(progress,total,id);//回调
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
