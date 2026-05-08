package com.augmind.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Access access = new Access();

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public static class Access {
        private String codeHash;

        public String getCodeHash() {
            return codeHash;
        }

        public void setCodeHash(String codeHash) {
            this.codeHash = codeHash;
        }
    }
}
