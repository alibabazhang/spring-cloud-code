package com.rui.cn.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.rui.cn.entity.User;
import com.rui.cn.mapper.UserMapper;
import com.rui.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/14
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * hytrix失败回调
     *
     * @return
     **/
    @Override
    @HystrixCommand(fallbackMethod = "defaultAddUser", commandKey = "adduser")
    @CacheResult
    public String adduser(User user, HttpServletRequest request) {
        if (!"spring".equals(user.getName())) {
            throw new RuntimeException("名字错啦");
        }
        String s = "hello," + user.getName();
        System.out.println(s);
        return s;
    }

    @Override
    @HystrixCommand
    @CacheRemove(commandKey = "adduser")
    public String updateUser(User user) {
        String s = "hello," + user.getName();
        System.out.println(s);
        return s;
    }

    public String defaultAddUser(User user, HttpServletRequest request) {
        String s = "嘿嘿，名字都写错了！！！";
        System.out.println(s);
        return s;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
        int i=1/0;
    }
}
//让我们来逐个介绍下@HystrixCommand注解的各个参数：
//　　1：commandKey：配置全局唯一标识服务的名称，比如，库存系统有一个获取库存服务，那么就可以为这个服务起一个名字来唯一识别该服务，如果不配置，则默认是@HystrixCommand注解修饰的函数的函数名。
//　　2：groupKey：一个比较重要的注解，配置全局唯一标识服务分组的名称，比如，库存系统就是一个服务分组。通过设置分组，Hystrix会根据组来组织和统计命令的告、仪表盘等信息。
//              Hystrix命令默认的线程划分也是根据命令组来实现。默认情况下，Hystrix会让相同组名的命令使用同一个线程池，所以我们需要在创建Hystrix命令时为其指定命令组来实现默认的线程池划分。
//              此外，Hystrix还提供了通过设置threadPoolKey来对线程池进行设置。建议最好设置该参数，使用threadPoolKey来控制线程池组。
//　　3：threadPoolKey：对线程池进行设定，细粒度的配置，相当于对单个服务的线程池信息进行设置，也可多个服务设置同一个threadPoolKey构成线程组。
//　　4：fallbackMethod：@HystrixCommand注解修饰的函数的回调函数，@HystrixCommand修饰的函数必须和这个回调函数定义在同一个类中，因为定义在了同一个类中，
//             所以fackback method可以是public/private均可。
//　　5：commandProperties：配置该命令的一些参数，如executionIsolationStrategy配置执行隔离策略，默认是使用线程隔离，此处我们配置为THREAD，即线程池隔离。
//             参见：com.netflix.hystrix.HystrixCommandProperties中各个参数的定义。
//　　6：threadPoolProperties：线程池相关参数设置，具体可以设置哪些参数请见：com.netflix.hystrix.HystrixThreadPoolProperties
//　　7：ignoreExceptions：调用服务时，除了HystrixBadRequestException之外，其他@HystrixCommand修饰的函数抛出的异常均会被Hystrix认为命令执行失败而触发服务降级的处理逻辑
//           （调用fallbackMethod指定的回调函数），所以当需要在命令执行中抛出不触发降级的异常时来使用它，通过这个参数指定，哪些异常抛出时不触发降级（不去调用fallbackMethod），而是将异常向上抛出。
//　　8：observableExecutionMode：定义hystrix observable command的模式；
//   9：raiseHystrixExceptions：任何不可忽略的异常都包含在HystrixRuntimeException中；
//   10：defaultFallback：默认的回调函数，该函数的函数体不能有入参，返回值类型与@HystrixCommand修饰的函数体的返回值一致。如果指定了fallbackMethod，则fallbackMethod优先级更高。