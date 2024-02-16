package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    String SELECT_ROLE_BY_ID_QUERY = "SELECT r.id, r.name, r.permission FROM Roles r JOIN UserRoles ur ON ur.role_id = r.id JOIN Players u ON u.id = ur.player_id WHERE u.id = :id";

    @Query(value = SELECT_ROLE_BY_ID_QUERY, nativeQuery = true)
    Role getRoleByPlayerId(Long playerId);
}
