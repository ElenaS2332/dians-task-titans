package com.example.demo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.resolver.EurekaEndpoint;
import com.netflix.discovery.shared.transport.EurekaHttpClient;
import com.netflix.discovery.shared.transport.TransportClientFactory;

import java.util.Collection;

public class NewTransportClientFactory implements TransportClientFactory {
    public EurekaClientConfig clientConfig;
    public Collection additionalFilters;
    public InstanceInfo instanceInfo;
    public NewTransportClientFactory(EurekaClientConfig clientConfig,
                                     Collection additionalFilters,
                                     InstanceInfo instanceInfo) {
        this.clientConfig = clientConfig;
        this.additionalFilters = additionalFilters;
        this.instanceInfo = instanceInfo;
    }

    @Override
    public EurekaHttpClient newClient(EurekaEndpoint serviceUrl) {
        return new NewEurekaHttpClient(clientConfig, additionalFilters, instanceInfo, serviceUrl);
    }

    @Override
    public void shutdown() {

    }
}
