package com.xyds.studyblog.realm;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyds.studyblog.bean.User;
import com.xyds.studyblog.service.UserService;

/**
 * 自定义的指定Shiro验证用户登录的类
 * 
 * @see 在本例中定义了2个用户:jadyer和玄玉,jadyer具有admin角色和admin:manage权限,玄玉不具有任何角色和权限
 * @create Sep 29, 2013 3:15:31 PM
 * @author 玄玉<http://blog.csdn.net/jadyer>
 */
public class MyRealm extends AuthorizingRealm {

	/**
	 * 1、@Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。
	 * 2、@Autowired默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，如果要允许null
	 * 值，可以设置它的required属性为false，如：@Autowired(required=false)
	 * ，如果我们想使用名称装配可以结合@Qualifier注解进行使用，如下： Java代码 收藏代码
	 * 
	 * @Autowired() @Qualifier("baseDao") private BaseDao baseDao;
	 *              3、@Resource（这个注解属于J2EE的），默认安照名称进行装配，名称可以通过name属性进行指定，
	 *              如果没有指定name属性
	 *              ，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。
	 *              当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
	 *              Java代码 收藏代码
	 * @Resource(name="baseDao") private BaseDao baseDao;
	 */
	@Autowired
	private UserService userService;

	/**
	 * 为当前登录的Subject授予角色和权限
	 * 
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * @see 个人感觉若使用了Spring3
	 *      .1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String) super
				.getAvailablePrincipal(principals);
		// List<String> roleList = new ArrayList<String>();
		// List<String> permissionList = new ArrayList<String>();
		// //从数据库中获取当前登录用户的详细信息
		// User user = userService.getByUsername(currentUsername);
		// if(null != user){
		// //实体类User中包含有用户角色的实体类信息
		// if(null!=user.getRoles() && user.getRoles().size()>0){
		// //获取当前登录用户的角色
		// for(Role role : user.getRoles()){
		// roleList.add(role.getName());
		// //实体类Role中包含有角色权限的实体类信息
		// if(null!=role.getPermissions() && role.getPermissions().size()>0){
		// //获取权限
		// for(Permission pmss : role.getPermissions()){
		// if(!StringUtils.isEmpty(pmss.getPermission())){
		// permissionList.add(pmss.getPermission());
		// }
		// }
		// }
		// }
		// }
		// }else{
		// throw new AuthorizationException();
		// }
		// //为当前用户设置角色和权限
		// SimpleAuthorizationInfo simpleAuthorInfo = new
		// SimpleAuthorizationInfo();
		// simpleAuthorInfo.addRoles(roleList);
		// simpleAuthorInfo.addStringPermissions(permissionList);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		// 实际中可能会像上面注释的那样从数据库取得
		if (null != currentUsername && "jadyer".equals(currentUsername)) {
			// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
			simpleAuthorInfo.addRole("admin");
			// 添加权限
			simpleAuthorInfo.addStringPermission("admin:manage");
			System.out.println("已为用户[jadyer]赋予了[admin]角色和[admin:manage]权限");
			return simpleAuthorInfo;
		} else if (null != currentUsername && "玄玉".equals(currentUsername)) {
			System.out.println("当前用户[玄玉]无授权");
			return simpleAuthorInfo;
		}
		// 若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
		// 详见applicationContext.xml中的<bean id="shiroFilter">的配置
		return null;
	}

	/**
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (0 == user.getIsAvailable()) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getEmail(), // 用户名
				user.getPassword(), // 密码
				// ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
				getName() // realm name
		);
		this.setSession("currentUser", user);
		return authenticationInfo;

		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
		// UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		// System.out.println("--------------------------");
		// System.out.println("验证当前Subject时获取到token为" +
		// ReflectionToStringBuilder.toString(token,
		// ToStringStyle.MULTI_LINE_STYLE));
		// System.out.println("--------------------------");
		// User user = userService.getUserByUsername(token.getUsername());
		// if(null != user){
		// AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo
		// (user.getEmail(),user.getPassword(),getName());
		// this.setSession("currentUser", user);
		// return authenticationInfo;
		//
		// }else{
		// return null;
		// }
		// User user = userService.getByUsername(token.getUsername());
		// if(null != user){
		// AuthenticationInfo authcInfo = new
		// SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
		// user.getNickname());
		// this.setSession("currentUser", user);
		// return authcInfo;
		// }else{
		// return null;
		// }
		// 此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
		// 说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)
		// 这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
		// if("mike".equals(token.getUsername())){
		// AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("mike",
		// "123456", this.getName());
		// this.setSession("currentUser", "mike");
		// return authcInfo;
		// }else if("碧琳".equals(token.getUsername())){
		// AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("碧琳",
		// "123456", this.getName());
		// this.setSession("currentUser", "碧琳");
		// return authcInfo;
		// }
		// 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			System.out
					.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}