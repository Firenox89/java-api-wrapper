package com.soundcloud.api;

import org.apache.http.HttpHost;

/**
 * The environment to operate against.
 * Use SANDBOX for testing your app, and LIVE for production applications.
 */
public enum Env {
    /** The main production site, http://soundcloud.com */
    LIVE("api.soundcloud.com", "soundcloud.com"),
    /** For testing, http://sandbox-soundcloud.com */
    SANDBOX("api.sandbox-soundcloud.com", "sandbox-soundcloud.com");

    public final HttpHost resourceHost, sslResourceHost, authResourceHost, sslAuthResourceHost;

    /**
     * @param resourceHost          the resource host
     * @param authResourceHost      the authentication resource host
     */
    Env(String resourceHost, String authResourceHost) {
        this.resourceHost = new HttpHost(resourceHost, 80, "http");
        sslResourceHost = new HttpHost(resourceHost, 443, "https");

        this.authResourceHost = new HttpHost(authResourceHost, 80, "http");
        sslAuthResourceHost = new HttpHost(authResourceHost, 443, "https");
    }

    public HttpHost getResourceHost(boolean secure) {
        return secure ? sslResourceHost : resourceHost;
    }

    public HttpHost getAuthResourceHost(boolean secure) {
        return secure ? sslAuthResourceHost : authResourceHost;
    }

    public boolean isApiHost(HttpHost host) {
        return ("http".equals(host.getSchemeName()) ||
               "https".equals(host.getSchemeName())) &&
                resourceHost.getHostName().equals(host.getHostName());
    }
}
