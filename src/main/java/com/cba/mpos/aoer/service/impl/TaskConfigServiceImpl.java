package com.cba.mpos.aoer.service.impl;

import com.cba.mpos.aoer.exception.NotFoundException;
import com.cba.mpos.aoer.model.internal.TaskConfig;
import com.cba.mpos.aoer.repository.internal.TaskConfigRepository;
import com.cba.mpos.aoer.service.TaskConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskConfigServiceImpl implements TaskConfigService {

    private final Logger LOGGER = LoggerFactory.getLogger(TaskConfigServiceImpl.class);

    private final TaskConfigRepository taskConfigRepository;

    public TaskConfigServiceImpl(TaskConfigRepository taskConfigRepository) {
        this.taskConfigRepository = taskConfigRepository;
    }

    @Override
    public List<TaskConfig> findAll() throws Exception {
        return taskConfigRepository.findAll();
    }

    @Override
    public TaskConfig findById(Integer id) throws Exception {
        return taskConfigRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found for ID: " + id));
    }

    @Override
    public TaskConfig create(TaskConfig taskConfig) throws Exception {
        return taskConfigRepository.saveAndFlush(taskConfig);
    }

    @Override
    public TaskConfig update(Integer id, TaskConfig taskConfig) throws Exception {
        TaskConfig existing = this.findById(id);
        taskConfig.setId(existing.getId());
        return taskConfigRepository.saveAndFlush(taskConfig);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        this.findById(id);
        taskConfigRepository.deleteById(id);
    }
}
