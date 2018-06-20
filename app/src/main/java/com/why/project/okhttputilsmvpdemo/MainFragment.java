package com.why.project.okhttputilsmvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.why.project.okhttputilsmvpdemo.bean.UpdateVersionBean;
import com.why.project.okhttputilsmvpdemo.iview.IUpdateAppView;
import com.why.project.okhttputilsmvpdemo.presenter.UpdateAppPresenter;
import com.why.project.okhttputilsmvpdemo.utils.ToastUtil;
import com.why.project.okhttputilsmvpdemo.utils.httputil.HttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by HaiyuKing
 * Used
 */

public class MainFragment extends Fragment implements IUpdateAppView{
	private static final String TAG = MainFragment.class.getSimpleName();

	private UpdateAppPresenter mvpUpdateAppPresenter;//发现新版本
	private String okHttpTag = "";//网络请求用到的tag

	/**View实例*/
	private View myView;
	private Context mContext;

	private Button btn_get;
	private TextView tv_show;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//presenter绑定view{1}
		if(mvpUpdateAppPresenter == null){
			mvpUpdateAppPresenter = new UpdateAppPresenter();
		}
	}

	//重写
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//presenter绑定view【适用presenter调用代码在onCreateView方法中执行的情况】{2}
		/*if(mvpPresenter != null) {
			mvpPresenter.attachView(this);
		}*/
		myView = inflater.inflate(R.layout.activity_main, container, false);
		return myView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
		//presenter绑定view【如果presenter调用的代码是在onCreateView中执行的话，那么此处不合适执行绑定的方法】{2"}
		if(mvpUpdateAppPresenter != null) {
			mvpUpdateAppPresenter.attachView(this);
		}

		mContext = getActivity();

		//初始化控件以及设置
		initViews();
		//初始化数据
		initData();
		initEvents();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		//presenter解绑view{3}
		if(mvpUpdateAppPresenter != null){
			mvpUpdateAppPresenter.detachView();
		}
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		//取消网络请求
		OkHttpUtils.getInstance().cancelTag(okHttpTag);
		super.onDestroy();
	}

	private void initViews() {
		btn_get = (Button) myView.findViewById(R.id.btn_get);
		tv_show = (TextView) myView.findViewById(R.id.tv_show);
	}

	/**初始化数据*/
	private void initData() {
		okHttpTag = this.getClass().getSimpleName();
	}

	private void initEvents() {
		btn_get.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (HttpUtil.isNetworkAvailable(mContext)) {
					//执行网络请求接口
					try {
						mvpUpdateAppPresenter.updateVersion();//请求发现新版本接口
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.network_enable));
				}
			}
		});
	}

	@Override
	public void showLoading(String msg) {
		//showProgressDialog("");//显示进度加载框
	}

	@Override
	public void hideLoading() {
		//dismissProgressDialog();//隐藏进度加载框
	}

	@Override
	public void showToast(String msg) {
		ToastUtil.showShortToast(msg);
	}

	@Override
	public String getOkHttpUtilTag() {
		return okHttpTag;
	}

	@Override
	public void showFail(String msg) {
		ToastUtil.showShortToast(msg);
	}

	@Override
	public void showNotNetWork(String msg) {
		ToastUtil.showShortToast(msg);
	}

	@Override
	public void showUpdateDialog(UpdateVersionBean updateVersionBean) {
		String showText = "新版本号：" + updateVersionBean.getVersionName() +
				"\n是否强制更新：" + updateVersionBean.getForceUpdate() +
				"\n标题：" + updateVersionBean.getTitle() +
				"\n内容：" + updateVersionBean.getContent().replace("\\n", "\n") +
				"\n文件大小：" + updateVersionBean.getFileSize() +
				"\n下载地址：" + updateVersionBean.getDownloadurl();
		tv_show.setText(showText);
	}
}
