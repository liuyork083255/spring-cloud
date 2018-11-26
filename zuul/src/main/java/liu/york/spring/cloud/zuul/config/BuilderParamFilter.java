package liu.york.spring.cloud.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import liu.york.spring.cloud.zuul.constants.FilterOrderConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一处理请求参数
 * 便于微服务统一处理
 */
@Component
public class BuilderParamFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterOrderConstant.BUILDER_PARAM_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return Boolean.TRUE;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("Authorization");

        /* 将 url-token 请求参数移到 请求头中 */
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
            ctx.addZuulRequestHeader("Authorization", "Bearer " + token);
        }
        return null;
    }
}