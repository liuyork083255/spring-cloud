package liu.york.spring.cloud.zuul.constants;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class FilterOrderConstant {

    /** 限流控制应该是第一个 */
    public static final int RATE_LIMIT_ORDER = FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 10;

    /** 检查 token 和校验 token 应该是第二个 */
    public static final int CHECK_TOKEN_ORDER = FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 5;

    /** 统一处理请求参数应该是第三个 */
    public static final int BUILDER_PARAM_ORDER = CHECK_TOKEN_ORDER + 1;

    /** 统一处理pre route post 三阶段异常应该是最后一个 */
    public static final int GLOBAL_ERROR_ORDER = 10;



}