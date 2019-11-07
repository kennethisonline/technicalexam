package com.kenneth.technicalexam.repositories;

import com.kenneth.technicalexam.model.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Integer> {
}
