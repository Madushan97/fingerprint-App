package com.cba.mpos.aoer.repository.internal;

import com.cba.mpos.aoer.model.internal.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity, Long> {

}
