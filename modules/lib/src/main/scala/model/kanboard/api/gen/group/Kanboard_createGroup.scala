package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Create a new group**
* -  Parameters:
* 
*    -  **name** (string, required)
*    -  **external_id** (string, optional)
* 
* -  Result on success: **link_id**
* -  Result on failure: **false**
**/

case class Kanboard_Request_createGroup(name: String, external_id: String) extends KanboardApiCall[Kanboard_Response_createGroup] {
  override val rpcMethodName: String = "createGroup"

  override val rpcParameters: Seq[(String, String)] = Seq("name" -> name, "external_id" -> external_id)
}

case class Kanboard_Response_createGroup(result: Int)
