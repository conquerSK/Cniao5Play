# Cniao5Play
关注点：
1.对BaseBean<T>的转换，下游只需要拿到T即可，无需关心code，message等。如果服务器返回异常要抛出给下游处理。
2.异常的处理是在基类Observer中统一处理的。
