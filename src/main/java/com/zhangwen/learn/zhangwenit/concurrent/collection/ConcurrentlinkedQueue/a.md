### 入队列时做两件事情
* 将入队节点设置为当前队列尾节点(head的最后一个next)的下一个节点
* 更新tail节点，如果tail的next节点为空，那么将入队节点设置为tail的next节点，如果当前tail的next节点不为空，那么将入队节点设置成tail节点
