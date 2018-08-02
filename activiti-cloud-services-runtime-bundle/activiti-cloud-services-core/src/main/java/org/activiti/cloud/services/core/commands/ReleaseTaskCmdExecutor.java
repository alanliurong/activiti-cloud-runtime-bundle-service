package org.activiti.cloud.services.core.commands;

import org.activiti.runtime.api.TaskRuntime;
import org.activiti.runtime.api.model.Task;
import org.activiti.runtime.api.model.payloads.ReleaseTaskPayload;
import org.activiti.runtime.api.model.results.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReleaseTaskCmdExecutor implements CommandExecutor<ReleaseTaskPayload> {

    private TaskRuntime taskRuntime;
    private MessageChannel commandResults;

    @Autowired
    public ReleaseTaskCmdExecutor(TaskRuntime taskRuntime,
                                  MessageChannel commandResults) {
        this.taskRuntime = taskRuntime;
        this.commandResults = commandResults;
    }

    @Override
    public String getHandledType() {
        return ReleaseTaskPayload.class.getName();
    }

    @Override
    public void execute(ReleaseTaskPayload releaseTaskPayload) {
        Task task = taskRuntime.release(releaseTaskPayload);
        TaskResult result = new TaskResult(releaseTaskPayload,
                                         task);
        commandResults.send(MessageBuilder.withPayload(result).build());
    }
}
