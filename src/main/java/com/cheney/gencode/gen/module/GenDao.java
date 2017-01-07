package com.cheney.gencode.gen.module;

import java.util.List;

import com.cheney.gencode.module.Dao;
import com.cheney.gencode.module.Method;

/**
 * @Moudle: GenDao
 * @version:v1.0
 * @Description: 生成Dao
 * @author: xuyushuai
 * @date: 2014年10月21日 下午2:55:47
 *
 */
public class GenDao {
	/**
	 *
	 * <p>Title: 获取Dao</p>
	 * <p>author : xuyushuai</p>
	 * <p>date : 2014年10月21日 下午3:03:05</p>
	 * 
	 * @param json
	 * @return Dao
	 */
	public static Dao getDao(String json) throws Exception {
		Dao dao = null;
		try {
			dao = new Dao();
			List<Method> methods = GenMethod.getMethods(json);
			dao.setMethods(methods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
}
