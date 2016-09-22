配置管理系统
============
功能介绍
------------
    集中管理系统中使用的各种配置（key-value），提供一个API接口，供系统快速生成复杂的配置文件。
    
    api接口定义：http://localhost/properties/{systemname}/{environment}
    
    参数说明：systemname:系统名称，environment:环境,此处默认三种环境 DEV, STABLE, PROD
    
使用技术
-----------
    后台框架：spring-boot-1.4.0 + mybatis + thymeleaf
    
    前端：bootstrap 
    
    数据库：Mariadb10.1（MySQL用户可以在application.yml中将驱动改下，SQL文件通用）<br>
运行步骤
-----------
    1.将mariadb.sql导入到数据库中
    
    2.修改application.yml数据库的配置
        mysql用户需要在pom.xml里面添加mysql的依赖
        
    3.在pom.xml目录下用mvn启动项目
      mvn spring-boot:run
联系方式
-----------
    Email:545544032@qq.com
