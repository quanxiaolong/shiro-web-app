/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.custom.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题：Login </p>
 * <p>
 *	功能描述：
 * </p>
 * <p>创建日期：2017年3月14日上午11:38:05</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
@Controller
public class Login {

	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	//@RequestMapping(value = "/dologin.html")  
	public String login(HttpServletRequest request, ModelMap model) {  
		String msg = "";  
		String userName = request.getParameter("userName");  
		String password = request.getParameter("password");  
		System.out.println(userName);  
		System.out.println(password);  
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);  
		token.setRememberMe(true);  
		Subject subject = SecurityUtils.getSubject();  
		try {  
			subject.login(token);  
			if (subject.isAuthenticated()) {  
				return "redirect:/about/welcome.html";  
			} else {  
				return "login";  
			}  
		} catch (IncorrectCredentialsException e) {  
			msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		} catch (ExcessiveAttemptsException e) {  
			msg = "登录失败次数过多";  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		} catch (LockedAccountException e) {  
			msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		} catch (DisabledAccountException e) {  
			msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		} catch (ExpiredCredentialsException e) {  
			msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		} catch (UnknownAccountException e) {  
			msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		} catch (UnauthorizedException e) {  
			msg = "您没有得到相应的授权！" + e.getMessage();  
			model.addAttribute("message", msg);  
			System.out.println(msg);  
		}  
		return "login";  
	}
	
	@RequestMapping(value = "/login.html")
	public String doLogin(HttpServletRequest req, ModelMap model){
		Subject subject=SecurityUtils.getSubject();
		if(subject!=null&&subject.isAuthenticated()){
			return "redirect:about/welcome.html";
		}
		String errorClassName = (String)req.getAttribute("shiroLoginFailure");
		if(!StringUtils.isEmpty(errorClassName)){
			if(UnknownAccountException.class.getName().equals(errorClassName)) {
				model.addAttribute("message", "用户名/密码错误"); 
			} else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
				model.addAttribute("message", "用户名/密码错误");  
			} else if(errorClassName != null) {
				model.addAttribute("message", "用户名/密码错误");  
			}
			logger.error("登录错误类型信息：{}",errorClassName);	
		}
		Session session=subject.getSession();
		session.setAttribute("key", "123");
		return "login"; 
	}
	
	//@RequestMapping("login")
	public String toLogin(){
		return "login";
	}
}
