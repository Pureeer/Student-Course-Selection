# 学生选课成绩管理系统
> Toy Project of DataBase Course

## 包结构
> AppConstants.java
> - 所有用到的常量

> DAO.java
> - 学生/管理员的枚举类型

### base
> Base.java
> - 数据访问基类

### dao
> AdminDAO.java
> - 管理员数据访问类

> StudentDAO.java
> - 学生数据访问类

### model
> Student.java
> - 学生实体类

> Course.java
> - 课程实体类

### util
> DBUtil.java
> - 数据库连接查询工具集

### view
> LoginView.java
> - 登录界面
> - LoginListener
>   - 监听登录事件

> StudentView.java
> - 学生选课界面
> - SelectListener
>   - 监听选课事件
> - DropListener
>   - 监听退课事件

> AdminView.java
> - 管理员界面
> - InputListener
>   - 监听登分事件

> StudentInfo.java
> - 学生管理界面
> - AddStudent
>   - 添加课程子窗口
> - DelStudent
>   - 删除课程子窗口

> CourseInfo.java
> - 课程管理界面
> - AddCourse
>   - 添加课程子窗口
> - DelCourse
>   - 删除课程子窗口

##  数据库结构
### student 表
```
+----------+-------------------+------+-----+---------+-------+
| Field    | Type              | Null | Key | Default | Extra |
+----------+-------------------+------+-----+---------+-------+
| sno      | char(4)           | NO   | PRI | NULL    |       |
| sname    | char(8)           | NO   |     | NULL    |       |
| sex      | enum('男','女')   | YES  |     | NULL    |       |
| age      | int(11)           | YES  |     | NULL    |       |
| sdept    | char(10)          | YES  |     | NULL    |       |
| username | char(20)          | NO   | UNI | NULL    |       |
| password | char(64)          | NO   |     | NULL    |       |
+----------+-------------------+------+-----+---------+-------+
```

### course 表
```
+--------+----------+------+-----+---------+-------+
| Field  | Type     | Null | Key | Default | Extra |
+--------+----------+------+-----+---------+-------+
| cno    | char(4)  | NO   | PRI | NULL    |       |
| cname  | char(20) | NO   |     | NULL    |       |
| credit | int(11)  | YES  |     | 0       |       |
| cdept  | char(10) | YES  |     | NULL    |       |
| tname  | char(8)  | YES  |     | NULL    |       |
+--------+----------+------+-----+---------+-------+
```

### stu_course 表
```
+-------+---------+------+-----+---------+-------+
| Field | Type    | Null | Key | Default | Extra |
+-------+---------+------+-----+---------+-------+
| sno   | char(4) | NO   | PRI | NULL    |       |
| cno   | char(4) | NO   | PRI | NULL    |       |
| grade | int(11) | YES  |     | NULL    |       |
+-------+---------+------+-----+---------+-------+
```

## 注意事项
- 测试学生账号
    - 账号：demo
    - 密码：test1234
- 测试管理账号
    - 账号：admin
    - 密码：admin1234
