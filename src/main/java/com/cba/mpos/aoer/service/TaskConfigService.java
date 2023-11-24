package com.cba.mpos.aoer.service;

import com.cba.mpos.aoer.model.internal.TaskConfig;

import java.util.List;

public interface TaskConfigService {

    List<TaskConfig> findAll() throws Exception;

    TaskConfig findById(Integer id) throws Exception;

    TaskConfig create(TaskConfig taskConfig) throws Exception;

    TaskConfig update(Integer id, TaskConfig taskConfig) throws Exception;

    void deleteById(Integer id) throws Exception;
}
