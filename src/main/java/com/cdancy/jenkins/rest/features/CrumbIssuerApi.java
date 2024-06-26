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

package com.cdancy.jenkins.rest.features;

import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import com.cdancy.jenkins.rest.fallbacks.JenkinsFallbacks;
import com.cdancy.jenkins.rest.filters.JenkinsNoCrumbAuthenticationFilter;
import com.cdancy.jenkins.rest.parsers.CrumbParser;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.QueryParams;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.ResponseParser;

import jakarta.inject.Named;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@RequestFilters(JenkinsNoCrumbAuthenticationFilter.class)
@Path("/crumbIssuer/api/xml")
public interface CrumbIssuerApi {

    @Named("crumb-issuer:crumb")
    @Fallback(JenkinsFallbacks.CrumbOnError.class)
    @ResponseParser(CrumbParser.class)
    @QueryParams(keys = { "xpath" }, values = { "concat(//crumbRequestField,\":\",//crumb)" })
    @Consumes(MediaType.TEXT_PLAIN)
    @GET
    Crumb crumb();
}
