package com.redmart.skiing.exception;

/**
 * @author albertojg
 * @version 1.0
 */
public class UnparsableInputFileException extends Exception
{
    public UnparsableInputFileException(String msg)
    {
        super(msg);
    }

    public UnparsableInputFileException(String msg, Throwable t)
    {
        super(msg, t);
    }
}
