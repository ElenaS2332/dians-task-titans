package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.discovery.shared.resolver.EurekaEndpoint;
import com.netflix.discovery.shared.transport.EurekaHttpClient;
import com.netflix.discovery.shared.transport.EurekaHttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

public class NewEurekaHttpClient implements EurekaHttpClient {

    public EurekaClientConfig clientConfig;
    public Collection additionalFilters;
    public InstanceInfo myInstanceInfo;
    public EurekaEndpoint serviceUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String instanceInfoToJson(InstanceInfo info) throws JsonProcessingException {
        return objectMapper.writeValueAsString(info);
    }
    public NewEurekaHttpClient(EurekaClientConfig clientConfig,
                               Collection additionalFilters,
                               InstanceInfo myInstanceInfo,
                               EurekaEndpoint serviceUrl) {
        this.clientConfig = clientConfig;
        this.additionalFilters = additionalFilters;
        this.myInstanceInfo = myInstanceInfo;
        this.serviceUrl = serviceUrl;
    }

    @Override
    public EurekaHttpResponse<Void> register(InstanceInfo info) {
        try {
            URL url = new URL(serviceUrl.getServiceUrl() + "apps/" + info.getAppName());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // To send request body
            conn.setRequestProperty("Content-Type", "application/json");

            String instanceInfoJson = instanceInfoToJson(info);
            conn.getOutputStream().write(instanceInfoJson.getBytes());

            int responseCode = HttpURLConnection.HTTP_OK;

            return EurekaHttpResponse.status(responseCode);
        } catch (IOException e) {
            e.printStackTrace();
            return EurekaHttpResponse.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
        }
    }

    @Override
    public EurekaHttpResponse<Void> cancel(String appName, String id) {
        try {
            URL url = new URL(serviceUrl.getServiceUrl() + "apps/" + appName + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = HttpURLConnection.HTTP_OK;

            return EurekaHttpResponse.status(responseCode);
        } catch (IOException e) {
            e.printStackTrace();
            return EurekaHttpResponse.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
        }
    }

    @Override
    public EurekaHttpResponse<InstanceInfo> sendHeartBeat(String appName, String id, InstanceInfo info, InstanceInfo.InstanceStatus overriddenStatus) {
        try {
            URL url = new URL(serviceUrl.getServiceUrl() + "apps/" + appName + "/" + id);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonPayload = constructHeartbeatJson(info, overriddenStatus);
            connection.getOutputStream().write(jsonPayload.getBytes());

            int responseCode = connection.getResponseCode();
            return new CustomEurekaHttpResponse<>(responseCode, null); // Create the response
        } catch (IOException e) {
            e.printStackTrace();
            return new CustomEurekaHttpResponse<>(HttpURLConnection.HTTP_INTERNAL_ERROR, null);
        }
    }
    private String constructHeartbeatJson(InstanceInfo info, InstanceInfo.InstanceStatus overriddenStatus) throws JsonProcessingException {
        ObjectNode jsonNode = objectMapper.createObjectNode();

        jsonNode.put("appName", info.getAppName());
        jsonNode.put("instanceId", info.getInstanceId());
        jsonNode.put("status", overriddenStatus.name()); //

        return objectMapper.writeValueAsString(jsonNode);
    }
    public class CustomEurekaHttpResponse<T> extends EurekaHttpResponse<T> {
        public CustomEurekaHttpResponse(int statusCode, T entity) {
            super(statusCode, entity);
        }
    }


    @Override
    public EurekaHttpResponse<Void> statusUpdate(String appName, String id, InstanceInfo.InstanceStatus newStatus, InstanceInfo info) {
        return null;
    }

    @Override
    public EurekaHttpResponse<Void> deleteStatusOverride(String appName, String id, InstanceInfo info) {
        return null;
    }

    @Override
    public EurekaHttpResponse<Applications> getApplications(String... regions) {
        return null;
    }

    @Override
    public EurekaHttpResponse<Applications> getDelta(String... regions) {
        return null;
    }

    @Override
    public EurekaHttpResponse<Applications> getVip(String vipAddress, String... regions) {
        return null;
    }

    @Override
    public EurekaHttpResponse<Applications> getSecureVip(String secureVipAddress, String... regions) {
        return null;
    }

    @Override
    public EurekaHttpResponse<Application> getApplication(String appName) {
        return null;
    }

    @Override
    public EurekaHttpResponse<InstanceInfo> getInstance(String appName, String id) {
        return null;
    }

    @Override
    public EurekaHttpResponse<InstanceInfo> getInstance(String id) {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
