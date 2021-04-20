package com.javaweb.auth.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> responseEntity = null;
        try {
            responseEntity = super.translate(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        OAuth2Exception body = responseEntity.getBody();
        if (e instanceof InvalidGrantException) {
            body.addAdditionalInformation("code", "501");
            body.addAdditionalInformation("msg", "用户名或密码不正确");
        } else {
            body.addAdditionalInformation("code", "401");
            body.addAdditionalInformation("msg", body.getLocalizedMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(responseEntity.getHeaders().toSingleValueMap());
        return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
    }
}
