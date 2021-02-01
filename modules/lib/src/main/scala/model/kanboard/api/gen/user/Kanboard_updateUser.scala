package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Update a user**
* -  Parameters:
* 
*    -  **id** (integer)
*    -  **username** (string, optional)
*    -  **name** (string, optional)
*    -  **email** (string, optional)
*    -  **role** (string, optional, example: app-admin, app-manager,
*       app-user)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_updateUser(id: String, role: String) extends KanboardApiCall[Kanboard_Response_updateUser] {
  override val rpcMethodName: String = "updateUser"

  override val rpcParameters: Seq[(String, String)] = Seq("id" -> id, "role" -> role)
}

case class Kanboard_Response_updateUser(result: Boolean)
