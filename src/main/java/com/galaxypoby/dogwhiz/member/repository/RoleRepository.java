package com.galaxypoby.dogwhiz.member.repository;

import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.code.RoleCode;
import com.galaxypoby.dogwhiz.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTypeCodeAndStatusCode(RoleCode roleCode, StatusCode statusCode);
}
