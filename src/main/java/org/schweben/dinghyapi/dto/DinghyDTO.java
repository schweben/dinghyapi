package org.schweben.dinghyapi.dto;

public record DinghyDTO(Integer id, String name, String manufacturer, Integer yardstick, Integer crew, String rig,
		Integer hulls, Boolean symmetricSpinnaker, Boolean asymmetricSpinnaker, Integer trapeze, Float length,
		Float beam, Float hullWeight, Float sailArea, Float spinnakerArea, String logoPath, String imagePath) {
}
