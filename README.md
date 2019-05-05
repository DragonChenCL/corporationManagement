
   项目采用前后端分离的方式，前端采取了vue的一个集成框架[vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/guide/#%E5%8A%9F%E8%83%BD)，暂无前端,后端采用spring boot框架
## 项目主要技术
### 后端使用技术
* 本意采用spring cloud做成微服务，但是由于项目较小，暂时先做成单体应用，日后可能重构。目前后端项目文件在dragonclub中
* spring boot 框架
* 持久层采用spring-data-jpa框架
* 数据库连接池采用druid
* 集成swagger2构建restful
* 鉴权采用spring security + jwt（后期将重构加上redis，实现token可控）
* .....(待增加)
## 项目主要功能
