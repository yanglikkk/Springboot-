# springboot-
使用springboot实现简单的单点跨域登录
原理图：
![image](https://github.com/yanglikkk/springboot-/blob/master/1.png)
![image](https://github.com/yanglikkk/springboot-/blob/master/2.png)

运行步骤：
1.首先在修改windows系统中的hosts文件，加上以上三个域名映射
127.0.0.1 www.sso.com
127.0.0.1 www.order.com
127.0.0.1 www.user.com
2.启动三个项目
3.访问www.user.com:8081/user/wel
4.输入用户名yangli以及密码123456
5.访问www.order.com:8082/order/wel
6.无需再次登录，系统实现了跨域的单点登录
