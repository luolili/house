package com.luo.house.common.page;

import lombok.Data;

@Data
public class PageParams {

    public static final Integer PAGE_SIZE = 2;
    private Integer pageSize;
    private Integer pageNum;
    private Integer offset;
    private Integer limit;

    public PageParams(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.limit = pageSize;
        this.offset = pageSize * (pageNum - 1);
    }

    public static PageParams build(Integer pageNum, Integer pageSize) {
        if (pageSize == null) {
            pageNum = PAGE_SIZE;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        return new PageParams(pageNum, pageSize);
    }

    public PageParams() {
        this(1, PAGE_SIZE);
    }
}
