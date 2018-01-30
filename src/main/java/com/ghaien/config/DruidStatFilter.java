package com.ghaien.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by ghaien on 2018/1/30.
 * 与DruidConfiguration中filterRegistrationBean方法的效果相同
 * 只是这种方法需要在启动类中增加注解ServletComponentScan
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        }
)
public class DruidStatFilter extends WebStatFilter {

}
