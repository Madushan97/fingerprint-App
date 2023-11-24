package com.cba.mpos.aoer.repository.external;

import com.cba.mpos.aoer.model.external.Terminals;
import com.cba.mpos.aoer.repository.common.ReadOnlyRepository;

import java.util.List;

public interface TerminalRepository extends ReadOnlyRepository<Terminals, Integer> {

    List<Terminals> findByDeletedRec(Integer deletedRec);

    List<Terminals> findByDeletedRecOrderByMerchantsAsc(Integer deletedRec);

}
