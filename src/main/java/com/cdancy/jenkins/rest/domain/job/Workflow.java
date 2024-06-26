/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cdancy.jenkins.rest.domain.job;

import com.google.auto.value.AutoValue;
import org.jclouds.json.SerializedNames;

import java.util.List;

@AutoValue
public abstract class Workflow {

   public abstract String name();

   public abstract String status();

   public abstract long startTimeMillis();

   public abstract long durationTimeMillis();

   public abstract List<Stage> stages();

   Workflow() {
   }

   @SerializedNames({ "name", "status", "startTimeMillis", "durationTimeMillis", "stages" })
   public static Workflow create(String name, String status, long startTimeMillis, long durationTimeMillis, List<Stage> stages) {
      return new AutoValue_Workflow(name, status, startTimeMillis, durationTimeMillis, stages);
   }
}
