package com.cubic.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@XmlRootElement
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerSearchResult {
	@Builder.Default
	private List<CustomerData> results = new ArrayList<CustomerData>();
}
