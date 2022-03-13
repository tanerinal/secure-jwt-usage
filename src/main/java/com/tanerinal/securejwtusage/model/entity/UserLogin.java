package com.tanerinal.securejwtusage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@SequenceGenerator(allocationSize = 1, name = "idgen", sequenceName = "SEQ_USERLOGIN")
public class UserLogin {
	private static final long serialVersionUID = 7215935282787785321L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@Column
	private Long oid;
	private Date created;
	private String username;
	private String userToken;
	private boolean tokenActive;

}
