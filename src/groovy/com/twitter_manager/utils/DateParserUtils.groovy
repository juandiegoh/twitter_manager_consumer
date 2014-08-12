package com.twitter_manager.utils

import java.text.SimpleDateFormat

class DateParserUtils {

    static def Date parseDateWithPattern(String date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern)
        sf.setLenient(true)
        return sf.parse(date)
    }
}
