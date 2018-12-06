package com.xiao.tools.db.util;

import com.xiao.tools.db.structure.ClassBuilder;
import com.xiao.tools.io.FileUtil;
import com.xiao.tools.string.StringUtil;

public class BeanUtil {

	/**
	 * 生成Bean文件
	 * 
	 * @param paths
	 * @param classBuilder
	 * @return
	 */
	public static void createBeanFile(String paths, ClassBuilder classBuilder) {
		// 判断类是否存在所属包
		paths = paths + StringUtil.toString(classBuilder.getPackageName()).replace(".", "/") + "/"
				+ classBuilder.getClassName() + ".java";
		FileUtil.writeFile(paths, classBuilder.toString());
	}

}
