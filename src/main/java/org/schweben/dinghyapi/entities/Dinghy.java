package org.schweben.dinghyapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
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
	private String yardstick;

	@Column(name = "CREW", nullable = false)
	private Integer crew;

	@Column(name = "RIG", nullable = false)
	private String rig;

	@Column(name = "SYMMETRIC_SPINNAKER")
	private boolean symmetricSpinnaker;

	@Column(name = "ASYMMETRIC_SPINNAKER")
	private boolean asymmetricSpinnaker;

	@Column(name = "TRAPEZE", nullable = false)
	private int trapeze;

	@Column(name = "LENGTH", nullable = false)
	private float length;

	@Column(name = "BEAM", nullable = false)
	private float beam;

	@Column(name = "SAIL_AREA", nullable = false)
	private float sailArea;

	@Column(name = "SPINNAKER_AREA")
	private float spinnakerArea;

	@Column(name = "LOGO_PATH")
	private String logoPath;

	@Column(name = "IMAGE_PATH")
	private String imagePath;
}
