package com.book.es.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.book.es.logging.LoggingUtil.addingLogContext;

public abstract class Logging {
    public static final String LOGGER_TYPE_NAME = "name";

    public static final String LOGGER_TYPE_CLASS = "class";

    Logger logger = null;

    /**
     * 支持两种，使用类路径名和使用常量字符串
     *
     * 而针对支持动态文件后缀的方式，则使用log4j2 的ROUTINGKEY方式来达到
     *
     */
    private Logger getLogger() {

        //1. 如果是静态态的，看是否logger不为空，如果不为空直接返回
        if(logger!=null){
            return logger;
        }

        //2. 如果不是静态态的，返回对应的logger type
        if(LOGGER_TYPE_CLASS.equalsIgnoreCase(getLogType())){
            return LogManager.getLogger((Class)getLoggerIdentify());
        }else if(LOGGER_TYPE_NAME.equalsIgnoreCase(getLogType())){
            return LogManager.getLogger((String)getLoggerIdentify());
        }else{
            return LogManager.getLogger();
        }
    }

    protected void info(String msg) {
        getLogger().info(addingLogContext(msg));
    }

    protected void info(String msg, Object... objs) {
        getLogger().info(addingLogContext(msg), objs);
    }

    protected void infoWithLogger(Logger spcifiedLogger, String msg, Object... objs) {
        spcifiedLogger.info(addingLogContext(msg), objs);
    }

    protected void warn(String msg) {
        getLogger().warn(() -> addingLogContext(msg));
    }

    protected void warn(String msg, Object... objs) {
        getLogger().warn(addingLogContext(msg), objs);
    }

    protected void warnWithLogger(Logger specifiedLogger, String msg, Object... objs) {
        specifiedLogger.warn(addingLogContext(msg), objs);
    }

    protected void error(String err) {
        getLogger().error(() -> addingLogContext(err));
    }

    protected void error(String err, Object... objs) {
        getLogger().error(addingLogContext(err), objs);
    }

    protected void errorWithLogger(Logger specifiedLogger, String err, Object... objs) {
        specifiedLogger.error(addingLogContext(err), objs);
    }

    protected void debug(String debug) {
        getLogger().debug(() -> addingLogContext(debug));
    }

    protected void debug(String debug, Object... objs) {
        getLogger().debug(addingLogContext(debug), objs);
    }

    protected void debugWithLogger(Logger specifiedLogger, String debug, Object... objs) {
        specifiedLogger.debug(addingLogContext(debug), objs);
    }

    protected String getLogType() {
        return LOGGER_TYPE_CLASS;
    }

    protected Object getLoggerIdentify() {
        return this.getClass();
    }


}
