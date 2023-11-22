package com.example.locationmasterdatalookup.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
@Slf4j
public class GlobalErrorAttributes extends DefaultErrorAttributes{
    private HttpStatus errorstatus = HttpStatus.BAD_REQUEST;
    private String erromessage = "Bad Request, please check input parameters";

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        if (getError(request) instanceof BadRequestException) {
            map.put("status", getErrorstatus());
            map.put("message", getErromessage());
        } else {
            map.put("status", HttpStatus.UNAUTHORIZED.value());
            map.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return map;
    }

    public HttpStatus getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(HttpStatus errorstatus) {
        this.errorstatus = errorstatus;
    }

    public String getErromessage() {
        return erromessage;
    }

    public void setErromessage(String erromessage) {
        this.erromessage = erromessage;
    }
}
