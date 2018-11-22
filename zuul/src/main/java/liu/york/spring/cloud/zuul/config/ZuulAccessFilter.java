package liu.york.spring.cloud.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulAccessFilter extends ZuulFilter {

    /**
     * 这里通过返回具体的字符串表示当前过滤器拦截的位置
     *
     * pre:     请求路由之前被调用
     * routing: 路由请求时被调用
     * post:    在routing和error过滤器之后被调用
     * error:   处理请求时发生错误时被调用
     *
     * 四个常量值在 {@link FilterConstants}
     */
    @Override
    public String filterType() {
//        return FilterConstants.PRE_TYPE;
        return "pre";
    }

    /**
     * 如果有多个过滤器在此处拦截，该值是用于决定谁先执行
     * 越大越先执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否执行下面的 {@link ZuulAccessFilter#run()} 方法
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 拦截器拦截的核心逻辑
     * 1 比如后面的微服务需要验证请求头或者请求体中是否有token信息，由于zuul默认不会带有token在请求头中
     * 2 比如终端发过来的数据处于安全考虑，可能会加密，zuul可以在网关层解密，再传到后面的微服务
     *
     */
    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getHeader("token");

        if(token == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        } else {
            // TODO: 2018/11/22  根据token获取相应的登录信息，进行校验（略）
            // 如果权限不够，则可以返回 403
        }
        /* 验证通过后添加Basic Auth认证信息 */
        ctx.addZuulRequestHeader("Authorization", "Basic " + "xxxYYY");
        return null;
    }

/* 如果需要修改请求体 body  可以参考
public Object run() {
    try {
        RequestContext context = getCurrentContext();
        InputStream in = (InputStream) context.get("requestEntity");
        if (in == null) {
            in = context.getRequest().getInputStream();
        }
        String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
        body = "动态增加一段内容到body中: " + body;
        byte[] bytes = body.getBytes("UTF-8");
        context.setRequest(new HttpServletRequestWrapper(getCurrentContext().getRequest()) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStreamWrapper(bytes);
            }

            @Override
            public int getContentLength() {
                return bytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return bytes.length;
            }
        });
    } catch (IOException e) {
        rethrowRuntimeException(e);
    }
    return null;
}
*/

/*
    Zuul 实现灰度发布
    大型分布式系统中，灰度发布是保证线上系统安全生产的重要手段，一般的做法为：从集群中指定一台（或某几台）机器，
    每次做新版本发布前，先只发布这些机器上，先观察一下是否正常，如果稳定运行后，再发布到其它机器
    利用 Spring Cloud MicroService中有一个metadata-map
    首先要引入一个jar包：(这是github上开源的一个项目ribbon-discovery-filter-spring-cloud-starter）
    eureka:
        instance:
            metadata-map:
                gated-launch: false
    所有节点发布后，默认灰度模式为false。然后把特定的灰度机器上的配置，该参数改成true
    参考链接：https://www.cnblogs.com/yjmyzz/p/spring-cloud-zuul-demo.html

@Override
public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();

    Object token = request.getParameter("token");

    //校验token
    if (token == null) {
        logger.info("token为空，禁止访问!");
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        return null;
    } else {
        //TODO 根据token获取相应的登录信息，进行校验（略）

        //灰度示例
        RibbonFilterContextHolder.clearCurrentContext();
        if (token.equals("1234567890")) {
            RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "true");
        } else {
            RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "false");
        }
    }

    //添加Basic Auth认证信息
    ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));

    return null;
}

这里演示了通过特定的token参数值，将请求引导到gated-lanuch=true的机器上
*/

}