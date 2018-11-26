package liu.york.spring.cloud.zuul.config;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import liu.york.spring.cloud.zuul.constants.FilterOrderConstant;
import liu.york.spring.cloud.zuul.model.ResponseUtil;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static liu.york.spring.cloud.zuul.config.ZuulAccessFilter.wrapFailResponse;

/**
 * 访问速率控制层
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    private static final RateLimiter limiter = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterOrderConstant.RATE_LIMIT_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return Boolean.TRUE;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        if(limiter.tryAcquire()){
            return null;
        }else{
            wrapFailResponse(ctx, ResponseUtil.error(HttpStatus.NOT_ACCEPTABLE.value(), "请求过于频繁"));
            return null;
        }
    }
}