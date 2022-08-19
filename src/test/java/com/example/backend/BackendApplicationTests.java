package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    public void testPasswordEncoder(){
        String username = "username";
        String encode = passwordEncoder.encode(username);
        //$2a$10$dzf/ce8eetd2MJTxAgs4su6Qc3s3ghsek6Oq0LCR4G4Ety0QQcN.6
        //$2a$10$/tZEexHo/7e06yGmZPdG2eK3R1CC595DRC7hZg86rT8Dj34XPtOfS
        //username + salt(random)
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(username, encode);
        System.out.println("matches = " + matches);
    }


}
