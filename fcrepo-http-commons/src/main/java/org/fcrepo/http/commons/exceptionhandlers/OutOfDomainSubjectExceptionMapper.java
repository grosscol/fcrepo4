/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.http.commons.exceptionhandlers;

import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.slf4j.LoggerFactory.getLogger;

import org.fcrepo.kernel.api.exception.OutOfDomainSubjectException;

import org.slf4j.Logger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 * @author whikloj
 * @since 2015-05-29
 */
@Provider
public class OutOfDomainSubjectExceptionMapper extends ConstraintExceptionMapper<OutOfDomainSubjectException>
    implements ExceptionDebugLogging {

    private static final Logger LOGGER =
            getLogger(OutOfDomainSubjectExceptionMapper.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(final OutOfDomainSubjectException e) {

        debugException(this, e, LOGGER);
        final Link link = buildConstraintLink(e, uriInfo);
        final String msg = e.getMessage();
        return status(BAD_REQUEST).entity(msg).links(link).build();
    }

}
