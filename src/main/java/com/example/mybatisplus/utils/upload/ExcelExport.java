package com.example.mybatisplus.utils.upload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导入工具
 *
 * @author oyp.
 * @date 2019-09-19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface ExcelExport {
    String columnName() default "";
    int cloumnWidth() default 100;
}
