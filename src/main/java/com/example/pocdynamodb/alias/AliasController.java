package com.example.pocdynamodb.alias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class AliasController {
  
  @Autowired
  Environment environment;
  
  @Autowired
  AliasService aliasService;
  
  @RequestMapping(value = "/alias", produces = {"application/json"}, method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Alias createAlias(@Valid @RequestBody @NotNull final Alias alias) {
    return aliasService.createAlias(alias);
  }
  
  @RequestMapping(value = "/alias", produces = {"application/json"}, method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.CREATED)
  public List<Alias> createAlias() {
    return aliasService.getAllAlias();
  }
  
  @RequestMapping(value = "/alias/{aliasId}", produces = {"application/json"}, method = RequestMethod.GET)
  public Alias getAlias(@PathVariable String aliasId) {
    return aliasService.getAliasById(aliasId);
  }
  
  @RequestMapping(value = "/alias/{aliasId}", produces = {"application/json"}, method = RequestMethod.PUT)
  public Alias updateAlias(@PathVariable String aliasId, @Valid @RequestBody @NotNull final Alias alias) {
    return aliasService.updateAlias(aliasId, alias);
  }
  
  @RequestMapping(value = "/alias/{aliasId}", produces = {"application/json"}, method = RequestMethod.DELETE)
  public void deleteAlias(@PathVariable String aliasId) {
    aliasService.deleteAlias(aliasId);
  }
}
