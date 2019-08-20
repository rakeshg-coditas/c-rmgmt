package com.coditas.service.impl;

import com.coditas.service.ProjectService;
import com.coditas.domain.Project;
import com.coditas.repository.ProjectRepository;
import com.coditas.service.dto.ProjectDTO;
import com.coditas.service.mapper.ProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Project}.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    /**
     * Save a project.
     *
     * @param projectDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        log.debug("Request to save Project : {}", projectDTO);
        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    /**
     * Get all the projects.
     *
     * @return the list of entities.
     */
    @Override
    public List<ProjectDTO> findAll() {
        log.debug("Request to get all Projects");
        return projectRepository.findAll().stream()
            .map(projectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one project by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ProjectDTO> findOne(String id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findById(id)
            .map(projectMapper::toDto);
    }

    /**
     * Delete the project by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }
}
