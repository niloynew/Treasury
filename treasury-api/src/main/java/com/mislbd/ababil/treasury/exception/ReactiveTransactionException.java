package com.mislbd.ababil.treasury.exception;

import com.mislbd.asset.commons.central.ExtendedRuntimeException;

public class ReactiveTransactionException extends ExtendedRuntimeException {

    public ReactiveTransactionException() {
        super(
                Error.REACTIVE_TRANSACTION_EXCEPTION.getModule(),
                Error.REACTIVE_TRANSACTION_EXCEPTION.getCode(),
                Error.REACTIVE_TRANSACTION_EXCEPTION.getMessages());
    }

    public ReactiveTransactionException(String message) {
        super(Error.REACTIVE_TRANSACTION_EXCEPTION.getModule(), Error.REACTIVE_TRANSACTION_EXCEPTION.getCode(), message);
    }
}