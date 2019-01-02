package com.albrus.shiro.password;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class Password {

    @Test
    public void getPassword() {
        String hashAlgorithmName = Md5Hash.ALGORITHM_NAME;
        String credentials = "123456";
        int hashIterations = 1024;
        String salt = RandomStringUtils.randomAscii(15) + "_albrus";
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleHash hash = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);

        System.out.println(salt);
        System.out.println(hash.toString().toUpperCase());
    }
}
