/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.perms;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题：PermsController </p>
 * <p>
 *    功能描述：
 * </p>
 * <p>创建日期：2017年3月16日下午2:47:47</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
@Controller
public class PermsController {

	@RequestMapping("/perms.html")
	public String perms(){
		
		return "perms/perms";
	}
	
	@RequiresAuthentication
	@RequiresRoles("admina")
	@RequestMapping("/annoPerms.html")
	public String annoPerms(){
		return "perms/annoPerms";
	}
}
