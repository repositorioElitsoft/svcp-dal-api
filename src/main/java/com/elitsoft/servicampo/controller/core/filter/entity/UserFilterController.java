package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.domain.entity.User;
import com.elitsoft.servicampo.filter.UserCriteria;
import com.elitsoft.servicampo.service.core.filter.entity.UserFilterService;
import com.elitsoft.servicampo.utils.PaginationUtils;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.elitsoft.servicampo.utils.PagedResponse;

import java.util.List;

@RestController
@RequestMapping("/core/filter/")
public class UserFilterController {

    @Autowired
    private UserFilterService userFilterService;


    @GetMapping("/users")
    public ResponseEntity<PagedResponse<User>> getUsers(@ModelAttribute UserCriteria criteria, PagingAndSorting paging) {
        List<User> users = userFilterService.getUsers(criteria, paging);
        int totalUsers = userFilterService.getTotalUsers(criteria);


        PagedResponse<User> response = PaginationUtils.createPagedResponse(users, totalUsers, paging);
        return ResponseEntity.ok(response);
    }
}
