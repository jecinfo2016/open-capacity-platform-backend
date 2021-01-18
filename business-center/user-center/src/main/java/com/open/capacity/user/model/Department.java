package com.open.capacity.user.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Department {

        private long id;

        private String deptName;

        private List<Department> children = new ArrayList<>();

        public void add(Department sysDept) {
            children.add(sysDept);
        }

}
