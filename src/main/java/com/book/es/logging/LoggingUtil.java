package com.book.es.logging;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.function.BiConsumer;

public class LoggingUtil {

    public static  void warnWithLogger(Logger specifiedLogger, String msg, Object... objs) {
        specifiedLogger.warn(addingLogContext(msg), objs);
    }

    public static  void infoWithLogger(Logger specifiedLogger, String msg, Object... objs) {
        specifiedLogger.info(addingLogContext(msg), objs);
    }
    public static  void errorWithLogger(Logger specifiedLogger, String msg, Object... objs) {
        specifiedLogger.error(addingLogContext(msg), objs);
    }

    public static String addingLogContext(String msg) {
        //1. traceId 信息，如果没有则会生成新的
        StringBuilder result = new StringBuilder();
        String traceId = TraceContextUtil.getTraceId();
        if(StringUtils.isEmpty(traceId)){
            traceId = TraceContextUtil.generateNewTraceId();
            TraceContextUtil.updateTraceId(traceId);
        }
        result.append("[");
        result.append(traceId);
        result.append("]");


        //2. coin type系列
//        String coinType = TraceContextUtil.getCoinType();
//        if (coinType == null) {
//            coinType = "";
//        }
//
//        result.append(StringUtils.rightPad(coinType, 4, '-'));
//        result.append(":");


        //3. user 信息
        if(!StringUtils.isEmpty(TraceContextUtil.getUserId())){
            result.append("[");
            result.append(TraceContextUtil.getUserId());
            result.append("]");
        }

        //4. 添加消息主题信息
        result.append(msg);


        //5. 添加 log4j2对应的 routing key信息
        TraceContextUtil.getLogRoutingMap().forEach(
                new BiConsumer<String, String>() {
                    @Override
                    public void accept(String key, String value) {
                        ThreadContext.put(key, value);
                    }
                }
        );

        return result.toString();
    }
}
