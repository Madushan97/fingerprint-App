package com.cba.mpos.aoer.repository.external;

import com.cba.mpos.aoer.model.external.Devices;
import com.cba.mpos.aoer.repository.common.ReadOnlyRepository;

import java.util.List;

public interface DeviceRepository extends ReadOnlyRepository<Devices, Integer> {

    List<Devices> findByDeletedRec(Integer deletedRec);

    Devices findBySerialNoAndDeletedRec(String serialNo, Integer deletedRec);
}
