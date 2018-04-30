/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.events;

import org.activiti.cloud.services.api.model.Task;

/**
 * Task cancelled event implementation
 */
public class TaskCancelledEventImpl extends AbstractProcessEngineEvent {

    private Task task;

    public TaskCancelledEventImpl() {
    }

    public TaskCancelledEventImpl(String applicationName,
                                  String executionId,
                                  String processDefinitionId,
                                  String processInstanceId,
                                  Task task) {
        super(applicationName,
              executionId,
              processDefinitionId,
              processInstanceId);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public String getEventType() {
        return "TaskCancelledEvent";
    }
}