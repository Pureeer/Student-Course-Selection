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
> StudentView.java
> - 学生选课界面
> - SelectListener
>   - 监听选课事件
> - DropListener
>   - 监听退课事件

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
| username | char(20) | NO   | UNI | NULL    |       |
| password | char(20) | YES  |     | NULL    |       |
+----------+----------+------+-----+---------+-------+
```

### course 表
```
+--------+----------+------+-----+---------+-------+
| Field  | Type     | Null | Key | Default | Extra |
+--------+----------+------+-----+---------+-------+
| cno    | char(4)  | NO   | PRI | NULL    |       |
| cname  | char(20) | NO   |     | NULL    |       |
| credit | int(11)  | NO   |     | NULL    |       |
| cdept  | char(10) | YES  |     | NULL    |       |
| tname  | char(8)  | NO   |     | NULL    |       |
+--------+----------+------+-----+---------+-------+
```

### stu_course 表
```
+-------+---------+------+-----+---------+-------+
| Field | Type    | Null | Key | Default | Extra |
+-------+---------+------+-----+---------+-------+
| sno   | char(4) | NO   | PRI | NULL    |       |
| cno   | char(4) | NO   | PRI | NULL    |       |
| grade | int(11) | NO   |     | NULL    |       |
+-------+---------+------+-----+---------+-------+
```
