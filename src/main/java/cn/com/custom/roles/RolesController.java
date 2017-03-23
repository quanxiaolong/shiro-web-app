/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.roles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题：RolesController </p>
 * <p>
 *    功能描述：
 * </p>
 * <p>创建日期：2017年3月16日下午2:48:27</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
@Controller
public class RolesController {

	@RequestMapping("/roles.html")
	public String roles(){
		
		return "roles/roles";
	}
	
	@RequestMapping("/annoRoles.html")
	public String anno(){
		return "roles/annoRoles";
	}
}
