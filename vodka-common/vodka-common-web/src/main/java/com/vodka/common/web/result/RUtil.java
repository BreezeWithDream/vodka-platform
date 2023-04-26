package com.vodka.common.web.result;


/**
 * @author Breeze
 * @date 2023/4/26 21:41
 * @description 快捷生成R对象的工具类
 */
public class RUtil {
    /**
     * 创建成功R返回对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R success(T data) {
        return new R(Codes.SUCCESS.getCode(), Codes.SUCCESS.getMsg(), data);
    }

    public static <T> R success() {
        return new R(Codes.SUCCESS.getCode(), Codes.SUCCESS.getMsg(), null);
    }

    /**
     * 创建指定枚举类型的R返回对象， 携带数据
     *
     * @param codes
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R create(Codes codes, T data) {
        return new R(codes.getCode(), codes.getMsg(), data);
    }

    /**
     * 创建指定枚举类型的R返回对象， 不携带数据
     *
     * @param codes
     * @param <T>
     * @return
     */
    public static <T> R create(Codes codes) {
        return new R(codes.getCode(), codes.getMsg(), null);
    }
}
