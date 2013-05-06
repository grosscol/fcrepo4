package org.fcrepo.exceptionhandlers;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class WildcardExceptionMapperTest {

    private WildcardExceptionMapper testObj;
    
    @Before
    public void setUp() {
        testObj = new WildcardExceptionMapper();
    }
    
    @Test
    public void testToResponse() {
        Exception input = new Exception();
        Response actual = testObj.toResponse(input);
        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), actual.getStatus());
        assertTrue(actual.getEntity() != null);
        testObj.showStackTrace = false;
        actual = testObj.toResponse(input);
        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), actual.getStatus());
        assertEquals(null, actual.getEntity());
    }
}