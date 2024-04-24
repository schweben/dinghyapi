package org.schweben.dinghyapi.dto;

public record DinghyDTO(Integer id,	String name, String manufacturer, Integer yardstick, Integer crew, String rig,
		Boolean symmetricSpinnaker, Boolean asymmetricSpinnaker, Integer trapeze, Float length, Float beam, float sailArea,
		float spinnakerArea, String logoPath, String imagePath) {
}
