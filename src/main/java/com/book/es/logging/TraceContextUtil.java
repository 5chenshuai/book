/**
 Alipay.com Inc.
 Copyright (c) 2004-2017 All Rights Reserved. */
package com.book.es.logging;

import org.apache.commons.lang.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qchen006
 * @version $Id: TraceContextUtil, v0.1 2017-12-05 16:34 bettercp3 Exp $
 */
public class TraceContextUtil {

    private static final String ROUTINGKEY_COINTYPE = "ROUTINGKEY_COINTYPE";

    public static TraceContext cloneLogContext() {
        TraceContext abstractLogContext = TraceContext.get();

        return abstractLogContext == null ? null : abstractLogContext.cloneContext();
    }

    public static String generateNewTraceId(){
        String prefix = RandomStringUtils.random(6,true,true);
        String time =  String.valueOf(System.currentTimeMillis()/1000);

        return prefix+time;
    }

    public static void putRoutingKeyValue(String key, String value){
        TraceContext.get().putRoutingLogItem(key,value);
    }

    public static void putRoutingCoinType(String value){
        TraceContext.get().putRoutingLogItem(ROUTINGKEY_COINTYPE,value);
    }

    public static Map<String,String> getLogRoutingMap(){
        return TraceContext.get().getRoutingHolder();
    }

    public static void updateTraceId(String traceId){
        TraceContext.get().putTraceId(traceId);
    }

//    public static void updateCoinType(String coinType){
//        TraceContext.get().putCoinType(coinType);
//    }

    public static String getTraceId() {
        return TraceContext.get().getTraceId();
    }

//    public static String getCoinType() {
//        return TraceContext.get().getCoinType();
//    }

    public static void updateUserId(String userId){
        TraceContext.get().putUserId(userId);
    }

    public static String getUserId(){
        return TraceContext.get().getUserId();
    }


    static class TraceContext{
        private static final String LOG_CTX_KEY_TRACEID = "traceId";
//        private static final String LOG_CTX_KEY_COINTYPE = "coinType";

        private static final String LOG_CTX_KEY_USER_ID = "userId";
        private static final String LOG_CTX_KEY_USER_NAME = "userName";

        /**
         * 文件之中，一条记录的记录上下文信息
         */
        private Map<String,String> singleLoggingHolder = new HashMap<>();

        /**
         * 区分不同文件的routing key
         */
        private Map<String,String> routingHolder = new HashMap<>();

        private static ThreadLocal<TraceContext> loggingContext = new ThreadLocal<>();

        public static TraceContext get(){
            TraceContext ctx =  loggingContext.get();
            if(ctx == null){
                ctx = new TraceContext();
                loggingContext.set(ctx);
            }
            return ctx;
        }

        public static void set(TraceContext context){
            loggingContext.set(context);
        }


        public String getTraceId(){
            return  singleLoggingHolder.get(LOG_CTX_KEY_TRACEID);
        }

//        public void putCoinType(String coinType){
//            singleLoggingHolder.put(LOG_CTX_KEY_COINTYPE,  coinType);
//        }
//
//        public String getCoinType(){
//            return singleLoggingHolder.get(LOG_CTX_KEY_COINTYPE);
//        }

        public TraceContext cloneContext() {
            TraceContext context = new TraceContext();
            Map<String, String> attr = new HashMap<>();
            attr.putAll(this.singleLoggingHolder);

            context.singleLoggingHolder = attr;
            return context;
        }

        public void putTraceId(String traceId) {
            this.singleLoggingHolder.put(LOG_CTX_KEY_TRACEID,traceId);
        }

        public void putUserId(String userId){
            this.singleLoggingHolder.put(LOG_CTX_KEY_USER_ID, userId);
        }

        public String getUserId(){
            return this.singleLoggingHolder.get(LOG_CTX_KEY_USER_ID);
        }

        public void putSingleLogItem(String key, String value) {
            this.singleLoggingHolder.put(key,value);
        }

        public Map<String, String> getRoutingHolder(){
            return routingHolder;
        }

        public void putRoutingLogItem(String key, String value) {
            this.routingHolder.put(key,value);
        }
    }
}
