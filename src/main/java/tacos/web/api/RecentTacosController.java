package tacos.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.data.JpaTacoRepository;
import tacos.model.Taco;

import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RepositoryRestController
public class RecentTacosController {

    private JpaTacoRepository tacoRepository;

    @Bean
    public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links) {

        return new ResourceProcessor<PagedResources<Resource<Taco>>>() {
            @Override
            public PagedResources<Resource<Taco>> process(
                    PagedResources<Resource<Taco>> resource) {
                resource.add(
                        links.linkFor(Taco.class)
                                .slash("recent")
                                .withRel("recents"));
                return resource;
            }
        };
    }

    public RecentTacosController(JpaTacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<Resources<TacoResource>> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();

        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
        Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
        recentResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos())
                .withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }
}
