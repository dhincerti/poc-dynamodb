package com.example.pocdynamodb.alias;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface AliasRepository extends CrudRepository<Alias, String> {

}
