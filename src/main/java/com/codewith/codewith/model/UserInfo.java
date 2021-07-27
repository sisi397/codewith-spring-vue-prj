package com.codewith.codewith.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String name;

    @Override
    public String toString(){
        return "{\nuserId:"+this.userId+"\nname:"+this.name+"\n}";
    }

}
