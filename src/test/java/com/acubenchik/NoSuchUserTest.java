package com.acubenchik;

import com.acubenchik.model.User;
import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by acubenchik on 24.09.14.
 */
public class NoSuchUserTest extends BaseTest{

    @Test
    public void testNoUser() {
        User user = getUserService().getUser(88l);
        Assert.assertNull(user);
    }
}
