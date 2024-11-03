package org.swallow.swallow_data_analysis.storage;

public class NotFoundSwallowTableException extends RuntimeException {
    public NotFoundSwallowTableException(String message) {
        super(message);
    }
}
