/**
 * 
 */
package mx.com.engen.activedirectoryws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.engen.activedirectoryws.ldap.repository.AdGroup;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;
import mx.com.engen.activedirectoryws.service.AdGroupService;
import mx.com.engen.activedirectoryws.util.HttpResponseValues;

/**
 * @author Carlos Escobedo Arriaga.
 *
 */


@OpenAPIDefinition(info = @Info(title = "Active Directory API"), tags = { @Tag(name = "Users"), @Tag(name = "Groups") })
@RestController
public class AdGroupController {
  /**
   * ActiveDirectory Service.
   */
  @Autowired
  private AdGroupService groupService;
  
  @Tag(name = "Groups")
  @GetMapping(path = "/groups")
  @Operation(summary = "Get all of the groups.")
  public List<AdGroup> getAllGroups() {

    return groupService.getAllGroups();
  }
  
  @Tag(name = "Groups")
  @PutMapping(path = "/groups/{groupName}/users/{userLogonName}")
  @Operation(summary = "Add a user to a group.")
  public CommonResponse addUserToGroup(@PathVariable String userLogonName, @PathVariable String groupName) {
    AdServiceResult result;

    result = groupService.addUserToGroup(userLogonName, groupName);

    if (result.isSuccess()) {
      return new CommonResponse(HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
          HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new CommonResponse(HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE, result.getErrorCode(),
          result.getErrorMesage(), HttpResponseValues.ERROR_MESSAGE);
    }
  }
  
  @Tag(name = "Groups")
  @DeleteMapping(path = "/groups/{groupName}/users/{userLogonName}")
  @Operation(summary = "Remove a user from a group.")
  public CommonResponse removeUserFromGroup(@PathVariable String userLogonName, @PathVariable String groupName) {
    AdServiceResult result;

    result = groupService.removeUserFromGroup(userLogonName, groupName);

    if (result.isSuccess()) {
      return new CommonResponse(HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
          HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new CommonResponse(HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE, result.getErrorCode(),
          result.getErrorMesage(), HttpResponseValues.ERROR_MESSAGE);
    }
  }
}
