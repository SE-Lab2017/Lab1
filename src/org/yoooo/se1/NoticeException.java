package org.yoooo.se1;

public class NoticeException extends RuntimeException {
    public NoticeException() {
    }

    public NoticeException(final String message) {
        super(message);
    }

    public NoticeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NoticeException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoticeException(final Throwable cause) {
        super(cause);
    }
}
