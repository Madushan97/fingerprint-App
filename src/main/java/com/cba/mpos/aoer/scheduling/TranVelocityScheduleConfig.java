package com.cba.mpos.aoer.scheduling;

import com.cba.mpos.aoer.model.internal.TaskConfig;
import com.cba.mpos.aoer.repository.internal.TaskConfigRepository;
import com.cba.mpos.aoer.service.AppOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class TranVelocityScheduleConfig implements SchedulingConfigurer {

    @Autowired
    AppOperationService appOperationService;

    private final Logger LOGGER = LoggerFactory.getLogger(TranVelocityScheduleConfig.class);

    private final TaskConfigRepository taskConfigRepository;

    public TranVelocityScheduleConfig(TaskConfigRepository taskConfigRepository) {
        this.taskConfigRepository = taskConfigRepository;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            try {
                appOperationService.checkTranVelocityAndNotify();
            } catch (Exception e) {
                LOGGER.error("Error occurred while running checkTranVelocityAndNotify().", e);
            }
        }, triggerContext -> {
            TaskConfig taskConfig = taskConfigRepository.findByAction("TRAN_VELOCITY");
            CronTrigger trigger = new CronTrigger(taskConfig.getCron().trim());
            return trigger.nextExecutionTime(triggerContext);
        });
    }
}