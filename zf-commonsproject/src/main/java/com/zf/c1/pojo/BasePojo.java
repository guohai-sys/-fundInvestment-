package com.zf.c1.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BasePojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3160366781066917634L;
	private Date upDate;
	private Date createDate;
}
