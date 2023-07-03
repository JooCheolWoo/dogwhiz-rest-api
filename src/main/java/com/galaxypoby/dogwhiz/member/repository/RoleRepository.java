package com.galaxypoby.dogwhiz.member.repository;

import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.code.TypeCode;
import com.galaxypoby.dogwhiz.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTypeCodeAndStatusCode(TypeCode typeCode, StatusCode statusCode);
}
