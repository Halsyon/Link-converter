package com.linkconverter.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.linkconverter.model.Url;

/**
 * ключом будет закодированное значение адреса, значением будет строка
 * т.к. В сервисе считается количество вызовов каждого адреса.
 * public  public void setTotal(int i, String id); - найти общее число обращений и увеличить на 1
 */
public interface UrlRepo extends CrudRepository<Url, String> {

 @Transactional
 @Modifying
 @Query(value = "update Url set total = total + ?1 where encode_url = ?2", nativeQuery = true)
 public void setTotal(int i, String id);

 }

