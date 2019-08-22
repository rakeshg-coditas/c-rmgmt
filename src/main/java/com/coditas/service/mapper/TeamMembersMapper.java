package com.coditas.service.mapper;

import com.coditas.domain.*;
import com.coditas.service.dto.TeamMembersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TeamMembers} and its DTO {@link TeamMembersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeamMembersMapper extends EntityMapper<TeamMembersDTO, TeamMembers> {



    default TeamMembers fromId(String id) {
        if (id == null) {
            return null;
        }
        TeamMembers teamMembers = new TeamMembers();
        teamMembers.setId(id);
        return teamMembers;
    }
}
