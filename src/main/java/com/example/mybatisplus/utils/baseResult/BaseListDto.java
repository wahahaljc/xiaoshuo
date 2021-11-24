package com.example.mybatisplus.utils.baseResult;


import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 封装list结果集以返回数据总条数。
 *
 * @author oyp
 * @date 2019-09-17
 */
public class BaseListDto<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据列表
     */
    private List<E> rows;

    /**
     * 总条数
     */
    private Long total;


    public BaseListDto(List<E> list) {
        this.rows = list;
        if (list instanceof Page) {
            total = ((Page) list).getTotal();
        }else{
            total= Long.valueOf(list.size());
        }
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
