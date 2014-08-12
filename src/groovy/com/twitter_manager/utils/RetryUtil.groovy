package com.twitter_manager.utils

class RetryUtil {
    static retry(int times, long sleepTime = 0, Closure c) {
        Throwable caughtThrowable = null

        for (int i = 0; i < times; i++) {
            try {
                return c.call(i)
            } catch (Throwable t) {
                caughtThrowable = t

                def e = new Exception("Failed to call closure. ${i + 1} of $times runs.", caughtThrowable)

                Thread.sleep(sleepTime)
            }
        }

        def e = new Exception("Finally failed to call closure after $times tries.", caughtThrowable)
        throw caughtThrowable
    }
}
