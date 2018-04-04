package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.ResponseBodys;
import com.example.demo.entity.ResponseHeader;
import com.example.demo.entity.ResponseInfo;
import com.example.demo.entity.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/")
public class UserController {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PostMapping("/user/register")
	public String register(User user) {
		String token = UUID.randomUUID().toString();
		System.out.println("token:" + token);
		this.user = user;
		ResponseHeader header = new ResponseHeader();
		header.setStatus(1001);
		header.setMessage("注册成功！");
		this.user.setToken(token);
		header.setToken(token);
		return JSON.toJSONString(header);
	}

	@PostMapping("/user/login")
	public String login(User user) {
		boolean flag = user.getToken().equals(this.user.getToken());
		ResponseHeader header = new ResponseHeader();
		header.setStatus(2001);
		String msg = "";
		if (flag) {
			msg = "登陆成功！";
		} else {
			msg = "登陆失败，token不正确！！";
		}
		header.setMessage(msg);
		header.setToken(this.user.getToken());
		return JSON.toJSONString(header);
	}

	@PostMapping("/user/loginState")
	public String loginState(User user) {
		ResponseHeader header = new ResponseHeader();
		header.setStatus(2001);
		boolean flag = user.getToken().equals(this.user.getToken());
		String msg = "";
		if (flag) {
			msg = "session可用！";
		} else {
			msg = "session过期，请重新登陆";
		}
		header.setMessage(msg);
		return JSON.toJSONString(header);
	}

	@GetMapping("/user/info/{username}")
	public String getUser(@PathVariable String username) {
		ResponseHeader header = new ResponseHeader();
		header.setStatus(3001);
		header.setMessage("获取用户信息成功！");
		ResponseBodys<User> body = new ResponseBodys<>();
		body.setT(this.user);
		ResponseInfo<User> info = new ResponseInfo<>();
		info.setBody(body);
		info.setHeader(header);
		return JSON.toJSONString(info);
	}

	@PostMapping("/user/logout")
	public String logout(User user) {
		ResponseHeader header = new ResponseHeader();
		header.setStatus(4001);
		header.setMessage("用户退出成功！");
		return JSON.toJSONString(header);
	}

	@GetMapping("/study")
	public ModelAndView study() {

		return new ModelAndView("study").addObject("cxg", "hehe");
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParams({
			@ApiImplicitParam(dataType = "java.lang.Long", name = "id", value = "id", required = true, paramType = "path"),
			@ApiImplicitParam(dataType = "com.example.demo.entity.User", name = "user", value = "用户信息", required = true) })
	// @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	@PostMapping("/user/{id}")
	public User insert(@PathVariable Long id, @RequestBody User user) {

		user.setId(id);
		return user;
	}

	@ApiOperation(value = "获取指定id用户详细信息", notes = "根据user的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "path")
	// @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {

		User user = new User();
		user.setId(id);
		user.setPassword("abc");
		user.setUsername("12345");
		return user;
	}

	@ApiOperation(value = "获取所有用户详细信息", notes = "获取用户列表信息")
	// @RequestMapping(value = "/users", method = RequestMethod.GET)
	@GetMapping("/users")
	public List<User> getUserList() {

		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Long l = Long.valueOf(i);
			list.add(new User(l, "cxg" + i, "as" + (100 + i)));
		}
		return list;
	}

	// @ApiIgnore
	@ApiOperation(value = "删除指定id用户", notes = "根据id来删除用户信息")
	@ApiImplicitParam(name = "id", value = "用户id", dataType = "java.lang.Long", paramType = "path")
	// @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/user/{id}")
	public String delete(@PathVariable Long id) {
		return "OK";
	}
}
