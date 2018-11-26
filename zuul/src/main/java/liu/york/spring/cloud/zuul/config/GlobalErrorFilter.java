package liu.york.spring.cloud.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import liu.york.spring.cloud.zuul.model.ResponseUtil;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static liu.york.spring.cloud.zuul.config.ZuulAccessFilter.wrapFailResponse;

/**
 * 处理 pre route 出现的异常，由于默认有一个 {@link org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter}
 * 会抛出异常，所以需要把异常捕获并且不抛出来
 * 方式一：在 properties 文件中禁用该过滤器 zuul.<ClassName>.<tpye>.disable = true
 * 方式二：见下面逻辑
 *
 *
 *
 */
@Component
public class GlobalErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        /*
         * 1 看源码发现 SendErrorFilter 类默认是0，FilterConstants.SEND_ERROR_FILTER_ORDER，可以查看执行顺序 FilterProcessor#funFilters
         * 2 这异常类会把捕获到的异常直接抛出来
         * 3 结合1和2，所以设置一个error-filter在 SendErrorFilter 之前，并且把异常处理掉，处理逻辑见 run
         */
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return Boolean.TRUE;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();

        System.out.println("获取到 zuul 异常  exception:" + throwable.getCause().getMessage());
        wrapFailResponse(ctx, ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "网关异常"));

        /*
         * 这个方法的确可以异常处理掉，但是 ctx.getThrowable() 获取始终会有异常，后面的error异常过滤器会调用这个方法
         * 如果有异常就会抛出来，所以这里如果捕获到异常后就直接将这个异常从 ctx 对象中删除，这样后面的默认SendErrorFilter就捕获不到异常了
         */
        ctx.remove("throwable");

        return null;

    }
}