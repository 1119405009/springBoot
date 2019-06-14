package com.felix.taskquartz.util;


import com.felix.taskquartz.job.base.BaseJob;

public class JobUtil {
    /**
     * 根据全类名获取Job实例
     *
     * @param classname Job全类名
     * @return {@link BaseJob} 实例
     * @throws Exception 泛型获取异常
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("com.felix.taskquartz.job.HelloJob");
        System.out.println(clazz.newInstance());
    }
}
