package liu.york.spring.cloud.user.server.exception;

import com.alibaba.fastjson.JSON;
import liu.york.spring.cloud.user.server.model.FailAuthModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求受保护资源时，参数中没有 token 或者 无效token 参数则会触发
 */
public class YorkAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.addHeader("Content-Type", "application/json;charset=UTF-8");

        FailAuthModel model = new FailAuthModel();
        model.setCode(HttpStatus.FORBIDDEN.value());
        model.setMessage("禁止访问!");
        model.setSuccess(false);

        response.getWriter().write(JSON.toJSONString(model));

    }
}