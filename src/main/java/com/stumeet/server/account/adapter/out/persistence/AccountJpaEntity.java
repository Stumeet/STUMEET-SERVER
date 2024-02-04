package com.stumeet.server.account.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountJpaEntity {

    @Id
    private Long id;
}
