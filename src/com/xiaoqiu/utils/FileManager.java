package com.xiaoqiu.utils;


public class FileManager {

	public static String getSaveFilePath() {
		if (CommonUtil.hasSDCard()) {
			return CommonUtil.getRootFilePath() + "xiaoqiuDouBan/files/";
		} else {
			return CommonUtil.getRootFilePath() + "xiaoqiuDouBan/files";
		}
	}
}
