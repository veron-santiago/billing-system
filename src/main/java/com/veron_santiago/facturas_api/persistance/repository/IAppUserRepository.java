package com.veron_santiago.facturas_api.persistance.repository;

import com.veron_santiago.facturas_api.persistance.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
}
