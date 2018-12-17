# mysql读写分离demo
* git clone
* run in tomcat

> 注册实现写<br>
登录实现读

### 使用springAop实现读写分离
1. 数据库使用了mysql,需要master,slave两个数据库
2. web使用springMvc
3. orm使用mybatis
3. 容器使用spring
4. 日志输在是System.out,可以根据自己修改路径在log4j.xml中
