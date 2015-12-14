# AIDLPool
AIDL在进程间通信很实用，但如果进程太多，重复地新建多个Service也不合适。使用AIDL池来实现一个池创建多个AIDL连接。
## 使用类的介绍
* poolBinder:一个单例，实现Binder的选择创建。
* mainActivity:客户端。模拟访问服务 。
* AidlManagerService:服务端。是在一个新进程中。模拟不同进程。
* addManager,plusManager:不同的后台服务，模拟多个进程。
* 各个aidl：不用多讲，为了生成aidl实例。
