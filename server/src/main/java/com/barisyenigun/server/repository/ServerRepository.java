package com.barisyenigun.server.repository;

import com.barisyenigun.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server,Long>{
    Server findByIpAddress(String ipAddress);

}
