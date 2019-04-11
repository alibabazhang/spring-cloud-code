package com.rui.cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
//        if (authentication == null) {
//            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
//            if (clientDetails != null) {
//                //密码
//                String clientSecret = clientDetails.getClientSecret();
//                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
//            }
//        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                passwordEncoder.encode("123"),
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        AuthorityUtils.createAuthorityList("course_get_baseinfo", "course_get_list");
        return userDetails;
    }
}
