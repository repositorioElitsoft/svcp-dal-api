package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.domain.entity.User;
import com.elitsoft.servicampo.filtro.UserCriteria; // You might have a separate mobile UserCriteria
import com.elitsoft.servicampo.mapper.UserMapper; // Or a MobileUserMapper if needed
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFilterMobileService {

    @Autowired
    private UserMapper userMapper; // Or MobileUserMapper

    public List<User> getUsers(UserCriteria criteria, PagingAndSorting paging) {
        int offset = paging.getPageNumber() * paging.getPageSize();
        return userMapper.selectUsers(criteria, paging.getSortField(), paging.getSortDirection(), paging.getPageSize(), offset); // Same mapper can be reused
    }

    public int getTotalUsers(UserCriteria criteria) {
        return userMapper.countUsers(criteria);
    }
}