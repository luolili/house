package com.luo.house.common.page;


import java.util.List;

public class PageData<T> {
    List<T> list;

    Pagination pagination;

    public PageData(List<T> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public static <T> PageData<T> build(List<T> list, long count, int pageSize, int pageNum) {
        Pagination pagination = new Pagination(pageNum, pageSize, count);
        return new PageData<>(list, pagination);

    }
}
