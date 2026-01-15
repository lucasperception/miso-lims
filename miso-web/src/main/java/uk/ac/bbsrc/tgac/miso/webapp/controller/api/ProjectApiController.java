package uk.ac.bbsrc.tgac.miso.webapp.controller.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uk.ac.bbsrc.tgac.miso.core.service.ProjectService;
import uk.ac.bbsrc.tgac.miso.dto.Dtos;
import uk.ac.bbsrc.tgac.miso.dto.ProjectDto;
import uk.ac.bbsrc.tgac.miso.webapp.controller.AbstractRestController;
import uk.ac.bbsrc.tgac.miso.webapp.controller.rest.RestUtils;

@RestController
@RequestMapping("/api/project")
public class ProjectApiController extends AbstractRestController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/create")
    public @ResponseBody Long create(@RequestBody ProjectDto dto) throws IOException {
        return RestUtils.createObject("Project", dto, Dtos::to, projectService, project -> Dtos.asDto(project, true)).getId();
    }
}
