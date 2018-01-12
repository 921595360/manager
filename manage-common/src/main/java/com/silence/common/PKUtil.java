package com.silence.common;

import java.util.UUID;

/**
 * 生成主键
 *   
 * PKUtil  
 *   
 * 2016年1月4日 下午2:51:34  
 *   
 * @version 1.0.0  
 *
 */
public class PKUtil {
	public static String getPrimarykeyStr() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
