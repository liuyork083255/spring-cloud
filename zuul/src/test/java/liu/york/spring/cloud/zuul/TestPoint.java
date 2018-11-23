package liu.york.spring.cloud.zuul;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestPoint {

    @Test
    public void fun1(){

        List<String> list = Arrays.asList("a", "b", "c", "b");

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("a,b,c,b");
        Set<String> strings = AuthorityUtils.authorityListToSet(authorityList);
        System.out.println(JSON.toJSONString(strings));

    }

}