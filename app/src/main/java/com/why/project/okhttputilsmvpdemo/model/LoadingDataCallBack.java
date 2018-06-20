package com.why.project.okhttputilsmvpdemo.model;

/**
 * Used 常规接口请求的回调【请求发现新版本接口】
 */

public interface LoadingDataCallBack<T> {

	/**
	 * 数据请求前的操作
	 * @ TODO 一般用于显示加载对话框
	 */
	void onBefore(String msg);

	/**
	 * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理
	 * @ TODO 请求结束，一般隐藏加载提示对话框，或者执行下一步操作
	 */
	void onAfter();

	/**
	 * 数据请求成功
	 * @param response 请求到的数据
	 * @ TODO 请求成功，一般执行下一步的操作（继续请求接口，或者显示）
	 */
	void onSuccess(T response);

	/**
	 * 数据请求成功的提示语
	 * @param msg 提示语
	 * @ TODO 请求成功，一般用来提示msg
	 */
	void onSuccessToast(String msg);

	/**
	 *  请求失败
	 *  使用网络API接口请求方式时，虽然已经请求成功但是由于{@code msg}的原因无法正常返回数据。
	 *  @ TODO 请求失败，一般用来提示msg状态值以及提示语，或者显示无数据的区域【所以需要跟onError方法区别开来】
	 */
	void onFailure(String msg);

	/**
	 * 请求数据错误，指在请求网络API接口请求方式时，出现无法联网、缺少权限，内存泄露等原因导致无法连接到请求数据源。
	 * @ TODO 请求错误，一般隐藏加载提示对话框，提示服务器返回的失败信息等
	 */
	void onError(String msg);

	/**
	 * 无网络时返回
	 * @ TODO 一般隐藏加载提示对话框，提示当前网络不可用，或者显示无网络的区域【所以需要跟onError方法区别开来】
	 */
	void onNoNetWork(String msg);
}
