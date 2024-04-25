package org.schweben.dinghyapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DINGHIES")
public class Dinghy {

	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "YARDSTICK")
	private Integer yardstick;

	@Column(name = "CREW", nullable = false)
	private Integer crew;

	@Column(name = "RIG", nullable = false)
	private String rig;

	@Column(name = "HULLS", nullable = false)
	private Integer hulls;

	@Column(name = "SYMMETRIC_SPINNAKER")
	private boolean symmetricSpinnaker;

	@Column(name = "ASYMMETRIC_SPINNAKER")
	private boolean asymmetricSpinnaker;

	@Column(name = "TRAPEZE", nullable = false)
	private Integer trapeze;

	@Column(name = "LENGTH", nullable = false)
	private Float length;

	@Column(name = "BEAM", nullable = false)
	private Float beam;

	@Column(name = "HULL_WEIGHT")
	private Float hullWeight;

	@Column(name = "SAIL_AREA", nullable = false)
	private Float sailArea;

	@Column(name = "SPINNAKER_AREA")
	private Float spinnakerArea;

	@Column(name = "LOGO_PATH")
	private String logoPath;

	@Column(name = "IMAGE_PATH")
	private String imagePath;
}
