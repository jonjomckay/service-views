package com.boomi.flow.services.views.views;

import com.google.common.collect.Lists;
import com.manywho.sdk.api.InvokeType;
import com.manywho.sdk.api.run.elements.config.UiServiceRequest;
import com.manywho.sdk.api.run.elements.config.UiServiceResponse;
import com.manywho.sdk.api.run.elements.ui.PageComponentDataResponse;
import com.manywho.sdk.api.run.elements.ui.PageComponentResponse;
import com.manywho.sdk.api.run.elements.ui.PageContainerDataResponse;
import com.manywho.sdk.api.run.elements.ui.PageContainerResponse;
import com.manywho.sdk.api.run.elements.ui.PageResponse;
import com.manywho.sdk.services.controllers.ViewController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/view")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DefaultViewController implements ViewController {

    @Override
    @Path("/{name: .+}")
    @POST
    public UiServiceResponse view(@PathParam("name") String name, UiServiceRequest request) throws Exception {
        PageContainerResponse pageContainerResponse = PageContainerResponse.builder()
                .containerType("VERTICAL_FLOW")
                .developerName("Step")
                .id(UUID.fromString("b61810ed-4bfa-4a50-81ad-609b8da34583"))
                .build();

        PageContainerDataResponse pageContainerDataResponse = PageContainerDataResponse.builder()
                .editable(true)
                .enabled(true)
                .visible(true)
                .pageContainerId(UUID.fromString("b61810ed-4bfa-4a50-81ad-609b8da34583"))
                .build();

        PageComponentResponse pageComponentResponse = PageComponentResponse.builder()
                .componentType("INPUT")
                .developerName("Step Content")
                .id(UUID.fromString("f160e6a2-fe53-4f48-a2a0-220cfb2e7ff8"))
                .maxSize(50)
                .pageContainerDeveloperName("Step")
                .pageContainerId(UUID.fromString("b61810ed-4bfa-4a50-81ad-609b8da34583"))
                .build();

        PageComponentDataResponse pageComponentDataResponse = PageComponentDataResponse.builder()
                .editable(true)
                .enabled(true)
                .required(true)
                .valid(true)
                .visible(true)
                .pageComponentId(UUID.fromString("f160e6a2-fe53-4f48-a2a0-220cfb2e7ff8"))
                .build();

        PageResponse pageResponse = PageResponse.builder()
                .pageComponentDataResponses(Lists.newArrayList(pageComponentDataResponse))
                .pageComponentResponses(Lists.newArrayList(pageComponentResponse))
                .pageContainerDataResponses(Lists.newArrayList(pageContainerDataResponse))
                .pageContainerResponses(Lists.newArrayList(pageContainerResponse))
                .build();

        UiServiceResponse uiServiceResponse = new UiServiceResponse(request.getTenantId(), InvokeType.Forward, request.getToken(), pageResponse);
        uiServiceResponse.setOutcomeResponses(request.getOutcomes());
        uiServiceResponse.setSelectedOutcomeId(request.getSelectedOutcomeId());

        if (request.getSelectedOutcomeId() != null) {
            uiServiceResponse.setComplete(true);
        }

        return uiServiceResponse;
    }
}