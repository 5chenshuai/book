package com.book.es.exception;

import com.book.es.web.WebResponse;

public class ValidatorException extends RuntimeException {

    private WebResponse webResponse;

    public ValidatorException(WebResponse webResponse) {
        this.webResponse = webResponse;
    }

    public WebResponse getWebResponse() {
        return webResponse;
    }

    public void setWebResponse(WebResponse webResponse) {
        this.webResponse = webResponse;
    }
}
