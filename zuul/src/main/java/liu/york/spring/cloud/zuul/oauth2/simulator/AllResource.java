package liu.york.spring.cloud.zuul.oauth2.simulator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 模拟数据库资源
 */
public class AllResource {

    public static Set<String> resources = new HashSet<>();

    static {

        List<String> resourcesList = Arrays.asList("","");

        resources.addAll(resourcesList);
    }

}