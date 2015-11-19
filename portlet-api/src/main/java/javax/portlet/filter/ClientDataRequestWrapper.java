/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package javax.portlet.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.portlet.ClientDataRequest;

/**
 * <div class="changed_added_3_0">
 * The <code>ClientDataRequestWrapper</code> provides a convenient 
 * implementation of the <code>ClientDataRequest</code> interface 
 * that can be subclassed by developers wishing to adapt the request.
 * This class implements the Wrapper or Decorator pattern. 
 * Methods default to calling through to the wrapped request object.
 * </div>
 */
public class ClientDataRequestWrapper extends PortletRequestWrapper implements
ClientDataRequest {

   ClientDataRequest request;

   /**
    * @param request
    */
   public ClientDataRequestWrapper(ClientDataRequest request) {
      super(request);
      this.request = request;
   }

   /**
    * Return the wrapped request object.
    * 
    * @return the wrapped request
    */
   public ClientDataRequest getRequest() {
      return request;
   }

   /**
    * Sets the request object being wrapped.
    * 
    * @param request the request to set
    * @throws java.lang.IllegalArgumentException   if the request is null.
    */
   public void setRequest(ClientDataRequest request) {
      if ( request == null) {
         throw new java.lang.IllegalArgumentException("Request is null");
      }
      super.setRequest(request);
      this.request = request;
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#getPortletInputStream()
    */
   public InputStream getPortletInputStream() throws IOException {
      return request.getPortletInputStream();
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#setCharacterEncoding(java.lang.String)
    */
   public void setCharacterEncoding(String enc)
         throws UnsupportedEncodingException {
      request.setCharacterEncoding(enc);
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#getReader()
    */
   public BufferedReader getReader() throws UnsupportedEncodingException,
   IOException {
      return request.getReader();
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#getCharacterEncoding()
    */
   public String getCharacterEncoding() {
      return request.getCharacterEncoding();
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#getContentType()
    */
   public String getContentType() {
      return request.getContentType();
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#getContentLength()
    */
   public int getContentLength() {
      return request.getContentLength();
   }

   /* (non-Javadoc)
    * @see javax.portlet.ClientDataRequest#getMethod()
    */
   public String getMethod() {
      return request.getMethod();
   }

}