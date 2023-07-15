package com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Morganne
 */
@Component
public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long> {

     AccountProfile findByHttpImageLink( String httpImageLink);
    AccountProfile findByName( String name);

}