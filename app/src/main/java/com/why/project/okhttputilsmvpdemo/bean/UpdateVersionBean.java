package com.why.project.okhttputilsmvpdemo.bean;

/**
 * Created by HaiyuKing
 * Used 更新APP的实体类
 */

public class UpdateVersionBean {

	private String versionName;//服务器上的版本号，比如3.0
	private String forceUpdate;//是否强制更新
	private String title;//标题，如果为空，则默认显示发现新版本
	private String content;//更新内容，如果为空，则不显示
	private String downloadurl;//下载地址
	private String fileSize;//文件大小

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDownloadurl() {
		return downloadurl;
	}

	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
