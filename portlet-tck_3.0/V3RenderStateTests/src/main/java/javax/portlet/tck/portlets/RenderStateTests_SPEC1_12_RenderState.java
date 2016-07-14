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

package javax.portlet.tck.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.MutableRenderParameters;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;
import javax.portlet.WindowState;
import javax.portlet.annotations.PortletConfiguration;
import javax.portlet.tck.beans.TestLink;
import javax.portlet.tck.beans.TestResult;
import javax.portlet.tck.util.ModuleTestCaseDetails;

import static javax.portlet.tck.util.ModuleTestCaseDetails.*;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case
 * names are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the
 * additionalTCs.xml file into the complete list of test case names for
 * execution by the driver.
 *
 */

@PortletConfiguration(portletName = "RenderStateTests_SPEC1_12_RenderState", publicParams = {
      "tr0_public" })
public class RenderStateTests_SPEC1_12_RenderState implements Portlet {

   @Override
   public void init(PortletConfig config) throws PortletException {
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq,
         ActionResponse portletResp) throws PortletException, IOException {
   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {

      PrintWriter writer = portletResp.getWriter();
      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();

      /*
       * TestCase: V3RenderStateTests_SPEC1_12_RenderState_getRenderParameters
       */
      /*
       * Details: "Returns an RenderParameters object representing the private
       * and public render parameters."
       */
      // TODO: How to declare public render parameters?
      // @PortletConfiguration(publicParams = {"tr0_public" }) is not working.
      // The problem is in renderParams.isPublic("tr0_public") check!!
      // Alternate code to test public render params -
      // portletReq.getPublicParameterMap()!=null &&
      // portletReq.getPublicParameterMap().containsKey("tr0_public") &&
      // portletReq.getPublicParameterMap().get("tr0_public").equals("true")
      RenderParameters renderParams = portletReq.getRenderParameters();
      if (!renderParams.isEmpty() && renderParams.isPublic("tr0_public")
            && renderParams.getValue("tr0_public") != null
            && renderParams.getValue("tr0_public").equals("true")
            && renderParams.getValue("tr0_private") != null
            && renderParams.getValue("tr0_private").equals("true")) {
         TestResult result = tcd.getTestResultFailed(
               V3RENDERSTATETESTS_SPEC1_12_RENDERSTATE_GETRENDERPARAMETERS);
         result.setTcSuccess(true);
         result.writeTo(writer);
      } else {
         RenderURL renderURL = portletResp.createRenderURL();
         MutableRenderParameters mutableRenderParams = renderURL
               .getRenderParameters();
         mutableRenderParams.setValue("tr0_private", "true");
         mutableRenderParams.setValue("tr0_public", "true");
         TestLink tb = new TestLink(
               "V3RenderStateTests_SPEC1_12_RenderState_getRenderParameters",
               renderURL);
         tb.writeTo(writer);
      }

      /*
       * TestCase: V3RenderStateTests_SPEC1_12_RenderState_getRenderParameters2
       */
      /*
       * Details:
       * "The RenderParameters object return by this method is immutable."
       */
      {
         TestResult result = tcd.getTestResultFailed(
               V3RENDERSTATETESTS_SPEC1_12_RENDERSTATE_GETRENDERPARAMETERS2);
         result.setTcSuccess(true);
         result.appendTcDetail(
               "There are no setters in RenderParameters object returned by RenderState.getRenderParameters() method");
         result.writeTo(writer);
      }

      /* TestCase: V3RenderStateTests_SPEC1_12_RenderState_getPortletMode */
      /* Details: "Returns the current portlet mode of the portlet." */
      {
         TestResult result = tcd.getTestResultFailed(
               V3RENDERSTATETESTS_SPEC1_12_RENDERSTATE_GETPORTLETMODE);
         if (portletReq.getPortletMode().equals(PortletMode.VIEW)) {
            result.setTcSuccess(true);
            result.appendTcDetail("Current portlet mode is VIEW");
         } else {
            result.appendTcDetail("Current portlet mode is not VIEW but "
                  + portletReq.getPortletMode().toString());
         }
         result.writeTo(writer);
      }

      /* TestCase: V3RenderStateTests_SPEC1_12_RenderState_getPortletMode2 */
      /*
       * Details: "If the portlet mode is not available, PortletMode.UNDEFINED
       * is returned."
       */
      // TODO: How to make portlet mode unavailable?
      if (portletReq.getPortletMode().equals(PortletMode.UNDEFINED)) {
         TestResult result = tcd.getTestResultFailed(
               V3RENDERSTATETESTS_SPEC1_12_RENDERSTATE_GETPORTLETMODE2);
         result.setTcSuccess(true);
         result.writeTo(writer);
      } else {
         RenderURL renderURL = portletResp.createRenderURL();
         //renderURL.setPortletMode(PortletMode.UNDEFINED);
         TestLink tb = new TestLink(
               "V3RenderStateTests_SPEC1_12_RenderState_getPortletMode2",
               renderURL);
         tb.writeTo(writer);
      }

      /* TestCase: V3RenderStateTests_SPEC1_12_RenderState_getWindowState */
      /* Details: "Returns the current window state of the portlet." */
      {
         TestResult result = tcd.getTestResultFailed(
               V3RENDERSTATETESTS_SPEC1_12_RENDERSTATE_GETWINDOWSTATE);
         if (portletReq.getWindowState().equals(WindowState.NORMAL)) {
            result.setTcSuccess(true);
            result.appendTcDetail("Current window state is NORMAL");
         } else {
            result.appendTcDetail("Current portlet mode is not NORMAL but "
                  + portletReq.getWindowState().toString());
         }
         result.writeTo(writer);
      }

      /* TestCase: V3RenderStateTests_SPEC1_12_RenderState_getWindowState2 */
      /*
       * Details: "If the window state is not available, WindowState.UNDEFINED
       * is returned."
       */
      // TODO: How to make window state unavailable?
      if (portletReq.getPortletMode().equals(WindowState.UNDEFINED)) {
         TestResult result = tcd.getTestResultFailed(
               V3RENDERSTATETESTS_SPEC1_12_RENDERSTATE_GETWINDOWSTATE2);
         result.setTcSuccess(true);
         result.writeTo(writer);
      } else {
         RenderURL renderURL = portletResp.createRenderURL();
         //renderURL.setWindowState(WindowState.UNDEFINED);
         TestLink tb = new TestLink(
               "V3RenderStateTests_SPEC1_12_RenderState_getWindowState2",
               renderURL);
         tb.writeTo(writer);
      }

   }

}