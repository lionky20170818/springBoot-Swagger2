package com.demos.swagger2;

import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * http://blog.csdn.net/zheng0518/article/details/65635357
 * http://www.jianshu.com/p/3d597e9d2d67
 */
@Slf4j
public class FeignTest {

    interface GitHub {
        @RequestLine(" /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

        @RequestLine(" /")
        String get();
    }

    static class Contributor {
        String login;
        int contributions;
    }

    @Test
    public void testFeign() {
                                                            //进行BA认证
        GitHub github = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin", "123"))
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        // 获取贡献者列表，并打印其登录名以及贡献次数
        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            log.info(contributor.login + " (" + contributor.contributions + ")");
        }
    }

    @Test
    public void testAuthFeign() {
        GitHub github = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin", "123456"))
                .decoder(new StringDecoder())
//                .encoder(new JacksonEncoder())//指定对象编码方式
//                .decoder(new JacksonDecoder())//指定对象解码方式
                .logger(new Logger.JavaLogger().appendToFile("logs/http.log"))//JavaLogger、ErrorLogger和NoOpLogger，默认为NoOpLogger
                .logLevel(Logger.Level.FULL)//Log Level：NONE、BASIC、HEADERS和FULL，NONE为不打印，FULL为打印headers、body和metadata，BASIC打印request method和url或response状态码和headers，HEADERS打印request或response的基本信息。
                .options(new Request.Options(1000, 3500))//指定连接超时时长及响应超时时长
                .retryer(new Retryer.Default(5000, 5000, 3))//其构造函数使得我们可以传入period、maxPeriod和maxAttempts三个参数进行配置，默认的period为100ms，maxPeriod为1000ms，maxAttempts为5次。
                .target(GitHub.class, "https://github.com/lionky20170818/201708-com-test");

        log.info("auth server resp {}", github.get());
    }

}
