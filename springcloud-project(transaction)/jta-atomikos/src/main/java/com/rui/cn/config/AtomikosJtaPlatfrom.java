package com.rui.cn.config;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Atomikos 事务管理器
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
public class AtomikosJtaPlatfrom extends AbstractJtaPlatform {
    private static TransactionManager transactionManager;
    private static UserTransaction userTransaction;

    public static void setTransactionManager(TransactionManager transactionManager) {
        AtomikosJtaPlatfrom.transactionManager = transactionManager;
    }

    public static void setUserTransaction(UserTransaction userTransaction) {
        AtomikosJtaPlatfrom.userTransaction = userTransaction;
    }

    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return userTransaction;
    }
}
