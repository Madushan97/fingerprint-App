package com.cba.mpos.aoer.repository.external;

import com.cba.mpos.aoer.model.external.Merchants;
import com.cba.mpos.aoer.repository.common.ReadOnlyRepository;

public interface MerchantRepository extends ReadOnlyRepository<Merchants, Integer> {

    Merchants findByMerchantId(String merchantId);
}
