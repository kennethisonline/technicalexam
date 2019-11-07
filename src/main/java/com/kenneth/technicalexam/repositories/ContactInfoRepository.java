package com.kenneth.technicalexam.repositories;

import com.kenneth.technicalexam.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
}
