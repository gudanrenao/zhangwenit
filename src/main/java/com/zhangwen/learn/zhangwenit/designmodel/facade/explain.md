##外观模式(门面模式)
###意图
为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
###主要解决
降低访问复杂系统的内部子系统时的复杂度，简化客户端与之的接口
###何时使用
* 客户端不需要知道系统内部的复杂联系，整个系统只需提供一个"接待员"即可
* 定义系统的入口
###应用实例
* JAVA的三层开发模式
###优点
* 减少系统相互依赖
* 提高灵活性
* 提高安全性
###缺点
* 不符合开闭原则，如果要改东西很麻烦，继承重写都不合适
