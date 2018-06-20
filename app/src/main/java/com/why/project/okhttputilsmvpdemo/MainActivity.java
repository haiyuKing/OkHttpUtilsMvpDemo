package com.why.project.okhttputilsmvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.why.project.okhttputilsmvpdemo.bean.UpdateVersionBean;
import com.why.project.okhttputilsmvpdemo.iview.IUpdateAppView;
import com.why.project.okhttputilsmvpdemo.presenter.UpdateAppPresenter;
import com.why.project.okhttputilsmvpdemo.utils.ToastUtil;
import com.why.project.okhttputilsmvpdemo.utils.httputil.HttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;

public class MainActivity extends AppCompatActivity implements IUpdateAppView{

	private static final String TAG = MainActivity.class.getSimpleName();

	private UpdateAppPresenter mvpUpdateAppPresenter;//发现新版本
	private String okHttpTag = "";//网络请求用到的tag

	private Button btn_get;
	private TextView tv_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//presenter绑定view{1}
		if(mvpUpdateAppPresenter == null){
			mvpUpdateAppPresenter = new UpdateAppPresenter();
		}
		//presenter绑定view{2}
		if(mvpUpdateAppPresenter != null) {
			mvpUpdateAppPresenter.attachView(this);
		}

		initViews();
		initData();
		initEvents();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//取消网络请求,根据tag取消请求
		OkHttpUtils.getInstance().cancelTag(okHttpTag);
	}

	protected void onDestroy() {
		//取消网络请求
		Log.w(TAG,"this.getClass().getSimpleName()="+this.getClass().getSimpleName());
		OkHttpUtils.getInstance().cancelTag(okHttpTag);
		super.onDestroy();

		//presenter解绑view{3}
		if(mvpUpdateAppPresenter != null){
			mvpUpdateAppPresenter.detachView();
		}
	}

	private void initViews() {
		btn_get = (Button) findViewById(R.id.btn_get);
		tv_show = (TextView) findViewById(R.id.tv_show);
	}

	/**初始化数据*/
	private void initData() {
		okHttpTag = this.getClass().getSimpleName();
	}

	private void initEvents() {
		btn_get.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (HttpUtil.isNetworkAvailable(MainActivity.this)) {
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
