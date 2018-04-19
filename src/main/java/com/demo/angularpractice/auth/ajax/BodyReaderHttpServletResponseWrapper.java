package com.demo.angularpractice.auth.ajax;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * liyan-下午7:37
 *
 * @author liyan
 **/
public class BodyReaderHttpServletResponseWrapper extends HttpServletResponseWrapper {


    public BodyReaderHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
