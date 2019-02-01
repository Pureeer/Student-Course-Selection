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

### util
> DBUtil.java
> - 数据库连接查询工具集

### view
> LoginView.java
> - 登录界面
> - LoginListener
>       - 监听登录事件


##  数据库结构
### student 表
```
+----------+----------+------+-----+---------+-------+
| Field    | Type     | Null | Key | Default | Extra |
+----------+----------+------+-----+---------+-------+
| sno      | char(4)  | NO   | PRI | NULL    |       |
| sname    | char(8)  | NO   |     | NULL    |       |
| sex      | char(2)  | NO   |     | NULL    |       |
| age      | int(11)  | YES  |     | NULL    |       |
| sdept    | char(10) | YES  |     | NULL    |       |
| username | char(20) | NO   |     | NULL    |       |
| password | char(20) | YES  |     | NULL    |       |
+----------+----------+------+-----+---------+-------+
```

### course 表
> 尚未建立

- cno
- cname
- credit
- cdept
- tname

### student-course 表
> 尚未建立

- sno
- cno
- grade
