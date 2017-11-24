package com.nzf.mvpframe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 *
 */
public class PackageInfoUtils {


	public static String getPackageVersion(Context context) {
		try {
			PackageInfo packinfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			String version = packinfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "解析版本号失败";
		}
	}
}
