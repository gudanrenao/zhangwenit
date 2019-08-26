## vhost虚拟主机
### 创建一个vhost
`rabbitmqctl add_vhost vhost_name`
### 删除一个vhost
`rabbitmqctl delete_vhost vhost_name`
### 添加权限
* `rabbitmqctl set_permissions -p vhostl root ".*" ".*" ".*"` : `授予 root用户可访问虚拟主机 vhostl，并在所有资源上都具备可配置、可写及可读的权限，`
* `rabbitmqctl set_permi ssions -p vhost2 root "^queue.*" ".*" ".*"` : `授予 root用户可访问虚拟主机 vhost2， 在以 "queue" 开头的资源上具备可配置权限， 并在 所有资源上 拥 有可写、可读 的权限，`
### 清除权限
* `rabbitmqctl clear_permissions -p vhost1 root`
### 列举vhost上的权限信息
* `rabbitmqctl list_permissions -p test`
### 列举用户上的权限信息
* `rabbitmqctl list_user_permissions vhost_test`
## 用户管理
### 新增用户
`rabbitmqctl add_user root root123` ： `创建一个用户名为 root、 密码为 root123 的用户`
### 修改用户密码
`rabbitmqctl change_password vhost_test 123`
### 清除用户密码(清除后无法登陆)
`rabbitmqctl clear_password vhost_test`
### 删除用户
`rabbitmqctl delete_user vhost_test`
### 用户列表
`rabbitmqctl list_users`
### 验证账密
`rabbitmqctl authenticate_user root root123`
### 角色类别五种
* none: 无任务角色。新创建的用户的角色默认为none。
* management: 可以访问Web管理页面。
* policymaker: 包含management一切权限，另外可以管理策略(Policy)和参数(Parameter).
* monitoring:  包含management一切权限，另外可以看到连接、信道、节点的相关信息。
* administrator: 包含 monitoring 的所有权限,井且可以管理用户、 虚拟主机、 权限、策略、参数等。 administrator 代表了最高的权限.
### 设置用户角色(可以设置多个。设置后，之前的角色会移除)
`rabbitmqctl set_user_tags demo management,monitoring`
## 插件
### 启用插件
`rabbitmq-plugins enable [plugin-name]`
### 禁用插件
`rabbitmq-plugins disable [plugin-name]`
### 当前使用的插件列表
`rabbitmq-plugins list`
## 应用管理
### 停止 RabbitMQ 服务应用，但是 Erlang 虚拟机还是处于运行状态
`rabbitmqctl stop_app`
### 启动 RabbitMQ 应用
`rabbitmqctl start_app`
### 停止运行 RabbitMQ 的 Erlang 虚拟机和 RabbitMQ 服务应用
`rabbitmqctl shutdown`
### 将 RabbitMQ 节点重置还原到最初状态。包括从原来所在的集群中删除此节点，从管理数据库 中删除所有的配置数据，如己配置的用户、 vhost 等，以及删除所有的持久化消息
`rabbitmqctl reset`
### 轮换日志文件
`rabbitmqctl rotate_logs {suffix}`


