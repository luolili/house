package com.luo.house.common.page;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class Pagination {
    private Integer pageNum;
    private Integer pageSize;
    private Long totalCount;
    private List<Integer> pages = Lists.newArrayList();

    public Pagination(Integer pageNum, Integer pageSize, Long totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        for (int i = 0; i < pageNum; i++) {
            pages.add(i);
        }
        Long pageCount = totalCount / pageNum + (totalCount % pageNum == 0 ? 0 : 1);
        if (pageCount > pageNum) {
            for (int i = pageNum + 1; i < pageCount; i++) {
                pages.add(i);
            }
        }
    }
}
