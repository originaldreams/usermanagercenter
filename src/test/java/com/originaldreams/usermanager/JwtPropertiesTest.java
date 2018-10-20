package com.originaldreams.usermanager;

import com.originaldreams.passport.config.JwtProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile({"test", "dev"})
public class JwtPropertiesTest {

    @Autowired
    private JwtProperties jwtProperties;


    @Test
    public void testJwtProperties () {
        Assert.assertNotNull(jwtProperties.getSecret());
        Assert.assertNotNull(jwtProperties.getExpiration());
    }
}
