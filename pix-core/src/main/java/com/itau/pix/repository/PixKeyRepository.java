package com.itau.pix.repository;

import com.itau.pix.entity.KeyType;
import com.itau.pix.entity.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKey, UUID>, JpaSpecificationExecutor<PixKey> {

    List<PixKey> findByAgencyAndAccount(int agency, int account);

}
