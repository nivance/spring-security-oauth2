客户端模式: Client Credentials Grant
---

客户端模式（Client Credentials Grant）指客户端以自己的名义，而不是以用户的名义，向"服务提供商"进行认证。严格地说，客户端模式并不属于OAuth框架所要解决的问题。在这种模式中，用户直接向客户端注册，客户端以自己的名义要求"服务提供商"提供服务，其实不存在授权问题。

![](../static/client_grant.png)

它的步骤如下：
> * （A）客户端向认证服务器进行身份认证，并要求一个访问令牌。
> * （B）认证服务器确认无误后，向客户端提供访问令牌。

A步骤中，客户端发出的HTTP请求，包含以下参数：
* granttype：表示授权类型，此处的值固定为"client_credentials"，必选项。
* scope：表示权限范围，可选项。
```
     POST /token HTTP/1.1
     Host: server.example.com
     Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
     Content-Type: application/x-www-form-urlencoded

    grant_type=client_credentials
```     

认证服务器必须以某种方式，验证客户端身份。

B步骤中，认证服务器向客户端发送访问令牌，下面是一个例子。
```
     HTTP/1.1 200 OK
     Content-Type: application/json;charset=UTF-8
     Cache-Control: no-store
     Pragma: no-cache

     {
       "access_token":"2YotnFZFEjr1zCsicMWpAA",
       "token_type":"example",
       "expires_in":3600,
       "example_parameter":"example_value"
     }
```


# 实际应用

**1. 获取access_token**

`POST`请求：`http://localhost:8080/oauth/token`

`Content-Type: application/x-www-form-urlencoded`

`Authorization`:

参数名称 | 参数值 | 参数说明
---|--- |--- 
Username | clientadmin | 放在Authorization, 客户端的用户名
Password | 123456 | 放在Authorization, 客户端的密码

`Parameters`:

参数名称 | 参数值 | 参数说明
---|--- |--- 
grant_type | client_credentials | 授权类型


返回示例：
```
{
    "access_token": "7c374bd5-b1fd-45c1-b327-213c95d572ef",
    "token_type": "bearer",
    "expires_in": 2591999,
    "scope": "admin"
}
```

**2. 使用access_token获取数据**

`GET`请求：`http://localhost:8080/api/users?access_token=6833fa31-d39f-4f4e-bc85-adb86668c20c`
结果：
```
[
    {
        "name": "adolfo",
        "email": "adolfo@mailinator.com"
    },
    {
        "name": "demigreite",
        "email": "demigreite@mailinator.com"
    },
    {
        "name": "jujuba",
        "email": "jujuba@mailinator.com"
    }
]
```

