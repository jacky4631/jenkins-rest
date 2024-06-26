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

package com.cdancy.jenkins.rest.parsers;

import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.google.common.base.Function;
import org.jclouds.http.HttpResponse;

import jakarta.inject.Singleton;

/**
 * Turn a valid response, but one that has no body, into a RequestStatus.
 */
@Singleton
public class RequestStatusParser implements Function<HttpResponse, RequestStatus> {

    @Override
    public RequestStatus apply(final HttpResponse input) {
        if (input == null) {
            throw new RuntimeException("Unexpected NULL HttpResponse object");
        }

        final int statusCode = input.getStatusCode();
        if (statusCode >= 200 && statusCode < 400) {
            return RequestStatus.create(true, null);
        } else {
            throw new RuntimeException(input.getStatusLine());
        }
    }
}
