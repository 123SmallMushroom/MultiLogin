# MultiLogin
这是一款高版本可用的外置登入插件，理论上支持Paper 1.8+版本。\
已测试通过的Paper版本号为paper-1.8.8-443、paper-1.9.4-773、paper-1.10.2-916、paper-1.11.2-1104、paper-1.12.2-1618、Paper-1.13.2-655、Paper-1.14.4-243、Paper-1.15.2-391、Paper-1.16.4-354\
该插件通过使用反射修改NMS内容从而达到实现多Yggdrasil共存的效果。

## 功能
截止目前，该插件有下列几项功能：
* 多Yggdrasil共存
* 游戏内UUID转化
* 兼容多Yggdrasil环境下的白名单
* 可为单独的验证服务器内玩家开启白名单

* 账号安全
  * ID保护
      * 防止服务器在多Ygg环境下出现重名账户
      * 防止特定情况下ID被抢注而无法游戏
  * 账号保护：防止服务器在多Ygg环境下出现重UUID账户而导致数据错乱（概率极低）

## 命令和权限

### 命令
            /Whitelist add <target>                         // 添加target到白名单中
            /Whitelist remove <target>                      // 移除target的白名单
            /Whitelist on                                   // 开启全局白名单
            /Whitelist off                                  // 关闭全局白名单
            /Whitelist list                                 // 查看白名单总人数
            /Multilogin query [target]                      // 查询target（可以是离线玩家）是通过何种方式登入的游戏
            /Multilogin reload                              // 重新加载配置文件
### 权限
            multilogin.update                               // 接收新版本通知
            multilogin.whitelist.tab                        // 自动补全Whitelist命令参数所需要的权限
            multilogin.whitelist.add                        // 使用命令Whitelist add命令所需的权限
            multilogin.whitelist.remove                     // 使用命令Whitelist remove命令所需的权限
            multilogin.whitelist.on                         // 使用命令Whitelist on命令所需的权限
            multilogin.whitelist.off                        // 使用命令Whitelist off命令所需的权限
            multilogin.whitelist.list                       // 使用命令Whitelist list命令所需的权限
            
            multilogin.multilogin.tab                       // 自动补全Multilogin命令参数所需要的权限
            multilogin.multilogin.query                     // 使用命令Multilogin query命令所需的权限
            multilogin.multilogin.reload                    // 使用命令Multilogin reload命令所需的权限
            
## 使用
请查阅插件config.yml文件