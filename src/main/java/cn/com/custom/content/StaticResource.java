/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题：StaticResource </p>
 * <p>
 *    功能描述：
 * </p>
 * <p>创建日期：2017年3月14日下午3:53:13</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
@Controller
public class StaticResource {

	@RequestMapping(value="/about/{file}")
	public String staitcVisit(@PathVariable String file){
		return file;
	}
}
