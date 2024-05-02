package org.schweben.dinghyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DinghyDTO(
		@Schema(hidden = true)
		Integer id,

		@Schema(description = "Class name")
		String name,

		@Schema(description = "Manufacturer of the class")
		String manufacturer,

		@Schema(description = "Portsmouth Yardstick")
		Integer yardstick,

		@Schema(description = "Number of crew")
		Integer crew,

		@Schema(description = "Type of rig")
		String rig,

		@Schema(description = "Number of hulls")
		Integer hulls,

		@Schema(description = "Does the class have a symmetric spinnaker")
		Boolean symmetricSpinnaker,

		@Schema(description = "Does the class have an asymmetric spinnaker")
		Boolean asymmetricSpinnaker,

		@Schema(description = "Number of trapezes")
		Integer trapeze,

		@Schema(description = "LOA (m)")
		Float length,

		@Schema(description = "Beam (m)")
		Float beam,

		@Schema(description = "Weight (kg)")
		Float hullWeight,

		@Schema(description = "Sail area (excluding spinnaker) (m2)")
		Float sailArea,

		@Schema(description = "Sail area of spinnaker (m2)")
		Float spinnakerArea,

		@Schema(description = "Path to class logo image")
		String logoPath,

		@Schema(description = "Path to photo of class")
		String imagePath) {
}
