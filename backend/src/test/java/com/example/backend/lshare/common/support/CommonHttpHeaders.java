package com.example.backend.lshare.common.support;

public class CommonHttpHeaders {

    private static final String HOST = "lnshare";
    private static final String REFERER = "Referer";
    private static final String SERVER = "";
    private static final String CONNECTION = "keep-alive";
    private static final String ACCEPT_LANGUAGE = "en-US,en;q=0.9,ko;q=0.8";
    private static final String DATE = "";
    private static final String TRANSFER_ENCODING = "chunked";

    public String getHost() {
        return HOST;
    }

    public String getReferer() {
        return REFERER;
    }

    public String getServer() {
        return SERVER;
    }

    public String getConnection() {
        return CONNECTION;
    }

    public String getAcceptLanguage() {
        return ACCEPT_LANGUAGE;
    }

    public String getTransferEncoding() {
        return TRANSFER_ENCODING;
    }

    public static void main(String[] args) throws Exception {

    }
}
