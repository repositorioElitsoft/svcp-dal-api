package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.entity.User;
import com.elitsoft.servicampo.filter.UserCriteria;
import com.elitsoft.servicampo.mapper.UserMapper;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFilterService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers(UserCriteria criteria, PagingAndSorting paging) {
        int offset = paging.getPageNumber() * paging.getPageSize();
        return userMapper.selectUsers(criteria, paging.getSortField(), paging.getSortDirection(), paging.getPageSize(), offset);
    }

    public int getTotalUsers(UserCriteria criteria) {
        return userMapper.countUsers(criteria);
    }
}