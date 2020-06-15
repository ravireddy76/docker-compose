package com.poc.gridfs.configuration;

import com.poc.gridfs.model.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "vehicledata")
public class VehicleConfig {

    private List<Vehicle> vehicles;
}
