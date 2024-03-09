package com.bluesoft.servicebank.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DbUtil implements Serializable {

    private static final long serialVersionUID = -1L;

    public PageRequest composePageable(Integer page, Integer perPage, String sort) {

        PageRequest pageRequest;

        if (sort != null) {
            List<Sort.Order> orders = getOrders(sort);
            pageRequest = PageRequest.of(page, perPage, Sort.by(orders));
        } else if (page >= 0 && perPage > 0) {
            pageRequest = PageRequest.of(page, perPage);
        } else {
            pageRequest = null;
        }
        return pageRequest;

    }

    public List<Sort.Order> getOrders(String sort) {

        List<Sort.Order> orders = new ArrayList<>();

        if (!StringUtils.isEmpty(sort)) {
            String[] sortList = sort.split(",");

            Arrays.stream(sortList).parallel().forEach(v -> {
                if (v.charAt(0) == '-') {
                    orders.add(new Sort.Order(Sort.Direction.DESC, v.substring(1)));
                } else {
                    orders.add(new Sort.Order(Sort.Direction.ASC, v));
                }
            });
        }

        return orders;

    }
}
