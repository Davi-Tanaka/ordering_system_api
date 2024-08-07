package com.app.domain.database.repository;

import com.app.domain.database.entity.company.CompanyContactEntity;
import com.app.domain.database.entity.company.CompanyEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @Query(
            value = "SELECT COUNT(id) FROM company_entity "
            + "INNER JOIN order_entity "
            + "ON company_entity.id = order_entity.company_id "
            + "WHERE "
            + "AND order_entity.status = \"SOLD\" "
            + "AND company_entity.id = ?1 "
            + "AND ( order_entity.createdAt BETWEEN ?2 AND ?3 )",
            nativeQuery = true
    )
    public Optional<Integer> getCompanySalesCountsBetweenDates(Long companyId, LocalDateTime fromDate, LocalDateTime toDate);    
    
    @Query(
            value = "SELECT COUNT(id) FROM company_contact_entity WHERE company.id = ?1", 
            nativeQuery = true
    )
    public Optional<List<CompanyContactEntity>> getCompanyContacts(Long companyId);
    
    @Query(
            value = "SELECT * FROM company_entity WHERE user_id = ?1",
            nativeQuery = true
    )
    public Optional<List<CompanyEntity>> getUseCompanies(Long userId);
}
