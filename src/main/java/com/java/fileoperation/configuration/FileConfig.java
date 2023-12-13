package com.java.fileoperation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class FileConfig {

	@Value(value = "${remote_file_location}")
	private String remoteFileLocation;

	public String getRemoteFileLocation() {
		return remoteFileLocation;
	}

	public void setRemoteFileLocation(String remoteFileLocation) {
		this.remoteFileLocation = remoteFileLocation;
	}
	
	
}
