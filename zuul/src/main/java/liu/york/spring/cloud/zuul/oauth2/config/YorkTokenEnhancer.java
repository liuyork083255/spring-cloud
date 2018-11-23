package liu.york.spring.cloud.zuul.oauth2.config;

import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.UUID;

public class YorkTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setValue(this.createTokenValue());

        ExpiringOAuth2RefreshToken refreshToken = (ExpiringOAuth2RefreshToken) token.getRefreshToken();
        token.setRefreshToken(new DefaultExpiringOAuth2RefreshToken(
                this.createTokenValue(), refreshToken.getExpiration()));

        return token;
    }

    private String createTokenValue() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}