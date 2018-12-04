客户端模式: Client Credentials Grant
---

客户端模式（Client Credentials Grant）指客户端以自己的名义，而不是以用户的名义，向"服务提供商"进行认证。严格地说，客户端模式并不属于OAuth框架所要解决的问题。在这种模式中，用户直接向客户端注册，客户端以自己的名义要求"服务提供商"提供服务，其实不存在授权问题。

![](../static/client_grant.png)

它的步骤如下：
> A）客户端向认证服务器进行身份认证，并要求一个访问令牌。
（B）认证服务器确认无误后，向客户端提供访问令牌。

A步骤中，客户端发出的HTTP请求，包含以下参数：
* granttype：表示授权类型，此处的值固定为"clientcredentials"，必选项。
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










post请求：

http://localhost:8080/oauth/token

参数名称 | 参数值 | 参数说明
---|--- |--- 
grant_type | client_credentials | 授权类型
Username | clientadmin | 放在Authorization,客户端的用户名
Password | 123 | 放在Authorization,客户端的密码
```
响应如下：
{
    "access_token": "0b18b42c-19d3-4689-9154-193b26433280",
    "token_type": "bearer",
    "expires_in": 2591992,
    "scope": "select"
}
```
---

说明：客户端模式只需要知道客户端的用户名和密码便能获取到正确的access_token，安全性较低，是四种模式中最简单的一种。

客户端模式虽然不需要用户的用户名和密码，但依然需要配置一个用户的用户名和密码，配置在application.properties中。

客户端模式可以配置多个客户端，可在内存中配置客户端，也可在数据库中配置客户端，配置客户端的地方如下图所示：

![image](./img/1.png)


配置认证受保护的资源如图所示，/api/**下的资源需要access_token才能访问

![image](./img/2.png)