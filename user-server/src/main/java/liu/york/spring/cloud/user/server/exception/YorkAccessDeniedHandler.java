package liu.york.spring.cloud.user.server.exception;

import com.alibaba.fastjson.JSON;
import liu.york.spring.cloud.user.server.model.FailAuthModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求受保护资源 且 带有token 且 token有效 且 权限不足 会触发这个方法
 */
public class YorkAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");

        FailAuthModel model = new FailAuthModel();
        model.setCode(HttpStatus.UNAUTHORIZED.value());
        model.setMessage("权限不足!");
        model.setSuccess(false);

        response.getWriter().write(JSON.toJSONString(model));

    }
}