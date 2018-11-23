package liu.york.spring.cloud.zuul.oauth2.simulator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 模拟所有角色
 */
public class RoleManager {

    public static Set<String> roles = new HashSet<>();

    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_GUEST = "guest";

    static {
        List<String> roleList= Arrays.asList("admin", "user", "guest");

        roles.addAll(roleList);
    }

//    public static String getRoleByResource(String res){
//
//    }

}