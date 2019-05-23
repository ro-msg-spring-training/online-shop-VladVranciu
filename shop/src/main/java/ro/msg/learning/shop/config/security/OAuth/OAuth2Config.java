package ro.msg.learning.shop.config.security.OAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private String clientid = "root1";
    private String clientSecret = "1234";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAs8nkLJSs4YEJaxLkQ010lZB/fOoJfYuB2cX5s/ozQJIWBI9P\n" +
            "vt51NGQREjLKTtuWNu4vxjR0ZeFGdkumUmI4KXrtJNetZnFgY//44rQtjIGP3oo/\n" +
            "/3B4UL0zw97Xw/1Crwc9fdipaGauHrk3cbHXARTV1cfuA5vg0Jh7ubPKfuhAX+Zb\n" +
            "/OOacRCZTE0ZcV7qIpDy3pIsJXPvmByCPfgINYlRcla+jlVDaswn4KIZiYAaBZKz\n" +
            "awVC8Qri06HaXr+mmZuawVhvQSYzW/LqJ9pUHij4aeOLZvwbScPUrlC8hDPEYTwQ\n" +
            "ixObywt00oE7Hbrp16xipMAQSRie6E6xrDPKzQIDAQABAoIBAHi/PCaQx7dAAYJP\n" +
            "Esz8xTL59BWB3VPbER+gbf7pJ+jS9ESlt2o6X78LAcgWIndp1O4ZdC6K5i3SaY9j\n" +
            "aVXlL1D0hTZ8P7IQrSscLByd5AV8spJEiYzWDYg0OgLvtDul6VZB55OQot5CL+jk\n" +
            "fg824G6SfrbwarMzyn5uGpJy5Knr06u37g/dEV/Hmvq5lGcdAVLED/UdcNCS9n7m\n" +
            "r5ggF8v1SmTuhbRi+zs0i+0Ko7bszYOZiur1vR3u+ZIS1RdyJngO78iyiagqL8tt\n" +
            "MW6qe5i3hu9238pGrzhOrYvKc+7MxElCOZ9ti9obDumzfy4AlDSac2Uv6YNvaDah\n" +
            "XNt8wEUCgYEA2yGWAMgZL2coYmbSBfT3fJZV55WUqvlr1dh2NrExSsIL6e7H5sBp\n" +
            "1D5mP6blTRhm/I6/L3sGfFWLBrrhoZXD16yK6tRw3FpUWCRVW6UxLLzrt5AkBOz0\n" +
            "JVrseIVmFASFrWdSJzO9/sTCKEoRLTe3Lw4N3UXn7p/4msKmdEk+gbsCgYEA0gm+\n" +
            "W/yd+aQiprSBT3JqTG//HxOc2HedTBykzCcp0Eoc597ME/qzTepST8SVllMYWz9P\n" +
            "bquoKRhTgKL86bNbix6cbBxT9+De5X8pHIOCqqCEz+HxvC1BJ3+ozVuX2sXC2+Cv\n" +
            "lluOVk6y0nhEK/idvcsiKLV7Bwsvx7dxU4MiuRcCgYBhmNrbbEOzVimpcgwWtR1J\n" +
            "Mxf9tiRp5f7+VpOIFnFSz4xI253TC13XNxeJQAceyECOOfGy7wNbeyoZjECUHEUG\n" +
            "mQWiQ44kxmKMg1o1ICT/vQDrV9qDCxpOTOnHX/qv7bewHLV9WlFD2FR1Z0+xKQCA\n" +
            "h6zrIYiEQmGSZCGI51pcowKBgGmSZjE8R3dMTfJ29iwluVJUVOqc5BBWByVTnG3V\n" +
            "HYpJRkAx12gPcgKuzdK0X+/gMnTi3J0nAO4vAQG+attYwKGrsDoS/w2v5Ll78gEY\n" +
            "BRyYdEk9K1FHeeFNpF4cNLbNAkWcqQUtXdz5z55EvxdO7pKQQMovdfgzYj/Db93n\n" +
            "IxPfAoGBAJXOkXmnWUvYgiNE9x3gAxdIXg1Qezh9254GLWWi4S3eRHCLpDkdNIMn\n" +
            "PrD08AID5OrxMXKYmRi97QkAWenFlEBjw3NHUqLAaVMI8h287QgH/t0cvdG0ZafJ\n" +
            "m8748ZoxezQxkMsWXwMj8EnABJODWYrr8dzfdksKd1qjj37pzUgZ\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs8nkLJSs4YEJaxLkQ010\n" +
            "lZB/fOoJfYuB2cX5s/ozQJIWBI9Pvt51NGQREjLKTtuWNu4vxjR0ZeFGdkumUmI4\n" +
            "KXrtJNetZnFgY//44rQtjIGP3oo//3B4UL0zw97Xw/1Crwc9fdipaGauHrk3cbHX\n" +
            "ARTV1cfuA5vg0Jh7ubPKfuhAX+Zb/OOacRCZTE0ZcV7qIpDy3pIsJXPvmByCPfgI\n" +
            "NYlRcla+jlVDaswn4KIZiYAaBZKzawVC8Qri06HaXr+mmZuawVhvQSYzW/LqJ9pU\n" +
            "Hij4aeOLZvwbScPUrlC8hDPEYTwQixObywt00oE7Hbrp16xipMAQSRie6E6xrDPK\n" +
            "zQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}