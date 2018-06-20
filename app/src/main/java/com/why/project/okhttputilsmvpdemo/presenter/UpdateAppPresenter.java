package com.why.project.okhttputilsmvpdemo.presenter;

import android.text.TextUtils;

import com.why.project.okhttputilsmvpdemo.bean.UpdateVersionBean;
import com.why.project.okhttputilsmvpdemo.iview.IUpdateAppView;
import com.why.project.okhttputilsmvpdemo.model.LoadingDataCallBack;
import com.why.project.okhttputilsmvpdemo.model.UpdateVersionModel;

/**
 * Created by HaiyuKing
 * Used 更新APP的presenter
 */

public class UpdateAppPresenter {
	private IUpdateAppView mvpView;//view接口类
	private UpdateVersionModel mUpdateVersionModel;//发现新版本接口请求的model类

	public UpdateAppPresenter(){
		mUpdateVersionModel = new UpdateVersionModel();
	}

	/**
	 * 绑定view，一般在初始化中调用该方法
	 */
	public void attachView(IUpdateAppView view){
		mvpView = view;
	}

	/**
	 * 断开view，一般在onDestroy中调用
	 */
	public void detachView() {
		mvpView = null;
	}

	/**
	 * 是否与View建立连接
	 * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
	 */
	public boolean isViewAttached(){
		return mvpView != null;
	}

	/**发现新版本接口数据*/
	public void updateVersion() {
		if (!TextUtils.isEmpty(mvpView.getOkHttpUtilTag())) {
			mUpdateVersionModel.updateVersionInfo(mvpView.getOkHttpUtilTag(),
					new LoadingDataCallBack<UpdateVersionBean>() {
						@Override
						public void onBefore(String msg) {
							if (isViewAttached()) {
								mvpView.showLoading(msg);
							}
						}

						@Override
						public void onAfter() {
							if (isViewAttached()) {
								mvpView.hideLoading();
							}
						}

						@Override
						public void onSuccess(UpdateVersionBean response) {
							if (isViewAttached()) {
								mvpView.showUpdateDialog(response);
							}
						}

						@Override
						public void onSuccessToast(String msg) {
							if (isViewAttached()) {
								mvpView.showToast(msg);
							}
						}

						@Override
						public void onFailure(String msg) {
							if (isViewAttached()) {
								mvpView.showFail(msg);
							}
						}

						@Override
						public void onError(String msg) {
							if (isViewAttached()) {
								mvpView.showToast(msg);
							}
						}

						@Override
						public void onNoNetWork(String msg) {

						}
					});
		}
	}
}
