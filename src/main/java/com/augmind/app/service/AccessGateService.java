package com.augmind.app.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccessGateService {

    private final byte[] expectedHash;

    public AccessGateService(@Value("${app.access.code-hash}") String configuredHashHex) {
        this.expectedHash = fromHex(configuredHashHex);
    }

    public boolean isValidCode(String code) {
        byte[] actual = sha256(code == null ? "" : code.trim());
        return MessageDigest.isEqual(expectedHash, actual);
    }

    private byte[] sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 algorithm not available", ex);
        }
    }

    private byte[] fromHex(String hex) {
        if (hex == null) {
            throw new IllegalStateException("Access code hash is not configured");
        }
        String normalized = hex.trim();
        if (normalized.length() != 64) {
            throw new IllegalStateException("Access code hash must be a 64-char SHA-256 hex string");
        }

        byte[] data = new byte[32];
        for (int i = 0; i < data.length; i++) {
            int idx = i * 2;
            data[i] = (byte) Integer.parseInt(normalized.substring(idx, idx + 2), 16);
        }
        return data;
    }
}
